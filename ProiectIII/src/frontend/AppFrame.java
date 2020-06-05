package frontend;

import backend.DB.DB;
import backend.Main;
import backend.University.ClassOfStudents;
import backend.University.Department;
import backend.University.Student;
import backend.University.Teacher;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class AppFrame extends JFrame {

    static private String[] integrOptions = {
            "1  View Student",
            "2  View Teacher",
            "3  View Department",
            "4  View Classes",
            "5  View Students in a specific class",
            "6  View all classmates of a given Student (by id)",
            "7  View Teachers in a specific department (by id)",
            "8  View Teachers in a specific department (by name)",
            "9  View all colleagues of a Teacher (in the same department)",
            "10 View all Teachers that are in no department"};

    static private String[] createOptions = {
            "1 Student",
            "2 Teacher",
            "3 Department",
            "4 Class"
    };

    static private String[] modifyOptions = {
            "1 Student Name",
            "2 Student Class",
            "3 Teacher Subject",
            "4 Teacher Department"
    };

    static private AppFrame interg = new AppFrame("Interogations", integrOptions, 1);
    static private AppFrame create = new AppFrame("Create", createOptions, 2);
    static private AppFrame modify = new AppFrame("Modify", modifyOptions, 3);
    static private AppFrame current = null;

    private String name;
    private String[] options;
    private int action;

    private AppFrame(String name, String[] options, int action) {
        this.name = name;
        this.options = options;
        this.action = action;
        setLayout(new BorderLayout());
        setSize(300, 300);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        ActionListener menuListener = e -> {
            String src = ((AbstractButton) e.getSource()).getText();
            if (src.compareToIgnoreCase(interg.getFrameName()) == 0) {
                setFrame(interg);
            } else if (src.compareToIgnoreCase(create.getFrameName()) == 0) {
                setFrame(create);
            } else if (src.compareToIgnoreCase(modify.getFrameName()) == 0) {
                setFrame(modify);
            } // else unknown
        };
        JMenuBar menuBar = new JMenuBar();
        JButton menuOption;

        menuOption = new JButton("Interogations");
        menuOption.addActionListener(menuListener);
        menuBar.add(menuOption);

        menuOption = new JButton("Create");
        menuOption.addActionListener(menuListener);
        menuBar.add(menuOption);

        menuOption = new JButton("Modify");
        menuOption.addActionListener(menuListener);
        menuBar.add(menuOption);

        setJMenuBar(menuBar);

        JPanel panel = new JPanel();
        JButton btn;

        ActionListener optionListener = e -> {
            String src = ((AbstractButton) e.getSource()).getText();
            int target = -1;
            int nr = 1;
            for (String option : getFrame().getOptions()) {
                if (option.compareToIgnoreCase(src) == 0) {
                    target = nr;
                }
                nr += 1;
            }
            if (target != 0) {
                exec (getFrame().action, target);
            }
        };

        for (int optionCnt = 0; optionCnt < options.length; ++optionCnt) {
            btn = new JButton(options[optionCnt]);
            btn.addActionListener(optionListener);
            panel.add(btn);
        }
        panel.setLayout(new GridLayout(options.length,1));
        add(panel);
    }

    private String getFrameName() {
        return name;
    }

    static public AppFrame getFrame () {
        if (current == null) {
            Main.open();
            current = interg;
            interg.setVisible(true);
        }
        return current;
    }

    static public void setFrame (AppFrame frame) {
        getFrame().setVisible(false);
        frame.setLocation(current.getLocation());
        frame.setSize(current.getSize());
        current = frame;
        current.setVisible(true);
    }

    public String[] getOptions() {
        return options;
    }

    public int getAction() {
        return action;
    }

    private void exec (int action, int target) {
        int id, targetId;
        String name;
        try {
            switch (action) {
                case 1:
                    switch (target) {
                        case 1:
                            JOptionPane.showInputDialog(Student.toPrintStudents() + "\nPress enter to continue");
                            break;
                        case 2:
                            JOptionPane.showInputDialog(Teacher.toPrintTeachers() + "\nPress enter to continue");
                            break;
                        case 3:
                            JOptionPane.showInputDialog(Department.toPrintDepartments() + "\nPress enter to continue");
                            break;
                        case 4:
                            JOptionPane.showInputDialog(ClassOfStudents.toPrintClasses() + "\nPress enter to continue");
                            break;
                        case 5:
                            id = Integer.parseInt(JOptionPane.showInputDialog("Class id: "));
                            if (ClassOfStudents.getClass(id) == null) {
                                throw new RuntimeException("Unsupported Class id " + id);
                            } else {
                                JOptionPane.showInputDialog(ClassOfStudents.getClass(id).toPrintStudents() + "\nPress enter to continue");
                            }
                            break;
                        case 6:
                            id = Integer.parseInt(JOptionPane.showInputDialog("Student id: "));
                            if (Student.getStudent(id) == null) {
                                throw new RuntimeException("Unsupported Student id " + id);
                            } else {
                                if (Student.getStudent(id).getCls() == null) {
                                    JOptionPane.showInputDialog(Student.getStudent(id) + "\nPress enter to continue");
                                } else {
                                    JOptionPane.showInputDialog(Student.getStudent(id).getCls().toPrintStudents() + "\nPress enter to continue");
                                }
                            }
                            break;
                        case 7:
                            id = Integer.parseInt(JOptionPane.showInputDialog("Department id: "));
                            if (Department.getDepartment(id) == null) {
                                throw new RuntimeException("Unsupported Department id " + id);
                            } else {
                                JOptionPane.showInputDialog(Department.getDepartment(id).toPrintTeachers() + "\nPress enter to continue");
                            }
                            break;
                        case 8:
                            String str = JOptionPane.showInputDialog("Department name: ");
                            if (Department.getDepartment(str) == null) {
                                throw new RuntimeException("Unsupported Department name " + str);
                            } else {
                                JOptionPane.showInputDialog(Department.getDepartment(str).toPrintTeachers() + "\nPress enter to continue");
                            }
                            break;
                        case 9:
                            id = Integer.parseInt(JOptionPane.showInputDialog("Teacher id: "));
                            if (Teacher.getTeacher(id) == null) {
                                throw new RuntimeException("Unsupported Teacher id " + id);
                            } else {
                                if (Teacher.getTeacher(id).getDepartment() == null) {
                                    JOptionPane.showInputDialog(Teacher.getTeacher(id) + "\nPress enter to continue");
                                } else {
                                    JOptionPane.showInputDialog(Teacher.getTeacher(id).getDepartment().toPrintTeachers() + "\nPress enter to continue");
                                }
                            }
                            break;
                        case 10:
                            JOptionPane.showInputDialog(Teacher.toPrintTeachersNoDep() + "\nPress enter to continue");
                            break;
                        default:
                            throw new RuntimeException("Unsupported target: " + target);
                    }
                    break;
                case 2: {
                    switch (target) {
                        case 1:
                            id = Integer.parseInt(JOptionPane.showInputDialog("Student id: "));
                            name = JOptionPane.showInputDialog("The Student Name is: ");
                            if (Student.createStudent(id, name, null)) {
                                System.out.println("Success");
                            } else {
                                System.out.println("Failed");
                            }
                            break;
                        case 2:
                            id = Integer.parseInt(JOptionPane.showInputDialog("Teacher id: "));
                            name = JOptionPane.showInputDialog("The Teacher subject is: ");
                            if (Teacher.createTeacher(id, name, null)) {
                                System.out.println("Success");
                            } else {
                                System.out.println("Failed");
                            }
                            break;
                        case 3:
                            id = Integer.parseInt(JOptionPane.showInputDialog("Department id: "));
                            name = JOptionPane.showInputDialog("The Department name is: ");
                            if (Department.createDep(id, name)) {
                                System.out.println("Success");
                            } else {
                                System.out.println("Failed");
                            }
                            break;
                        case 4:
                            id = Integer.parseInt(JOptionPane.showInputDialog("Class id: "));
                            name = JOptionPane.showInputDialog("The Class Name is: ");
                            if (ClassOfStudents.createClass(id, name)) {
                                System.out.println("Success");
                            } else {
                                System.out.println("Failed");
                            }
                            break;
                        default:
                            throw new RuntimeException("Unsupported target table: " + target);
                    }
                    break;
                }
                case 3:
                    id = Integer.parseInt(JOptionPane.showInputDialog("His id: "));
                    switch (target) {
                        case 1:
                            if (Student.getStudent(id) == null) {
                                throw new RuntimeException("Unsupported Student id " + id);
                            } else {
                                Student.getStudent(id).setName(JOptionPane.showInputDialog("His new name: "));
                            }
                            break;
                        case 2:
                            if (Student.getStudent(id) == null) {
                                throw new RuntimeException("Unsupported Student id " + id);
                            } else {
                                targetId = Integer.parseInt(JOptionPane.showInputDialog("His new class id is: "));
                                if (ClassOfStudents.getClass(targetId) == null) {
                                    throw new RuntimeException("Unsupported Class id " + id);
                                } else {
                                    ClassOfStudents.getClass(targetId).addStudent(Student.getStudent(id));
                                }
                            }
                            break;
                        case 3:
                            if (Teacher.getTeacher(id) == null) {
                                throw new RuntimeException("Unsupported Teacher id " + id);
                            } else {
                                Teacher.getTeacher(id).setSubject(JOptionPane.showInputDialog("His new subject: "));
                            }
                            break;
                        case 4:
                            if (Teacher.getTeacher(id) == null) {
                                throw new RuntimeException("Unsupported Teacher id " + id);
                            } else {
                                targetId = Integer.parseInt(JOptionPane.showInputDialog("His new Department id is: "));
                                if (Department.getDepartment(targetId) == null) {
                                    throw new RuntimeException("Unsupported Department id " + id);
                                } else {
                                    Department.getDepartment(targetId).addTeacher(Teacher.getTeacher(id));
                                }
                            }
                            break;
                        default:
                            throw new RuntimeException("Unsupported target table: " + target);
                    }
                    break;
                default:
                    break; // should never enter here
                }
        } catch (Exception e) {
            JOptionPane.showInputDialog(e.getMessage() + "\nPress enter to continue");
        }
    }

    public static void main(String[] args) {
       getFrame();
    }
}
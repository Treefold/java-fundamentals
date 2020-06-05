package frontend;

import backend.University.ClassOfStudents;
import backend.University.Department;
import backend.University.Student;
import backend.University.Teacher;

import javax.swing.*;
import java.awt.event.WindowEvent;

public class MainFrame extends JFrame {

    public Thread thread = new Thread(() -> exec());

    private static void open () {
        Department.fetchData();
        Teacher.fetchData();
        ClassOfStudents.fetchData();
        Student.fetchData();
    }
    public MainFrame() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        open();
        thread.run();
    }

    private void exec () {
        int action, target, id, targetId;
        String name;
        try {
            boolean exit = false;
            while (!exit) {
                action = Integer.parseInt(JOptionPane.showInputDialog(
                        "\tChoose your action:\n" +
                                "0. Exit\n" +
                                "1. Predefined Interrogations\n" +
                                "2. Create Entity in Table\n" +
                                "3. Modify Entity in Table\n" +
                                "Your choice:"));
                switch (action) {
                    case 0:
                        exit = true;
                        break;
                    case 1:
                        target = Integer.parseInt(JOptionPane.showInputDialog(
                                "Target table:\n" +
                                        "1.  View Student\n" +
                                        "2.  View Teacher\n" +
                                        "3.  View Department\n" +
                                        "4.  View Classes\n" +
                                        "5.  View Students in a specific class\n" +
                                        "6.  View all classmates of a given Student (by id)\n" +
                                        "7.  View Teachers in a specific department (by id)\n" +
                                        "8.  View Teachers in a specific department (by name)\n" +
                                        "9.  View all colleagues of a Teacher (in the same department)\n" +
                                        "10. View all Teachers that are in no department\n" +
                                        "Your choice: "));
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
                        target = Integer.parseInt(JOptionPane.showInputDialog(
                                "Target table:\n" +
                                        "1. Student\n" +
                                        "2. Teacher\n" +
                                        "3. Department\n" +
                                        "4. Class\n" +
                                        "Your choice: "));
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
                        target = Integer.parseInt(JOptionPane.showInputDialog(
                                "Target table:\n" +
                                        "1. Student Name\n" +
                                        "2. Student Class\n" +
                                        "3. Teacher Subject\n" +
                                        "4. Teacher Department\n" +
                                        "Your choice: "));
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
                        throw new RuntimeException("Unsupported action: " + action);
                }
            }
        } catch (NumberFormatException e){
            // when closing the input dialog
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            backend.DB.DB.close();
            this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
        }
    }

    public static void main(String[] args) {
        MainFrame mainFrame = new MainFrame();
        try {
            mainFrame.thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

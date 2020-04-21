import University.*;

import java.util.Scanner;

public class Main {
    private static String nextString(Scanner scanner) {
        scanner.nextLine();
        return scanner.nextLine().replace("\n", "").replace(",", ";");
    }

    private static void open () {
        Department.fetchData();
        Teacher.fetchData();
        ClassOfStudents.fetchData();
        Student.fetchData();
    }

    private static void close() {
        Teacher.enableModify(false);
        Department.enableModify(false);
        ClassOfStudents.enableModify(false);
        Student.enableModify(false);
    }

    public static void main(String[] args) {
        open();
        try {
            boolean exit = false;
            while (!exit) {
                System.out.println("\n\nChoose your action:\n" +
                        "0. Exit\n" +
                        "1. Predefined Interrogations\n" +
                        "2. Create Entity in Table\n" +
                        "3. Modify Entity in Table\n");
                System.out.print("Your choice: ");
                Scanner scanner = new Scanner(System.in);
                int action = scanner.nextInt();
                int target, id, targetId;
                switch (action) {
                    case 0:
                        exit = true;
                        break;
                    case 1:
                        System.out.println("Target table:\n" +
                                "1.  View Student\n" +
                                "2.  View Teacher\n" +
                                "3.  View Department\n" +
                                "4.  View Classes\n" +
                                "5.  View Students in a specific class\n" +
                                "6.  View all classmates of a given Student (by id)\n" +
                                "7.  View Teachers in a specific department (by id)\n" +
                                "8.  View Teachers in a specific department (by name)\n" +
                                "9.  View all colleagues of a Teacher (in the same department)\n" +
                                "10. View all Teachers that are in no department\n");
                        System.out.print("Your choice: ");
                        target = scanner.nextInt();
                        switch (target) {
                            case 1:
                                Student.printStudents();
                                break;
                            case 2:
                                Teacher.printTeachers();
                                break;
                            case 3:
                                Department.printDepartments();
                                break;
                            case 4:
                                ClassOfStudents.printClasses();
                                break;
                            case 5:
                                System.out.print("Class id: ");
                                id = scanner.nextInt();
                                if (ClassOfStudents.getClass(id) == null) {
                                    throw new RuntimeException("Unsupported Class id " + id);
                                } else {
                                    ClassOfStudents.getClass(id).printStudents();
                                }
                                break;
                            case 6:
                                System.out.print("Student id: ");
                                id = scanner.nextInt();
                                if (Student.getStudent(id) == null) {
                                    throw new RuntimeException("Unsupported Student id " + id);
                                } else {
                                    if (Student.getStudent(id).getCls() == null) {
                                        System.out.println(Student.getStudent(id));
                                    } else {
                                        Student.getStudent(id).getCls().printStudents();
                                    }
                                }
                                break;
                            case 7:
                                System.out.print("Department id: ");
                                id = scanner.nextInt();
                                if (Department.getDepartment(id) == null) {
                                    throw new RuntimeException("Unsupported Department id " + id);
                                } else {
                                    Department.getDepartment(id).printTeachers();
                                }
                                break;
                            case 8:
                                System.out.print("Department name: ");
                                String str = nextString(scanner);
                                if (Department.getDepartment(str) == null) {
                                    throw new RuntimeException("Unsupported Department name " + str);
                                } else {
                                    Department.getDepartment(str).printTeachers();
                                }
                                break;
                            case 9:
                                System.out.print("Teacher id: ");
                                id = scanner.nextInt();
                                if (Teacher.getTeacher(id) == null) {
                                    throw new RuntimeException("Unsupported Teacher id " + id);
                                } else {
                                    if (Teacher.getTeacher(id).getDepartment() == null) {
                                        System.out.println(Teacher.getTeacher(id));
                                    } else {
                                        Teacher.getTeacher(id).getDepartment().printTeachers();
                                    }
                                }
                                break;
                            case 10:
                                Teacher.printTeachersNoDep();
                                break;
                            default:
                                throw new RuntimeException("Unsupported target: " + target);
                        }
                        break;
                    case 2:
                        System.out.println("Target table:\n" +
                                "1. Student\n" +
                                "2. Teacher\n" +
                                "3. Department\n" +
                                "4. Class\n");
                        System.out.print("Your choice: ");
                        target = scanner.nextInt();
                        switch (target) {
                            case 1:
                                if (Student.canCreate()) {
                                    System.out.print("The Student Name is: ");
                                    new Student(nextString(scanner));
                                    System.out.println("Success");
                                } else {
                                    System.out.println("Student is full, can't create");
                                }
                                break;
                            case 2:
                                if (Teacher.canCreate()) {
                                    System.out.print("The Teacher Subject: ");
                                    new Teacher(nextString(scanner));
                                    System.out.println("Success");
                                } else {
                                    System.out.println("Teacher is full, can't create");
                                }
                                break;
                            case 3:
                                if (Department.canCreate()) {
                                    System.out.print("The Department Name: ");
                                    if (Department.createDep(nextString(scanner)) == -1) {
                                        System.out.println("Department already exists");
                                    } else {
                                        System.out.println("Success");
                                    }
                                } else {
                                    System.out.println("Department is full, can't create");
                                }
                                break;
                            case 4:
                                if (ClassOfStudents.canCreate()) {
                                    System.out.print("The Class Name: ");
                                    new ClassOfStudents(nextString(scanner));
                                    System.out.println("Success");
                                } else {
                                    System.out.println("Class is full, can't create");
                                }
                                break;
                            default:
                                throw new RuntimeException("Unsupported target table: " + target);
                        }
                        break;
                    case 3:
                        System.out.println("Target table:\n" +
                                "1. Student Name\n" +
                                "2. Student Class\n" +
                                "3. Teacher Subject\n" +
                                "4. Teacher Department\n");
                        System.out.print("Your choice: ");
                        target = scanner.nextInt();
                        System.out.print("His Id: ");
                        id = scanner.nextInt();
                        switch (target) {
                            case 1:
                                if (Student.getStudent(id) == null) {
                                    throw new RuntimeException("Unsupported Student id " + id);
                                } else {
                                    System.out.print("His new name: ");
                                    Student.getStudent(id).setName(nextString(scanner));
                                }
                                break;
                            case 2:
                                if (Student.getStudent(id) == null) {
                                    throw new RuntimeException("Unsupported Student id " + id);
                                } else {
                                    System.out.print("His new class id is: ");
                                    targetId = scanner.nextInt();
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
                                    System.out.print("His new subject is: ");
                                    Teacher.getTeacher(id).setSubject(nextString(scanner));
                                }
                                break;
                            case 4:
                                if (Teacher.getTeacher(id) == null) {
                                    throw new RuntimeException("Unsupported Teacher id " + id);
                                } else {
                                    System.out.print("His new Department id is: ");
                                    targetId = scanner.nextInt();
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
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("Bye");
            close();
        }
    }
}

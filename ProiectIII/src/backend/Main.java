package backend;

import backend.University.*;

import java.util.Scanner;

public class Main {//*
    private static String nextString(Scanner scanner) {
        scanner.nextLine();
        return scanner.nextLine().replace("\n", "");
    }

    private static void open () {
        Department.fetchData();
        Teacher.fetchData();
        ClassOfStudents.fetchData();
        Student.fetchData();
    }

    public static void main(String[] args) {
        open();
        int action, target, id, targetId;
        String name;
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
                action = scanner.nextInt();
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
                                System.out.println(Student.toPrintStudents());
                                break;
                            case 2:
                                Teacher.toPrintTeachers();
                                break;
                            case 3:
                                System.out.println(Department.toPrintDepartments());
                                break;
                            case 4:
                                System.out.println(ClassOfStudents.toPrintClasses());
                                break;
                            case 5:
                                System.out.print("Class id: ");
                                id = scanner.nextInt();
                                if (ClassOfStudents.getClass(id) == null) {
                                    throw new RuntimeException("Unsupported Class id " + id);
                                } else {
                                    System.out.println(ClassOfStudents.getClass(id).toPrintStudents());
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
                                        System.out.println(Student.getStudent(id).getCls().toPrintStudents());
                                    }
                                }
                                break;
                            case 7:
                                System.out.print("Department id: ");
                                id = scanner.nextInt();
                                if (Department.getDepartment(id) == null) {
                                    throw new RuntimeException("Unsupported Department id " + id);
                                } else {
                                    System.out.println(Department.getDepartment(id).toPrintTeachers());
                                }
                                break;
                            case 8:
                                System.out.print("Department name: ");
                                String str = nextString(scanner);
                                if (Department.getDepartment(str) == null) {
                                    throw new RuntimeException("Unsupported Department name " + str);
                                } else {
                                    System.out.println(Department.getDepartment(str).toPrintTeachers());
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
                                        System.out.println(Teacher.getTeacher(id).getDepartment().toPrintTeachers());
                                    }
                                }
                                break;
                            case 10:
                                System.out.println(Teacher.toPrintTeachersNoDep());
                                break;
                            default:
                                throw new RuntimeException("Unsupported target: " + target);
                        }
                        break;
                    case 2: {
                        System.out.println("Target table:\n" +
                                "1. Student\n" +
                                "2. Teacher\n" +
                                "3. Department\n" +
                                "4. Class\n");
                        System.out.print("Your choice: ");
                        target = scanner.nextInt();
                        switch (target) {
                            case 1:
                                System.out.print("The Student Id is: ");
                                id = scanner.nextInt();
                                System.out.print("The Student Name is: ");
                                name = nextString(scanner);
                                if (Student.createStudent(id, name, null)) {
                                    System.out.println("Success");
                                } else {
                                    System.out.println("Failed");
                                }
                                break;
                            case 2:
                                System.out.print("The Teacher Id is: ");
                                id = scanner.nextInt();
                                System.out.print("The Teacher Subject: ");
                                name = nextString(scanner);
                                if (Teacher.createTeacher(id, name, null)) {
                                    System.out.println("Success");
                                } else {
                                    System.out.println("Failed");
                                }
                                break;
                            case 3:
                                System.out.print("The Department Id is: ");
                                id = scanner.nextInt();
                                System.out.print("The Department Name: ");
                                name = nextString(scanner);
                                if (Department.createDep(id, name)) {
                                    System.out.println("Success");
                                } else {
                                    System.out.println("Failed");
                                }
                                break;
                            case 4:
                                System.out.print("The Class Id is: ");
                                id = scanner.nextInt();
                                System.out.print("The Class Name: ");
                                name = nextString(scanner);
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
            backend.DB.DB.close();
        }
    }//*/
}

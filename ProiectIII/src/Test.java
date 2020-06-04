import University.*;

public class Test {
    private static void printSep() {
        System.out.println("---------------------------------------------------------------\n");
    }

    private static void open () {
        Department.fetchData();
        Teacher.fetchData();
        ClassOfStudents.fetchData();
        Student.fetchData();
    }

    public static void main(String[] args) {
        open();
        //*
        System.out.println("Test Department:\n");
        Department.printDepartments();
        Department.createDep(10, "Info");
        Department.createDep(11, "Algebra");
        Department.createDep(12, "ALgeBRa");
        Department.createDep(13, "Geometry");
        Department.printDepartments();
        System.out.println(Department.getDepartment("Info"));
        System.out.println(Department.getDepartment(10));
        System.out.println(Department.getDepartment(13));
        Department.getDepartment(10).deleteDep();
        Department.printDepartments();
        Department.getDepartment("Algebra").printTeachers();
        System.out.println("\nEnd of Test Department\n");

        printSep();

        System.out.println("Test Teacher in Department:\n");
        Teacher.createTeacher(3, "Geometry", null);
        Department.getDepartment(13).addTeacher(Teacher.getTeacher(3));
        Teacher.printTeachers();
        Department.getDepartment(13).deleteDep();
        System.out.println(Teacher.getTeacher(3));
        Teacher.getTeacher(3).deleteTeacher();
        Teacher.printTeachers();
        Teacher.createTeacher(21, "Boolean Algebra", Integer.toString(Department.getDepartment("algebra").getId()));
        Teacher.createTeacher(22, "Boolean Algebra1", null);
        Teacher.createTeacher(23, "Boolean Algebra2", null);
        Teacher.printTeachers();
        Teacher algebraTeach  = Teacher.getTeacher(21);
        Teacher algebraTeach1 = Teacher.getTeacher(22);
        Teacher algebraTeach2 = Teacher.getTeacher(23);
        System.out.println(algebraTeach);
        System.out.println(algebraTeach1);
        System.out.println(Department.getDepartment("Algebra").isInDepartments(algebraTeach));
        System.out.println(Department.getDepartment("Algebra").isInDepartments(algebraTeach1));
        Department.getDepartment("Algebra").printTeachers();
        Department.getDepartment("Algebra").addTeacher(algebraTeach1);
        Department.getDepartment("Algebra").addTeacher(algebraTeach2);
        Department.getDepartment("Algebra").removeTeacher(algebraTeach);
        Department.getDepartment("Algebra").printTeachers();
        System.out.println(Department.getDepartment("Algebra").isInDepartments(algebraTeach));
        System.out.println(Department.getDepartment("Algebra").isInDepartments(algebraTeach1));
        System.out.println();
        algebraTeach.deleteTeacher();
        algebraTeach1.deleteTeacher();
        algebraTeach2.deleteTeacher();
        System.out.println(Department.getDepartment("Algebra"));
        System.out.println("End of Test Teacher in Department\n");

        printSep();

        System.out.println("Test Students in Class(ofStudents):\n");
        ClassOfStudents.createClass(31,"232.1");
        ClassOfStudents.createClass(32,"232.2");
        ClassOfStudents[] classes = {ClassOfStudents.getClass(31), ClassOfStudents.getClass(32)};
        Student.createStudent(11, "Andrei", null);
        Student.createStudent(12, "ana", null);
        Student.createStudent(13, "josheph", "31");

        Student[] students = {Student.getStudent(11), Student.getStudent(12), Student.getStudent(13)};

        classes[0].printStudents(); classes[1].printStudents();

        classes[0].addStudent(students[1]);

        classes[0].printStudents(); classes[1].printStudents();

        classes[1].addStudent(students[2]);

        classes[0].printStudents(); classes[1].printStudents();

        for (Student student : students) {
            student.deleteStudent();
        }
        Student.printStudents();

        for (ClassOfStudents cls : classes) {
            cls.deleteClass();
        }

        System.out.println("End of Test Students in Class(ofStudents)\n");
        printSep();

        // The next one doesn't save its data
        System.out.println("TestNoBD Hour in Timetable with Teacher & Class(ofStudents):\n");
        algebraTeach.printTimetable();
        classes[0].printTimetable();
        classes[1].printTimetable();
        Hour.createHour(0, 2, "lab10", algebraTeach, classes[0]);
        Hour.createHour(1, 3, "lab10", algebraTeach, classes[0]);
        Hour.createHour(2, 3, "lab01", algebraTeach, classes[1]);
        algebraTeach.printTimetable();
        classes[0].printTimetable();
        classes[0].clearHour(0, 2); // clear the first
        // hour
        Hour.createHour(1, 3, "lab10", algebraTeach, classes[1]); // teacher changes classes
        algebraTeach.printTimetable();
        classes[0].printTimetable();
        classes[1].printTimetable();

        System.out.println("End of TestNoBD Hour in Timetable with Teacher & Class(ofStudents)\n");
    }
}

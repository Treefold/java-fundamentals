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

    private static void close() {
        Teacher.enableModify(false);
        Department.enableModify(false);
        ClassOfStudents.enableModify(false);
        Student.enableModify(false);
    }

    public static void main(String[] args) {
        // manually clean all .csv files and run this code
        open();
        System.out.println("Test Department:\n");
        Department.printDepartments();
        System.out.println(Department.createDep("Info"));
        System.out.println(Department.createDep("Algebra"));
        System.out.println(Department.createDep("ALgeBRa"));
        System.out.println(Department.createDep("Geometry"));
        Department.printDepartments();
        System.out.println(Department.getDepartment("Info"));
        System.out.println(Department.getDepartment(2));
        System.out.println(Department.getDepartment(3));
        Department.getDepartment("Algebra").printTeachers();
        System.out.println("\nEnd of Test Department\n");

        printSep();

        System.out.println("Test Teacher in Department:\n");
        Teacher algebraTeach  = new Teacher("Boolean Algebra");
        Teacher algebraTeach1 = new Teacher("Boolean Algebra1");
        Teacher algebraTeach2 = new Teacher("Boolean Algebra2");
//        Teacher algebraTeach  = Teacher.getTeacher(0);
//        Teacher algebraTeach1 = Teacher.getTeacher(1);
//        Teacher algebraTeach2 = Teacher.getTeacher(2);
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
        System.out.println("End of Test Teacher in Department\n");

        printSep();

        System.out.println("Test Students in Class(ofStudents):\n");
        Student[] students = {new Student("Andrei"), new Student("ana"), new Student("josheph")};
//        Student[] students = {Student.getStudent(0), Student.getStudent(1), Student.getStudent(2)};
        System.out.println(students[0] + "\n" + students[1] + "\n" + students[2] + "\n");
        ClassOfStudents[] classes = {new ClassOfStudents("232.1"), new ClassOfStudents("232.2")};
//        ClassOfStudents[] classes = {ClassOfStudents.getClass(0), ClassOfStudents.getClass(1)};

        System.out.println(classes[0] + "\n" + classes[1] + "\n");
        classes[0].addStudent(students[0]); //System.out.println(classes[0] + "\n" + classes[1] + "\n");
        classes[0].addStudent(students[1]); //System.out.println(classes[0] + "\n" + classes[1] + "\n");
        classes[1].addStudent(students[2]); System.out.println(classes[0] + "\n" + classes[1] + "\n");
        classes[1].addStudent(students[1]); System.out.println(classes[0] + "\n" + classes[1] + "\n"); // transfer student
        System.out.println("End of Test Students in Class(ofStudents)\n");

        printSep();
        // The next one doesn't save its data

        System.out.println("Test Hour in Timetable with Teacher & Class(ofStudents):\n");
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

        System.out.println("End of Test Hour in Timetable with Teacher & Class(ofStudents)\n");
        close();
    }
}

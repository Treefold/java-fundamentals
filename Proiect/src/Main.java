import University.*;

public class Main {
    private  static void printSep() {
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
        open();
        System.out.println("Test Department:\n");
        Department.PrintDepartments();
//        System.out.println(Department.CreateDep("Info"));
//        System.out.println(Department.CreateDep("Algebra"));
//        System.out.println(Department.CreateDep("ALgeBRa"));
//        System.out.println(Department.CreateDep("Geometry"));
        Department.PrintDepartments();
        System.out.println(Department.getDepartment("Info"));
        System.out.println(Department.getDepartment(2));
        System.out.println(Department.getDepartment(3));
        Department.getDepartment("Algebra").PrintTeachers();
        System.out.println("\nEnd of Test Department\n");

        printSep();

        System.out.println("Test Teacher in Department:\n");
//        Teacher algebraTeach  = new Teacher("Boolean Algebra");
//        Teacher algebraTeach1 = new Teacher("Boolean Algebra1");
//        Teacher algebraTeach2 = new Teacher("Boolean Algebra2");
        Teacher algebraTeach  = Teacher.getTeacher(0);
        Teacher algebraTeach1 = Teacher.getTeacher(1);
        Teacher algebraTeach2 = Teacher.getTeacher(2);
        System.out.println(algebraTeach);
        System.out.println(algebraTeach1);
        System.out.println(Department.getDepartment("Algebra").IsInDepartments(algebraTeach));
        System.out.println(Department.getDepartment("Algebra").IsInDepartments(algebraTeach1));
        Department.getDepartment("Algebra").PrintTeachers();
        Department.getDepartment("Algebra").AddTeacher(algebraTeach1);
        Department.getDepartment("Algebra").AddTeacher(algebraTeach2);
        Department.getDepartment("Algebra").RemoveTeacher(algebraTeach);
        Department.getDepartment("Algebra").PrintTeachers();
        System.out.println(Department.getDepartment("Algebra").IsInDepartments(algebraTeach));
        System.out.println(Department.getDepartment("Algebra").IsInDepartments(algebraTeach1));
        System.out.println();
        System.out.println("End of Test Teacher in Department\n");

        printSep();

        System.out.println("Test Students in Class(ofStudents):\n");
//        Student[] students = {new Student("Andrei"), new Student("ana"), new Student("josheph")};
        Student[] students = {Student.getStudent(0), Student.getStudent(1), Student.getStudent(2)};
        System.out.println(students[0] + "\n" + students[1] + "\n" + students[2] + "\n");
//        ClassOfStudents[] classes = {new ClassOfStudents("232.1"), new ClassOfStudents("232.2")};
        ClassOfStudents[] classes = {ClassOfStudents.getClass(0), ClassOfStudents.getClass(1)};

        System.out.println(classes[0] + "\n" + classes[1] + "\n");
        classes[0].AddStudent(students[0]); //System.out.println(classes[0] + "\n" + classes[1] + "\n");
        classes[0].AddStudent(students[1]); //System.out.println(classes[0] + "\n" + classes[1] + "\n");
        classes[1].AddStudent(students[2]); System.out.println(classes[0] + "\n" + classes[1] + "\n");
        classes[1].AddStudent(students[1]); System.out.println(classes[0] + "\n" + classes[1] + "\n"); // transfer student
        System.out.println("End of Test Students in Class(ofStudents)\n");

        printSep();
        // The next one doesn't save its data

        System.out.println("Test Hour in Timetable with Teacher & Class(ofStudents):\n");
        algebraTeach.PrintTimetable();
        classes[0].PrintTimetable();
        classes[1].PrintTimetable();
        Hour.CreateHour(0, 2, "lab10", algebraTeach, classes[0]);
        Hour.CreateHour(1, 3, "lab10", algebraTeach, classes[0]);
        Hour.CreateHour(2, 3, "lab01", algebraTeach, classes[1]);
        algebraTeach.PrintTimetable();
        classes[0].PrintTimetable();
        classes[0].ClearHour(0, 2); // clear the first
        // hour
        Hour.CreateHour(1, 3, "lab10", algebraTeach, classes[1]); // teacher changes classes
        algebraTeach.PrintTimetable();
        classes[0].PrintTimetable();
        classes[1].PrintTimetable();

        System.out.println("End of Test Hour in Timetable with Teacher & Class(ofStudents)\n");
        close();
    }
}

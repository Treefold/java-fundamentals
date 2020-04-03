package University;

import java.util.Arrays;

public class ClassOfStudents {
    static private int classIdCnt;
    static private int maxClassSize = 33;
    private int classSize;
    private int id;
    private String name;
    private Student[] students; // always sorted by surname, on equal by name, on equal by id
    private Timetable timetable;
    public ClassOfStudents(String name) {
        this.id        = classIdCnt++;
        this.students  = new Student[maxClassSize];
        this.classSize = 0;
        this.name      = name;
        this.timetable = new Timetable();
    }

    public int getId() {
        return id;
    }

    public int getClassSize() {
        return classSize;
    }

    public Student[] getStudents() {
        return students;
    }

    public boolean IsInClass (Student student) {
        return student != null && student.getCls() == this;
    }

    public boolean AddStudent (Student student) {
        if (IsInClass(student)) {return true;} // already in this class
        if (student == null || classSize >= maxClassSize) {return false;}
        if (student.getCls() != null) {student.getCls().RemoveStudent(student);}
        student.setCls(this);
        int currPoz = classSize++;
        // keep the list ordered
        while (currPoz > 0 && Student.CompareByName(student, students[currPoz-1]) == -1) {
            students[currPoz--] = students[currPoz];
        }
        students[currPoz] = student;
        return true;
    }

    public boolean RemoveStudent (Student student) {
        if (!IsInClass(student)) {return false;} // not in the class
        int currIndex = 0;
        while (currIndex < classSize) {
            if (student.getId() == students[currIndex].getId()) {break;}
            ++currIndex;
        }
        student.setCls(null); // not in a (this) class anymore (classless)
        if (currIndex == classSize) {return false;} // but it wasn't actually in this class
        ++currIndex;
        while (currIndex < classSize) {
            students[currIndex-1] = students[currIndex++];
        }
        --classSize; // found and eliminated
        return true;
    }

    public void AddHour (Hour hour) {
        timetable.AddHour(hour);
    }

    public void ClearHour (int day, int beginsAt) {
        timetable.ClearHour(day, beginsAt);
    }

    public void PrintTimetable () {
        System.out.println("Class " + name + " " + timetable);
    }

    @Override
    public String toString() {
        String toPrint = "ClassOfStudents { \n\tid=" + id + "\n\tname='" + name + "'\n\tstudents: ";
        if (classSize == 0) {toPrint += "No Students";}
        else {
            toPrint += "[";
            for (int currPoz = 0; currPoz < classSize; ++currPoz) {
                toPrint += "\n\t\t " + students[currPoz];
            }
            toPrint += "\n\t]";
        }
        toPrint += "\n}";
        return toPrint;
    }
}

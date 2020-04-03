package University;

import People.Employee;

import java.util.Objects;

public class Teacher extends Employee {
    static private int teacherIdCnt = 0;
    private String subject;
    private Department department; // should be changed only from Department class
    // timetable[x][y] := what hour is starting at y (hour) on x (day)
    // where x = day (0:4 -> Mon:Fri) and y = starting hour (0:5 -> {8, 10, 12, 14, 16, 18})
    private Timetable timetable;

    public Teacher (String subject) { // testing purpose
        super(++teacherIdCnt, "", "", "", '\0', "", "", 0, "Teacher", "");
        this.subject   = subject;
        this.timetable = new Timetable();
    }

    public Teacher(String cnp, String surname, String name, char gender, String phone, String mail, int salary, String job, String EmploymentDate, String subject) {
        super(++teacherIdCnt, cnp, surname, name, gender, phone, mail, salary, job, EmploymentDate);
        this.subject = subject;
        timetable    = new Timetable();
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public void AddHour (Hour hour) {
        timetable.AddHour(hour);
    }

    public void ClearHour (int day, int beginsAt) {
        timetable.ClearHour(day, beginsAt);
    }

    public void PrintTimetable () {
        System.out.println(subject + " Teacher " + timetable);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Teacher)) return false;
        Teacher teacher = (Teacher) o;
        return this.id == teacher.id;
    }

    @Override
    public String toString() {
        return subject + " Teacher "       + super.toString() +
                " is in "       + (department == null ? "no department" : department);
    }
}

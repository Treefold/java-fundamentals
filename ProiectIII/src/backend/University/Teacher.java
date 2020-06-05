package backend.University;

import backend.DB.DB;
import backend.MyLog.Log;
import backend.People.Employee;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class Teacher extends Employee {
    // project specific added fields (static)
    static private String tableName = "teachers";
    static private String idName    = "teacher_id";
    static private ArrayList<Teacher> teachers = new ArrayList<>();
    // project specific added fields
    private Timetable timetable;
        // timetable[x][y] := what hour is starting at y (hour) on x (day)
        // where x = day (0:4 -> Mon:Fri) and y = starting hour (0:5 -> {8, 10, 12, 14, 16, 18})
    // table fields
    private String subject;
    private Department department; // should be changed only from a Department Object

    public static void fetchData() {
        ResultSet data = DB.fetchData(tableName);
        if (data == null) {
            Log.logData("No prior data for " + tableName);
            return;
        }
        try {
            Log.logData("Started fetching data for " + tableName);
            while (data.next()) {
                new Teacher(data);
            }
        } catch (SQLException e) {
            System.out.println("Data fetching for " + tableName + " has been interrupted");
            e.printStackTrace();
        } finally {
            Log.logData("Stopped fetching data for " + tableName);
        }
    }

    private Teacher (ResultSet data) throws SQLException {
        super (data);
        subject = data.getString("subject");
        department = null;
        if (data.getString("dept_id") != null) {
            Department.getDepartment(Integer.parseInt(data.getString("dept_id"))).addTeacher(this);
        }
        timetable = new Timetable();
        teachers.add(this);
        Log.logData("Fetched " + this);
    }

    private Teacher(int id, String cnp, String surname, String name, String gender, String phone, String mail, int salary, String job, String hire_date, String subject, String dept_id) {
        super(id, cnp, surname, name, gender, phone, mail, salary, job, hire_date);
        this.subject = subject;
        department = null;
        if (dept_id != null) {
            department = Department.getDepartment(Integer.parseInt(dept_id));
            if (department != null) {
                department.addTeacher(this);
            }
        }
        timetable = new Timetable();
        teachers.add(this);
        Log.logData("Created new " + this);
    }

    static public boolean createTeacher (int id, String cnp, String surname, String name, String gender, String phone, String mail, String salary, String job, String hire_date, String subject, String dept_id) {
        if (getTeacher(id) != null) {
            System.out.println("Teacher id already exists");
            return false;
        }
        if (salary == null) {salary = "0";}
        String[] cols = {idName, "cnp", "surname", "name", "gender", "phone", "mail", "salary", "job", "hire_date", "subject", "dept_id"};
        String[] vals = {Integer.toString(id), "'"+cnp+"'", "'"+surname+"'", "'"+name+"'", "'"+gender+"'", "'"+phone+"'", "'"+mail+"'", salary, "'"+job+"'", "'"+hire_date+"'", "'"+subject+"'", dept_id};
        if (DB.insertData(tableName, cols, vals)) {
            new Teacher(id, cnp, surname, name, gender, phone, mail, Integer.parseInt(salary), job, hire_date, subject, dept_id);
            return true;
        } else {return false;}
    }

    static public boolean createTeacher (int id, String subject, String dept_id) {
        return createTeacher(id, null, null, null, null, null, null, null, null, null, subject, dept_id);
    }

    public static Teacher getTeacher (int id) {
        for (Teacher teacher: teachers) {
            if (teacher.id == id) {
                return teacher;
            }
        }
        return null;
    }

    public boolean deleteTeacher () {
//        timetable.delete();
        if (department != null) {department.removeTeacher(this);}
        if (DB.deleteData(tableName,  idName + "=" + id)) {
            teachers.remove(this);
            return true;
        } else { // fail -> revert
            if (department != null) {department.addTeacher(this);}
            return false;
        }
    }

    @Override
    protected String getTableName() {
        return tableName;
    }

    @Override
    protected String getIdName () {
        return idName;
    }

    public String getSubject() {
        return subject;
    }

    public boolean setSubject(String subject) {
        if (DB.updateData(getTableName(), "subject", "'"+subject+"'", getIdName() + "=" + id)) {
            this.subject = subject;
            return true;
        } else {return false;}
    }

    public Department getDepartment() {
        return department;
    }

    public boolean setDepartment(Department department) {
        // this call should only be made from a Department Object
        if (DB.updateData(getTableName(), "dept_id", (department == null ? null : Integer.toString(department.getId())), getIdName() + "=" + id)) {
            this.department = department;
            return true;
        } else {return false;}
    }

    public void addHour(Hour hour) {
        timetable.addHour(hour);
    }

    public void clearHour(int day, int beginsAt) {
        timetable.clearHour(day, beginsAt);
    }

    public void printTimetable() {
        System.out.println(subject + " Teacher " + timetable);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Teacher)) return false;
        Teacher teacher = (Teacher) o;
        return this.id == teacher.id;
    }

    public static String toPrintTeachers() {
        if (teachers.size() == 0) {return "No Teachers";}
        String toPrint = "Teachers [";
        for (Teacher teacher : teachers) {
            toPrint += "\n\t " + teacher;
        }
        toPrint += "\n]";
        return toPrint;
    }

    public static String toPrintTeachersNoDep() {
        if (teachers.size() == 0) {return "No Teachers without Department";}
        boolean atLestOne = false;
        String toPrint = "Teachers [";
        for (Teacher teacher : teachers) {
            if (teacher.department == null) {
                atLestOne = true;
                toPrint += "\n\t " + teacher;
            }
        }
        toPrint += "\n]";
        return atLestOne?toPrint:"No Teachers without Department";
    }

    @Override
    public String toString() {
        return subject + " Teacher " + super.toString() + " is in " + (department == null ? "no department" : department);
    }
}

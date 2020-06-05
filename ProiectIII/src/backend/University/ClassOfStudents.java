package backend.University;

import backend.DB.DB;
import backend.MyLog.Log;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ClassOfStudents {
    // project specific added fields (static)
    static private String tableName = "classes";
    static private String idName    = "class_id";
    static private ArrayList<ClassOfStudents> classes = new ArrayList<>();
    // project specific added fields
    private ArrayList<Student> students = new ArrayList<>();
    private Timetable timetable;
        // timetable[x][y] := what hour is starting at y (hour) on x (day)
        // where x = day (0:4 -> Mon:Fri) and y = starting hour (0:5 -> {8, 10, 12, 14, 16, 18})
    // table fields
    private int id;
    private String name;

    public static void fetchData() {
        ResultSet data = DB.fetchData(tableName);
        if (data == null) {
            Log.logData("No prior data for " + tableName);
            return;
        }
        try {
            Log.logData("Started fetching data for " + tableName);
            while (data.next()) {
                new ClassOfStudents(data);
            }
        } catch (SQLException e) {
            System.out.println("Data fetching for " + tableName + " has been interrupted");
            e.printStackTrace();
        } finally {
            Log.logData("Stopped fetching data for " + tableName);
        }
    }

    private ClassOfStudents (ResultSet data) throws SQLException {
        id = Integer.parseInt(data.getString(idName));
        name = data.getString("name");
        classes.add(this);
        Log.logData("Fetched " + this);
    }

    private ClassOfStudents(int id, String name) {
        this.id        = id;
        this.name      = name;
        this.timetable = new Timetable();
        classes.add(this);
        Log.logData("Created new " + this);
    }

    static public ClassOfStudents getClass (int id) {
        for (ClassOfStudents cls : classes) {
            if (cls.getId() == id) {
                return cls; // found
            }
        }
        return null; // not found
    }

    static public ClassOfStudents getClass (String  name) {
        for (ClassOfStudents cls : classes) {
            if (cls.getName().compareToIgnoreCase(name) == 0) {
                return cls; // found
            }
        }
        return null; // not found
    }

    static public boolean createClass (int id, String name) {
        if (getClass(id) != null) {return false;}
        String[] cols = {idName, "name"};
        String[] vals = {Integer.toString(id), name};
        if (DB.insertData(tableName, cols, vals)) {
            new ClassOfStudents(id, name);
            return true;
        } else {return false;}
    }

    public boolean deleteClass () {
        for (Student student : students) {
            student.setCls(null);
        }
        if (DB.deleteData(tableName, idName + "=" + this.id)) {
            classes.remove(this);
            return true;
        } else { // fail -> revert
            for (Student student : students) {
                student.setCls(this);
            }
            return false;
        }
    }

    public int getId() {
        return id;
    }

    public boolean setId(int id) {
        if (getClass(id) != null) {
        return false;
    }
        if (DB.updateData(tableName, "'"+idName+"'", Integer.toString(id), idName + "=" + id)) {
            this.id = id;
            return true;
        } else {return false;}
    }

    public String getName() {
        return name;
    }

    public boolean setName(String name) {
        if (getClass(name) != null) {
            return false;
        }
        if (DB.updateData(tableName, "name", "'" + name +"'", idName + "=" + id)) {
            this.name = name;
            return true;
        } else {return false;}
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    public boolean isInClass(Student student) {
        if (student == null) {return false;} // not in here
        for (Student studentCurr : students) {
            if (studentCurr.equals(student)) {
                return true; // found
            }
        }
        return false; // not found
    }

    public boolean addStudent(Student student) {
        if (student == null) {return false;}
        student.setCls(this);
        return true;
    }

    public void addJustStudent(Student student) {
        if (!isInClass(student)) {students.add (student);}
    }

    public boolean removeStudent(Student student) {
        if (student == null || student.getCls() != this) {
            return true; // not in this department
        }
        if (student.setCls(null)) {
            return true;
        } else {return false;}
    }

    public void removeJustStudent(Student student) {
        if (student != null) {
            students.remove(student);
        }
    }

    public void addHour(Hour hour) {
        timetable.addHour(hour);
    }

    public void clearHour(int day, int beginsAt) {
        timetable.clearHour(day, beginsAt);
    }

    public void printTimetable() {
        System.out.println("Class " + name + " " + timetable);
    }

    public String toPrintStudents() {
        String toPrint = "ClassOfStudents { \n\tid=" + id + "\n\tname='" + name + "'\n\tstudents: ";
        if (students.size() == 0) {toPrint += "No Students";}
        else {
            toPrint += "[";
            for (Student student : students) {
                toPrint += "\n\t\t " + student;
            }
            toPrint += "\n\t]";
        }
        toPrint += "\n}";
        return toPrint;
    }

    public static String toPrintClasses() {
        if (classes.size() == 0) {return"No Classes";}
        String toPrint = "Classes [";
        for (ClassOfStudents cls : classes) {
            toPrint += "\n\t" + cls;
        }
        toPrint += "\n]";
        return toPrint;
    }

    @Override
    public String toString() {
        return"ClassOfStudents {id=" + id + "; name='" + name + "'}";
    }//*/
}

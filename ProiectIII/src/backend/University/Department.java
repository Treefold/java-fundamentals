package backend.University;

import backend.DB.DB;
import backend.MyLog.Log;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Department {
    // project specific added fields (static)
    static private String tableName = "departments";
    static private String idName    = "dept_id";
    static private ArrayList <Department> departments = new ArrayList<>();
    // project specific added fields
    private ArrayList<Teacher> teachers = new ArrayList<>();
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
                new Department(data);
            }
        } catch (SQLException e) {
            System.out.println("Data fetching for " + tableName + " has been interrupted");
            e.printStackTrace();
        } finally {
            Log.logData("Stopped fetching data for " + tableName);
        }
    }

    private Department(ResultSet data) throws SQLException {
        id = Integer.parseInt(data.getString(idName));
        name = data.getString("name");
        departments.add(this);
        Log.logData("Fetched " + this);
    }

    private Department(int id, String name) {
        this.id = id;
        this.name = name;
        departments.add(this);
        Log.logData("Created new " + this);
    }

    // returns whether it was a success or not
    static public boolean createDep(int id, String name) {
        if (getDepartment(id) != null) {
            System.out.println("Department id already exists");
            return false;
        } else if (getDepartment(name) != null) {
            System.out.println("Department name already exists");
            return false;
        }
        else {
            String[] cols = {"dept_id", "name"};
            String[] vals = {Integer.toString(id), "'" + name + "'"};
            if (DB.insertData(tableName, cols, vals)) {
                new Department(id, name);
                return true;
            } else {return false;}
        }
    }

    public boolean deleteDep () {
        for (Teacher teacher : teachers) {
            teacher.setDepartment(null);
        }
        if (DB.deleteData(tableName, idName + "=" + this.id)) {
            departments.remove(this);
            return true;
        } else { // fail -> revert
            for (Teacher teacher : teachers) {
                teacher.setDepartment(this);
            }
            return false;
        }
    }

    static public String getIdName () {return idName;}

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean setId(int id) {
        if (getDepartment(id) != null) {
            return false;
        }
        if (DB.updateData(tableName, idName, Integer.toString(id), idName + "=" + id)) {
            this.id = id;
            return true;
        } else {return false;}
    }

    public boolean setName(String name) {
        if (getDepartment(name) != null) {
            return false;
        }
        if (DB.updateData(tableName, "name", "'" + name +"'", idName + "=" + id)) {
            this.name = name;
            return true;
        } else {return false;}
    }

    // returns null on unknown department
    public static Department getDepartment (int id) {
        for (Department dept : departments) {
            if (dept.id == id) {
                return dept;
            }
        }
        return null; // not found
    }

    public static Department getDepartment (String name) {
        for (Department dept : departments) {
            if (dept.name.compareToIgnoreCase(name) == 0) {
                return dept;
            }
        }
        return null; // not found
    }

    public boolean isInDepartments(Teacher teacher) {
        if (teacher == null) {return false;} // not in here
        for (Teacher teacherCurr : teachers) {
            if (teacherCurr.equals(teacher)) {
                return true; // found
            }
        }
        return false; // not found
    }

    public boolean addTeacher(Teacher teacher) {
        if (teacher == null) {return false;}
        if (!isInDepartments(teacher)) {teachers.add (teacher);}
        return teacher.setDepartment(this);
    }

    public boolean removeTeacher(Teacher teacher) {
        if (teacher == null || teacher.getDepartment() != this) {
            return true; // not in this department
        }
        if (teacher.setDepartment(null)) {
            teachers.remove(teacher);
            return true;
        } else {return false;}
    }

    public String toPrintTeachers() {
        if (teachers.size() == 0) {return "No Teachers in " + this;}
        String toPrint = this + " [";
        for (Teacher teacher : teachers) {
            toPrint += "\n\t " + teacher;
        }
        toPrint += "\n]";
        return toPrint;
    }

    public static String toPrintDepartments() {
        if (departments.size() == 0) {return "No Departments";}
        String toPrint = "Departments [";
        for (Department dept : departments) {
            toPrint += "\n\t " + dept;
        }
        toPrint += "\n]";
        return toPrint;
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                "; name='" + name + '\'' +
                '}';
    }
}

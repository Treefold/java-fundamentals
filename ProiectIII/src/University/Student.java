package University;

import DB.DB;
import MyLog.Log;
import People.Person;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Student extends Person {
    // project specific added fields (static)
    static private String tableName = "students";
    static private String idName    = "student_id";
    static private ArrayList<Student> students = new ArrayList<>();
    // project specific added fields;
    private ClassOfStudents cls; // should be changed only from ClassOfStudents class

    public static void fetchData() {
        ResultSet data = DB.fetchData(tableName);
        if (data == null) {
            Log.logData("No prior data for " + tableName);
            return;
        }
        try {
            Log.logData("Started fetching data for " + tableName);
            while (data.next()) {
                new Student(data);
            }
        } catch (SQLException e) {
            System.out.println("Data fetching for " + tableName + " has been interrupted");
            e.printStackTrace();
        } finally {
            Log.logData("Stopped fetching data for " + tableName);
        }
    }

    private Student (ResultSet data) throws SQLException {
        super(data);
        cls = null;
        if (data.getString("class_id") != null) {
            ClassOfStudents.getClass(Integer.parseInt(data.getString("class_id"))).addStudent(this);
        }
        students.add(this);
        Log.logData("Fetched " + this);
    }

    private Student(int id, String cnp, String surname, String name, String gender, String phone, String mail, String class_id) {
        super(id, cnp, surname, name, gender, phone, mail);
        cls = null;
        if (class_id != null) {
            cls = ClassOfStudents.getClass(Integer.parseInt(class_id));
            if (cls != null) {
                cls.addStudent(this);
            }
        }
        students.add(this);
        Log.logData("Created new " + this);
    }

    static public boolean createStudent (int id, String cnp, String surname, String name, String gender, String phone, String mail, String class_id) {
        if (getStudent(id) != null) {
            System.out.println("Student id already exists");
            return false;
        }
        String[] cols = {idName, "cnp", "surname", "name", "gender", "phone", "mail", "class_id"};
        String[] vals = {Integer.toString(id), "'"+cnp+"'", "'"+surname+"'", "'"+name+"'", "'"+gender+"'", "'"+phone+"'", "'"+mail+"'", class_id};
        if (DB.insertData(tableName, cols, vals)) {
            new Student(id, cnp, surname, name, gender, phone, mail, class_id);
            return true;
        } else {return false;}
    }

    static public boolean createStudent (int id, String name, String class_id) { // testing purpose and for the interface
        return createStudent(id, "", "", name, "M", "", "", class_id);
    }

    public boolean deleteStudent () {
//        timetable.delete();
        if (cls != null) {cls.removeJustStudent(this);}
        if (DB.deleteData(tableName,  idName + "=" + id)) {
            students.remove(this);
            return true;
        } else { // fail -> revert
            if (cls != null) {cls.addStudent(this);}
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

    static public Student getStudent (int id) {
        for (Student student : students) {
            if (student.id == id) {
                return student; // found
            }
        }
        return null; // not found
    }

    public ClassOfStudents getCls() {
        return cls;
    }

    public boolean setCls(ClassOfStudents cls) {
        // this call should only be made from a ClassOfStudnets Object
        if (DB.updateData(getTableName(), "class_id", (cls == null ? null : Integer.toString(cls.getId())), getIdName() + "=" + id)) {
            if (this.cls != null) {this.cls.removeJustStudent(this);}
            this.cls = cls;
            if (cls != null) {cls.addJustStudent(this);}
            return true;
        } else {return false;}
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student)) return false;
        Student student = (Student) o;
        return this.id == student.id;
    }

    public static void printStudents() {
        if (students.size() == 0) {System.out.println("No Students"); return;}
        String toPrint = "Students [";
        for (Student student : students) {
            toPrint += "\n\t " + student;
        }
        toPrint += "\n]";
        System.out.println(toPrint);
    }

    @Override
    public String toString() {
        return "Student{" + super.toString() + "} is in " + (cls == null ? "no class" : cls);
    }

    // 0 if equal, -1 if student1 < student2; +1 if student1 > student2
    static public int compareByName(Student student1, Student student2) {
        if (student1.equals(student2)) {return 0;}
        int rez = student1.getSurname().compareToIgnoreCase(student2.getSurname());
        if (rez != 0) {return rez < 0 ? -1 : 1;}
        rez = student1.getName().compareToIgnoreCase(student2.getName());
        if (rez != 0) {return rez < 0 ? -1 : 1;}
        return student1.getId() < student2.getId() ? -1 : 1;
    }
}

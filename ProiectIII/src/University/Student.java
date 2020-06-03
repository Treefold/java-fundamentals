package University;

import MyLog.Log;
import People.Person;

import java.io.*;

public class Student extends Person {
    private static boolean fetchedAll = false;
    private static String fileName = "Student.csv";
    private static FileWriter tableFile = null;
    static private int studentsIdCnt = 0;
    static private int studentsMaxCnt = 10;
    static private Student[] students = new Student[studentsMaxCnt];
    private ClassOfStudents cls; // should be changed only from ClassOfStudents class
    // TODO: record marks

    public static void fetchData() {
        try (BufferedReader csvReader = new BufferedReader(new FileReader(fileName))) {
            String row;
            csvReader.readLine(); // Skip first line
            while ((row = csvReader.readLine()) != null) {
                new Student ((row+",.").split(","));
            }
            fetchedAll = true;
        } catch (FileNotFoundException e) {
            System.out.println("No prior data saved for Departments");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            enableModify(true);
        }
    }

    public static void enableModify(boolean enable) {
        if (enable) {
            tableFile = enableTable(fileName, true);
        }
        else {
            try {
                tableFile.flush();
                tableFile.close();
                tableFile = null;
            } catch (Exception e) {
                // enters here when it shall log but the logging is not enabled
            }
        }
    }

    protected void updated () {
        if (!fetchedAll) {return;}
        Log.logData("Updated " + this);
        enableModify (false);
        tableFile = enableTable(fileName, false);
        for (int i = 0; i < studentsIdCnt; ++i)
            if (students[i] != null)
                students[i].saveData(tableFile);
    }

    public Student(String[] csvData) {
        super (csvData);
        cls = null;
        students[id] = this;
        studentsIdCnt = id + 1;
        if (csvData[7].length() != 0) {
            ClassOfStudents clss = ClassOfStudents.getClass(Integer.parseInt(csvData[7]));
            if (clss != null) {
                clss.addStudent(this);
            }
        }
    }

    public Student(String name) { // testing purpose and for the gast interface
        super(studentsIdCnt++, "", "", name, 'M', "", "");
        students[id] = this;
        Log.logData("Created new " + this);
        saveData(tableFile);
    }

    public Student(String cnp, String surname, String name, char gender, String phone, String mail) {
        super(studentsIdCnt++, cnp, surname, name, gender, phone, mail);
        students[id] = this;
        Log.logData("Created new " + this);
        saveData(tableFile);
    }

    static public boolean canCreate() {
        return studentsIdCnt < studentsMaxCnt;
    }

    static public Student getStudent (int id) {
        return id >= studentsIdCnt ? null : students[id];
    }

    public ClassOfStudents getCls() {
        return cls;
    }

    public void setCls(ClassOfStudents cls) {
        this.cls = cls;
        updated();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student)) return false;
        Student student = (Student) o;
        return this.id == student.id;
    }

    public static void printStudents() {
        if (studentsIdCnt == 0) {System.out.println("No Students"); return;}
        String toPrint = "Students [";
        for (int currPoz = 0; currPoz < studentsIdCnt; ++currPoz) {
            toPrint += "\n\t " + students[currPoz];
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

    @Override
    protected String toCsv() {
        return super.toCsv() + "," + (cls == null ? "" : cls.getId());
    }
}

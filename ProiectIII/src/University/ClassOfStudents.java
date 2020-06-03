package University;

import MyLog.Log;
import MyLog.Table;

import java.io.*;

public class ClassOfStudents extends Table {
    static private int classIdCnt;
    static private int classMaxCnt = 10;
    static private ClassOfStudents[] classes = new ClassOfStudents[classMaxCnt];
    static private String fileName = "ClassOfStudents.csv";
    static private FileWriter tableFile = null;
    static private int maxClassSize = 33;
    private int classSize;
    private int id;
    private String name;
    private Student[] students; // always sorted by surname, on equal by name, on equal by id
    private Timetable timetable;

    public static void fetchData() {
        try (BufferedReader csvReader = new BufferedReader(new FileReader(fileName))) {
            String row;
            csvReader.readLine(); // Skip first line
            while ((row = csvReader.readLine()) != null) {
                new ClassOfStudents (row.split(","));
            }
        } catch (FileNotFoundException e) {
            System.out.println("No prior data saved for Departments");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            enableModify(true);
        }
    }

    public static void enableModify(boolean enable) {
        // Departments can only be added
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

    private ClassOfStudents (String[] csvData) {
        // all data was written by us with this app so the data should be valid
        this.id        = Integer.parseInt(csvData[0]);
        this.students  = new Student[maxClassSize];
        this.classSize = 0;
        this.name      = csvData[1];
        this.timetable = new Timetable();
        classes[id]    = this;
        classIdCnt     = id + 1;
    }

    public ClassOfStudents(String name) {
        this.id        = classIdCnt++;
        this.students  = new Student[maxClassSize];
        this.classSize = 0;
        this.name      = name;
        this.timetable = new Timetable();
        classes[id]    = this;
        Log.logData("Created new " + this);
        saveData(tableFile);
    }

    static public boolean canCreate() {
        return classIdCnt < classMaxCnt;
    }

    static public ClassOfStudents getClass (int id) {
        return id >= classIdCnt ? null : classes[id];
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

    public boolean isInClass(Student student) {
        return student != null && student.getCls() == this;
    }

    public boolean addStudent(Student student) {
        if (isInClass(student)) {return true;} // already in this class
        if (student == null || classSize >= maxClassSize) {return false;}
        if (student.getCls() != null) {student.getCls().removeStudent(student);}
        student.setCls(this);
        int currPoz = classSize++;
        // keep the list ordered
        while (currPoz > 0 && Student.compareByName(student, students[currPoz-1]) == -1) {
            students[currPoz--] = students[currPoz];
        }
        students[currPoz] = student;
        return true;
    }

    public boolean removeStudent(Student student) {
        if (!isInClass(student)) {return false;} // not in the class
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

    public void addHour(Hour hour) {
        timetable.addHour(hour);
    }

    public void clearHour(int day, int beginsAt) {
        timetable.clearHour(day, beginsAt);
    }

    public void printTimetable() {
        System.out.println("Class " + name + " " + timetable);
    }

    public void printStudents() {
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
        System.out.println(toPrint);
    }

    public static void printClasses() {
        if (classIdCnt == 0) {System.out.println("No Classes"); return;}
        String toPrint = "Classes [";
        for (int currPoz = 0; currPoz < classIdCnt; ++currPoz) {
            toPrint += "\n\t" + classes[currPoz];
        }
        toPrint += "\n]";
        System.out.println(toPrint);
    }

    @Override
    public String toString() {
        return"ClassOfStudents {id=" + id + "; name='" + name + "'}";
    }

    @Override
    protected String toCsv () {
        return id + "," + name;
    }
}

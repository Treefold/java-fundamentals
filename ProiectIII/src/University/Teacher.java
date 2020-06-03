package University;

import MyLog.Log;
import People.Employee;

import java.io.*;

public class Teacher extends Employee {
    private static boolean fetchedAll = false;
    private static String fileName = "Teacher.csv";
    private static FileWriter tableFile = null;
    static private int teacherIdCnt = 0;
    static private int teacherMaxCnt = 10;
    static private Teacher[] teachers = new Teacher[teacherMaxCnt];
    private String subject;
    private Department department; // should be changed only from Department class
    // timetable[x][y] := what hour is starting at y (hour) on x (day)
    // where x = day (0:4 -> Mon:Fri) and y = starting hour (0:5 -> {8, 10, 12, 14, 16, 18})
    private Timetable timetable;

    public static void fetchData() {
        try (BufferedReader csvReader = new BufferedReader(new FileReader(fileName))) {
            String row;
            csvReader.readLine(); // Skip first line
            while ((row = csvReader.readLine()) != null) {
                new Teacher ((row+",.").split(","));
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

    @Override
    protected void updated () {
        if (!fetchedAll) {return;}
        Log.logData("Updated " + this);
        enableModify (false);
        tableFile = enableTable(fileName, false);
        for (int i = 0; i < teacherIdCnt; ++i)
            if (teachers[i] != null)
                teachers[i].saveData(tableFile);
    }

    protected Teacher (String[] csvData) {
        super (csvData);
        subject = csvData[11];
        this.timetable = new Timetable();
        department = null;
        teachers[id] = this;
        teacherIdCnt = id + 1;
        if (csvData[12].length() != 0) {
            Department dep = Department.getDepartment(Integer.parseInt(csvData[12]));
            if (dep != null) {
                dep.addTeacher(this);
            }
        }
    }

    public Teacher (String subject) { // testing purpose
        super(teacherIdCnt++, "", "", "", 'M', "", "", 0, "Teacher", "");
        this.subject   = subject;
        this.timetable = new Timetable();
        teachers[id] = this;
        Log.logData("Created new " + this);
        saveData(tableFile);
    }

    public Teacher(String cnp, String surname, String name, char gender, String phone, String mail, int salary, String job, String EmploymentDate, String subject) {
        super(teacherIdCnt++, cnp, surname, name, gender, phone, mail, salary, job, EmploymentDate);
        this.subject = subject;
        timetable    = new Timetable();
        teachers[id] = this;
        Log.logData("Created new " + this);
        saveData(tableFile);
    }

    static public boolean canCreate() {
        return teacherIdCnt < teacherMaxCnt;
    }

    public static Teacher getTeacher (int id) {
        return id >= teacherIdCnt ? null : teachers[id];
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
        updated();
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
        updated();
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

    public static void printTeachers() {
        if (teacherIdCnt == 0) {System.out.println("No Teachers"); return;}
        String toPrint = "Teachers [";
        for (int currPoz = 0; currPoz < teacherIdCnt; ++currPoz) {
            toPrint += "\n\t " + teachers[currPoz];
        }
        toPrint += "\n]";
        System.out.println(toPrint);
    }

    public static void printTeachersNoDep() {
        if (teacherIdCnt == 0) {System.out.println("No Teachers"); return;}
        boolean atLestOne = false;
        String toPrint = "Teachers [";
        for (int currPoz = 0; currPoz < teacherIdCnt; ++currPoz) {
            if (teachers[currPoz].department == null) {
                atLestOne = true;
                toPrint += "\n\t " + teachers[currPoz];
            }
        }
        toPrint += "\n]";
        System.out.println(atLestOne?toPrint:"No Teachers without Department");
    }

    @Override
    public String toString() {
        return subject + " Teacher " + super.toString() + " is in " + (department == null ? "no department" : department);
    }

    protected String toCsv() {
        return super.toCsv() + "," + subject + "," + (department == null ? "" : department.getId());
    }
}

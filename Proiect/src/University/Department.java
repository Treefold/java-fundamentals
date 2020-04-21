package University;

import MyLog.Log;
import MyLog.Table;

import java.io.*;

public class Department extends Table {
    static private int depIdCnt;
    static private int depMaxCnt = 30;
    static private int depSizeCnt;
    static private int depMaxSize = 10;
    static private Department[] departments = new Department[depMaxCnt];
    private static String fileName = "Department.csv"; // fetchData();
    private static FileWriter tableFile = null; // setFileName(fileName);
    private int id;
    private String name;
    private Teacher[] teachers = new Teacher[depMaxSize];

    public static void fetchData() {
        try (BufferedReader csvReader = new BufferedReader(new FileReader(fileName))) {
            String row;
            csvReader.readLine(); // Skip first line
            while ((row = csvReader.readLine()) != null) {
                new Department (row.split(","));
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

    private Department (String[] csvData) {
        // all data was written by us with this app so the data should be valid
        this.id         = Integer.parseInt(csvData[0]);
        this.name       = csvData[1];
        departments[id] = this;
        depIdCnt        = id + 1;
    }

    private Department(String name) {
        this.id = depIdCnt;
        this.name = name;
        departments[depIdCnt++] = this;
        Log.logData("Created new " + this);
        saveData(tableFile);
    }

    // returns department Id on success and 01 on failure or if it already exists
    static public int CreateDep (String name) {
        if (depIdCnt >= depMaxCnt || getDepartment(name) != null) {return -1;}
        return (new Department(name)).id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // returns null on unknown department
    public static Department getDepartment (int id) {
        return id >= depIdCnt ? null : departments[id];
    }

    public static Department getDepartment (String name) {
        for (int currPoz = 0; currPoz < depIdCnt; ++currPoz) {
            if (departments[currPoz].name.compareToIgnoreCase(name) == 0) {
                return departments[currPoz];
            }
        }
        return null; // not found
    }

    public boolean IsInDepartments (Teacher teacher) {
        return teacher != null && teacher.getDepartment() == this;
    }

    public void RemoveTeacher (Teacher teacher) {
        if (!IsInDepartments(teacher)) {return;} // not in this department
        int currIndex = 0;
        while (currIndex < depMaxSize) {
            if (teacher.getId() == teachers[currIndex].getId()) {break;}
            ++currIndex;
        }
        teacher.setDepartment(null); // not in this department
        if (currIndex == depMaxSize) {return;} // but it wasn't actually in this department
        ++currIndex;
        while (currIndex < depMaxSize) {
            teachers[currIndex-1] = teachers[currIndex++];
        }
        --depSizeCnt; // found and eliminated
    }

    public boolean AddTeacher (Teacher teacher) {
        if (IsInDepartments(teacher)) {return true;}  // already in this department
        if (depSizeCnt >= depMaxSize) {return false;} // this department is full
        teacher.setDepartment(this);
        teachers[depSizeCnt++] = teacher;
        return true;
    }

    public void PrintTeachers() {
        if (depSizeCnt == 0) {System.out.println("No Teachers in " + this); return;}
        String toPrint = this + " [";
        for (int currPoz = 0; currPoz < depSizeCnt; ++currPoz) {
            toPrint += "\n\t " + teachers[currPoz];
        }
        toPrint += "\n]";
        System.out.println(toPrint);
    }

    public static void PrintDepartments () {
        if (depIdCnt == 0) {System.out.println("No Departments"); return;}
        String toPrint = "Departments [";
        for (int currPoz = 0; currPoz < depIdCnt; ++currPoz) {
            toPrint += "\n\t " + departments[currPoz];
        }
        toPrint += "\n]";
        System.out.println(toPrint);
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                "; name='" + name + '\'' +
                '}';
    }

    @Override
    protected String toCsv () {
        return id + "," + name;
    }
}

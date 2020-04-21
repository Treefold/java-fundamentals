package University;

import MyLog.Log;
import People.Employee;

import java.io.*;

public class AuxiliaryStuff extends Employee {
    private static boolean fetchedAll = false;
    static private int auxStuffIdCnt;
    static private int auxStuffMaxCnt = 10;
    static  AuxiliaryStuff[] auxiliaryStuffs = new AuxiliaryStuff[auxStuffMaxCnt];

    private String jobDescription;
    private static String fileName = "AuxiliaryStuff.csv";
    private static FileWriter tableFile = null;

    public static void fetchData() {
        try (BufferedReader csvReader = new BufferedReader(new FileReader(fileName))) {
            String row;
            csvReader.readLine(); // Skip first line
            while ((row = csvReader.readLine()) != null) {
                new AuxiliaryStuff (row.split(","));
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
        for (int i = 0; i < auxStuffIdCnt; ++i)
            if (auxiliaryStuffs[i] != null)
                auxiliaryStuffs[i].saveData(tableFile);
    }

    protected AuxiliaryStuff (String[] csvData) {
        super (csvData);
        jobDescription = csvData[11];
        auxiliaryStuffs[id] = this;
        auxStuffIdCnt = id + 1;
    }

    public AuxiliaryStuff(String cnp, String surname, String name, char gender, String phone, String mail, int salary, String job, String EmploymentDate, String jobDescription) {
        super(++auxStuffIdCnt, cnp, surname, name, gender, phone, mail, salary, job, EmploymentDate);
        this.jobDescription = jobDescription;
        auxiliaryStuffs[id] = this;
        Log.logData("Created new " + this);
        saveData(tableFile);
    }

    public String getJobDescription() {
        return jobDescription;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
        updated();
    }

    public static void printAuziliaryStuff() {
        if (auxStuffIdCnt == 0) {System.out.println("No Auxiliary stuff"); return;}
        String toPrint = "Students [";
        for (int currPoz = 0; currPoz < auxStuffIdCnt; ++currPoz) {
            toPrint += "\n\t " + auxiliaryStuffs[currPoz];
        }
        toPrint += "\n]";
        System.out.println(toPrint);
    }

    @Override
    public String toString() {
        return "AuxiliaryStuff" + super.toString() +
                " has to do:" + jobDescription;
    }

    @Override
    protected String toCsv () {
        return super.toCsv() + ',' + jobDescription;
    }
}

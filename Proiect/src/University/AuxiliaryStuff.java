package University;

import People.Employee;

public class AuxiliaryStuff extends Employee {
    static private int auxStuffIdCnt;
    private String jobDescription;

    public AuxiliaryStuff(String cnp, String surname, String name, char gender, String phone, String mail, int salary, String job, String EmploymentDate, String jobDescription) {
        super(++auxStuffIdCnt, cnp, surname, name, gender, phone, mail, salary, job, EmploymentDate);
        this.jobDescription = jobDescription;
    }

    public String getJobDescription() {
        return jobDescription;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }

    @Override
    public String toString() {
        return "AuxiliaryStuff" + super.toString() +
                " has to do:" + jobDescription;
    }
}

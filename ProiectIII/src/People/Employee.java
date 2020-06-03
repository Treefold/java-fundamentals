package People;

public abstract class Employee extends Person {
    private int salary;
    private String job;
    private String employmentDate;
    private String unemploymentDate = "";

    protected Employee (String[] csvData) {
        super(csvData);
        salary = Integer.parseInt(csvData[7]);
        job = csvData[8];
        employmentDate = csvData[9];
        unemploymentDate = csvData[10];

    }

    public Employee(int id, String cnp, String surname, String name, char gender, String phone, String mail, int salary, String job, String employmentDate) {
        super(id, cnp, surname, name, gender, phone, mail);
        this.salary         = salary;
        this.job            = job;
        this.employmentDate = employmentDate;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
        updated();
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
        updated();
    }

    public String getEmploymentDate() {
        return employmentDate;
    }

    public void setEmploymentDate(String employmentDate) {
        this.employmentDate = employmentDate;
        updated();
    }

    public String getUnemploymentDate() {
        return unemploymentDate;
    }

    public void setUnemploymentDate(String unemploymentDate) {
        this.unemploymentDate = unemploymentDate;
        updated();
    }

    @Override
    public String toString() {
        return "{" + super.toString() +
                "} has salary $" + salary +
                " working as " + job +
                " from " + employmentDate +
                " to " + (unemploymentDate == "" ? "date" : unemploymentDate);
    }

    @Override
    protected String toCsv () {
        return super.toCsv() + ',' + salary + ',' + job + ',' + employmentDate + ',' + unemploymentDate;
    }
}

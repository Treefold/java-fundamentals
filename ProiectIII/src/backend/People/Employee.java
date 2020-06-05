package backend.People;

import backend.DB.DB;

import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class Employee extends Person {
    private int    salary;
    private String job;
    private String hire_date;

    protected Employee (ResultSet data) throws SQLException {
        super(data);
        if (data.getString("salary") != null) {
            salary = Integer.parseInt(data.getString("salary"));
        } else {salary  = 0;}
        job = data.getString("job");
        hire_date = data.getString("hire_date");
    }

    protected Employee(int id, String cnp, String surname, String name, String gender, String phone, String mail, int salary, String job, String hire_date) {
        super(id, cnp, surname, name, gender, phone, mail);
        this.salary         = salary;
        this.job            = job;
        this.hire_date = hire_date;
    }

    @Override
    abstract protected String getTableName ();

    @Override
    abstract protected String getIdName ();

    public int getSalary() {
        return salary;
    }

    public boolean setSalary(int salary) {
        if (DB.updateData(getTableName(), "salary", Integer.toString(salary), getIdName() + "=" + id)) {
            this.salary = salary;
            return true;
        } else {return false;}
    }

    public String getJob() {
        return job;
    }

    public boolean setJob(String job) {
        if (DB.updateData(getTableName(), "job", "'"+job+"'", getIdName() + "=" + id)) {
            this.job = job;
            return true;
        } else {return false;}
    }

    public String getHire_date() {
        return hire_date;
    }

    public boolean setHire_date(String hire_date) {
        if (DB.updateData(getTableName(), "hire_date", "'"+hire_date+"'", getIdName() + "=" + id)) {
            this.hire_date = hire_date;
            return true;
        } else {return false;}
    }

    @Override
    public String toString() {
        return "{" + super.toString() +
                "} has salary " + ((salary > 0) ? "$" + salary : "unknown") +
                " working as " + job +
                " from " + hire_date;
    }
}

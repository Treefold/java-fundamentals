package backend.People;

import backend.DB.DB;

import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class Person {
    protected int    id;
    protected String cnp;
    protected String surname;
    protected String name;
    protected String gender;
    protected String phone;
    protected String mail;

    protected Person (ResultSet data) throws SQLException {
        id      = Integer.parseInt(data.getString(getIdName()));
        cnp     = data.getString("cnp");
        surname = data.getString("surname");
        name    = data.getString("name");
        gender  = data.getString("gender");
        phone   = data.getString("phone");
        mail    = data.getString("mail");
    }

    public Person(int id, String cnp, String surname, String name, String gender, String phone, String mail) {
        this.id      = id;
        this.cnp     = cnp;
        this.surname = surname;
        this.name    = name;
        this.gender  = gender;
        this.phone   = phone;
        this.mail    = mail;
    }
    abstract protected String getTableName ();
    abstract protected String getIdName ();

    public int getId() {
        return id;
    }

    public boolean setId(int id) {
        if (DB.updateData(getTableName(), getIdName(), Integer.toString(id), getIdName() + "=" + id)) {
            this.id = id;
            return true;
        } else {return false;}
    }

    public String getCnp() {
        return cnp;
    }

    public boolean setCnp(String cnp) {
        if (DB.updateData(getTableName(),"cnp", "'"+cnp+"'", getIdName() + "=" + id)) {
            this.cnp = cnp;
            return true;
        } else {return false;}
    }

    public String getSurname() {
        return surname;
    }

    public boolean setSurname(String surname) {
        if (DB.updateData(getTableName(), "surname", "'"+surname+"'", getIdName() + "=" + id)) {
            this.surname = surname;
            return true;
        } else {return false;}
    }

    public String getName() {
        return name;
    }

    public boolean setName(String name) {
        if (DB.updateData(getTableName(), "name", "'"+name+"'", getIdName() + "=" + id)) {
            this.name = name;
            return true;
        } else {return false;}
    }

    public String getGender() {
        return gender;
    }

    public boolean setGender(String gender) {
        if (DB.updateData(getTableName(), "gender", "'"+gender+"'", getIdName() + "=" + id)) {
            this.gender = gender;
            return true;
        } else {return false;}
    }

    public String getPhone() {
        return phone;
    }

    public boolean setPhone(String phone) {
        if (DB.updateData(getTableName(), "phone", "'"+phone+"'", getIdName() + "=" + id)) {
            this.phone = phone;
            return true;
        } else {return false;}
    }

    public String getMail() {
        return mail;
    }

    public boolean setMail(String mail) {
        if (DB.updateData(getTableName(), "mail", "'"+mail+"'", getIdName() + "=" + id)) {
            this.mail = mail;
            return true;
        } else {return false;}
    }

    @Override
    public String toString() {
        return  "Id: "       + id +
                ", CNP: "    + cnp +
                ", Name: "   + surname + ' ' + name +
                ", Gender: " + gender +
                ", Phone: "  + phone +
                ", mail: "   + mail;
    }
}


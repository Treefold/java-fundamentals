package People;

import MyLog.Table;

public abstract class Person extends Table {
    protected int    id;
    protected String cnp;
    protected String surname;
    protected String name;
    protected char   gender;
    protected String phone;
    protected String mail;

    protected Person (String[] csvData) {
        id      = Integer.parseInt(csvData[0]);
        cnp     = csvData[1];
        surname = csvData[2];
        name    = csvData[3];
        gender  = csvData[4].charAt(0);
        phone   = csvData[5];
        mail    = csvData[6];
    }

    public Person(int id, String cnp, String surname, String name, char gender, String phone, String mail) {
        this.id      = id;
        this.cnp     = cnp;
        this.surname = surname;
        this.name    = name;
        this.gender  = gender;
        this.phone   = phone;
        this.mail    = mail;
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

    public void setSurname(String surname) {
        this.surname = surname;
        updated();
    }

    public void setName(String name) {
        this.name = name;
        updated();

    }

    public void setPhone(String phone) {
        this.phone = phone;
        updated();
    }

    public void setMail(String mail) {
        this.mail = mail;
        updated();
    }

    public int getId() {
        return id;
    }

    public String getCnp() {
        return cnp;
    }

    public String getSurname() {
        return surname;
    }

    public String getName() {
        return name;
    }

    public char getGender() {
        return gender;
    }

    public String getPhone() {
        return phone;
    }

    public String getMail() {
        return mail;
    }

    @Override
    protected String toCsv () {
        return id + "," + cnp + "," + surname + "," + name + "," + gender + "," + phone + "," + mail;
    }
}


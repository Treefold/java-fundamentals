package People;

public abstract class Person {
    protected int    id;
    protected String cnp;
    protected String surname;
    protected String name;
    protected char   gender;
    protected String phone;
    protected String mail;

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
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setMail(String mail) {
        this.mail = mail;
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
}


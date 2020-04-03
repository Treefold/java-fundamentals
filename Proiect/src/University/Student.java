package University;

import People.Person;

public class Student extends Person {
    static private int studentsIdCnt = 0;
    private ClassOfStudents cls; // should be changed only from ClassOfStudents class
    // TODO: record marks

    public Student(String name) { // testing purpose
        super(++studentsIdCnt, "", "", name, '\0', "", "");
    }

    public Student(String cnp, String surname, String name, char gender, String phone, String mail) {
        super(++studentsIdCnt, cnp, surname, name, gender, phone, mail);
    }

    public ClassOfStudents getCls() {
        return cls;
    }

    public void setCls(ClassOfStudents cls) {
        this.cls = cls;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student)) return false;
        Student student = (Student) o;
        return this.id == student.id;
    }

    @Override
    public String toString() {
        return "Student{" + super.toString() + "}";
    }

    // 0 if equal, -1 if student1 < student2; +1 if student1 > student2
    static public int CompareByName (Student student1, Student student2) {
        if (student1.equals(student2)) {return 0;}
        int rez = student1.getSurname().compareToIgnoreCase(student2.getSurname());
        if (rez != 0) {return rez < 0 ? -1 : 1;}
        rez = student1.getName().compareToIgnoreCase(student2.getName());
        if (rez != 0) {return rez < 0 ? -1 : 1;}
        return student1.getId() < student2.getId() ? -1 : 1;
    }
}

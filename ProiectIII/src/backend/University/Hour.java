package backend.University;

public class Hour {
    /*
        All hours are:
        - weekly
        - is starts at X:00 and ends at (X+1):50 with a break form X:50 to (X+1):00; where X can be (8, 10, 12, 14, 16, 18)
        - with a hole class
        - for courses multiple classes will have different hours with the same teacher and place
        TODO: differentiate courses(allow multiple classes), labs(allow halves of classes) and seminars
     */
    static private int hourIdCnt = 0;
    static private String[] weekday = {"Mon", "Tue", "Wed", "Thu", "Fri"};
    private int     id;
    private int     day;
    private int     beginsAt;
    private String  place;
    private Teacher teacher;
    private ClassOfStudents classOfStudents;

    // all checks shall be done with proper error handling
    public Hour(int day, int beginsAt, String place, Teacher teacher, ClassOfStudents classOfStudents) {
        this.id       = ++hourIdCnt;
        this.day      = day;      // TODO: check day (0-4 : Mon-Fri)
        this.beginsAt = beginsAt; // TODO: check the starting time (0-5 -> {8, 10, 12, 14, 16, 18})
        this.place    = place;
        this.teacher  = teacher;  // TODO: check not null
        this.classOfStudents = classOfStudents; // TODO: check not null
    }

    static public void createHour(int day, int beginsAt, String place, Teacher teacher, ClassOfStudents classOfStudents) {
        if (teacher == null || classOfStudents == null) {return;} // not a valid hour
        Hour hour = new Hour (day, beginsAt, place, teacher, classOfStudents);
        teacher.addHour(hour);
        classOfStudents.addHour(hour);
    }

    static public void deleteHour(Hour hour) {
        if (hour == null) {return;}
        if (hour.teacher != null) {hour.teacher.clearHour(hour.day, hour.beginsAt);}
        if (hour.classOfStudents != null) {hour.classOfStudents.clearHour(hour.day, hour.beginsAt);}
    }

    public int getId() {
        return id;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getBeginsAt() {
        return beginsAt;
    }

    public void setBeginsAt(int beginsAt) {
        this.beginsAt = beginsAt;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public ClassOfStudents getClassOfStudents() {
        return classOfStudents;
    }

    public void setClassOfStudents(ClassOfStudents classOfStudents) {
        this.classOfStudents = classOfStudents;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Hour)) return false;
        Hour hour = (Hour) o;
        return this.id == hour.id;
    }

    @Override
    public String toString() {
        return "Hour{" +
                "Id="         + id +
                ", day="      + weekday[day] +
                ", beginsAt=" + beginsAt +
                ", place="    + place +
                ", teacher="  + teacher.getId() +
                ", class="    + classOfStudents.getId() +
                '}';
    }
}

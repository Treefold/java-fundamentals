package Ex2;

import java.util.Comparator;

public class CmpStudents implements Comparator<Student> {
    @Override
    public int compare(Student s1, Student s2) {
        int rez = s2.getNume().compareToIgnoreCase(s1.getNume());
        if (rez == 0) {
            double rezM = s2.getMedie() - s1.getMedie();
            return (rezM < 0) ? -1 : (rezM > 0) ? 1 : 0;
        }
        return rez;
    }
}

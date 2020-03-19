package asociere;

import java.lang.reflect.Array;
import java.util.Arrays;

public class Universitate {

    private String nume;
    private Departament[] departamente; // has-to-has-a

    public Universitate (String nume, Departament[] departamente) {
        this.nume = nume;
        this.departamente = Arrays.copyOf(departamente, departamente.length);
    }

    @Override
    public String toString() {
        return "Universitate{nume=" + nume + ", Departamente=" + Arrays.toString(departamente) + "}";
    }
}

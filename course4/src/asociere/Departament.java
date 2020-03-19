package asociere;

import java.util.Arrays;

public class Departament {
    private String nume;
    private Profesor[] profesori; // has-a

    public Departament(String nume) {
        this.nume = nume;
    }

    public void setProfesori(Profesor[] profesori) {
        this.profesori = profesori;
    }

    public Profesor[] getProfesori() {
        return profesori;
    }

    @Override
    public String toString() {
        return "Departament{" +
                "nume='" + nume + '\'' +
                ", profesori=" + Arrays.toString(profesori) +
                '}';
    }
}

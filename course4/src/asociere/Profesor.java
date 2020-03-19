package asociere;

public class Profesor {
    int id;
    String nume;

    @Override
    public String toString() {
        return "Profesor{" +
                "id=" + id +
                ", nume='" + nume + '\'' +
                '}';
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public Profesor(int id, String nume) {
        this.id = id;
        this.nume = nume;
    }
}

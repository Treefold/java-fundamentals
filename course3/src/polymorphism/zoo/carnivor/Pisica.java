package polymorphism.zoo.carnivor;

import polymorphism.zoo.Animal;

public class Pisica extends Carnivor {

    public Pisica(String nume, int varsta) {
        super(nume, varsta);
        this.sunetSpecific = "mianune";
    }

    public void scoateSunet() {
        System.out.println ("Pisica " + this.sunetSpecific);
    }

    public String toString() {
        return  super.toString() + "Pisica{" +
                "sunetSpecific='" + sunetSpecific + "'" +
                "}";
    }

    public boolean equals (Object obj) {
        if (obj instanceof Pisica) {
            Pisica pisica = (Pisica) obj;

        }
        return false;
    }
}

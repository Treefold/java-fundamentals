package polymorphism.zoo.omnivor;

import polymorphism.zoo.Animal;

public abstract class Omnivor extends Animal {

    public Omnivor(String nume, int varsta) {
        super(nume, varsta);
        this.tipHrana = "carne & vegetatie";
    }

    public void seHraneste() {
        System.out.println (this + "se hraneste cu " + this.tipHrana);
    }
}

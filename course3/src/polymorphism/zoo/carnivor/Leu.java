package polymorphism.zoo.carnivor;

public class Leu extends Carnivor {

    public Leu(String nume, int varsta) {
        super(nume, varsta);
        this.sunetSpecific = "rage";
    }

    public void scoateSunet() {
        System.out.println ("Leu " + this.sunetSpecific);
    }
}

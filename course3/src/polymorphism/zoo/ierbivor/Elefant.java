package polymorphism.zoo.ierbivor;

public class Elefant extends Ierbivor{

    public Elefant(String nume, int varsta) {
        super(nume, varsta);
        this.sunetSpecific = "trambiteze";
    }

    public void scoateSunet() {
        System.out.println ("Elefant " + this.sunetSpecific);
    }
}

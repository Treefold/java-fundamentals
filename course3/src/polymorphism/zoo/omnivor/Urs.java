package polymorphism.zoo.omnivor;

public class Urs extends Omnivor {

    public Urs(String nume, int varsta) {
        super(nume, varsta);
        this.sunetSpecific = "mormaie";
    }

    public void scoateSunet() {
        System.out.println ("Urs " + this.sunetSpecific);
    }
}

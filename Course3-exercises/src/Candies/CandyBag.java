package Candies;
// d
public class CandyBag {
    private int bagSize;
    private int quanitityInside;
    private CandyBox[] bag;

    public CandyBag(int bagSize) {
        this.quanitityInside = 0;
        if (bagSize > 0) {
            this.bagSize = bagSize;
            bag = new CandyBox[bagSize];
        }
        else {
            this.bagSize = 0;
        }
    }

    public void addCandy (CandyBox box) {
        for (int i = 0; i < quanitityInside; ++i) {
            if (bag[i].equals(box)) {
                System.out.println("Already in the bag: " + box);
                return;
            }
        }

        if (quanitityInside + 1 > bagSize) {
            System.out.println("The bag is full, couldn't add: " + box);
            return;
        }

        if (box instanceof Lindt) {
            System.out.println("Added: " + box);
            bag [quanitityInside++] = new Lindt((Lindt) box);
        }
        else if (box instanceof Milka) {
            System.out.println("Added: " + box);
            bag [quanitityInside++] = new Milka((Milka) box);
        }
        else if (box instanceof Heidi) {
            System.out.println("Added: " + box);
            bag [quanitityInside++] = new Heidi((Heidi) box);
        }
        else {System.out.println("Unknown candy");}
    }

    public void printInside () {
        if (quanitityInside == 0) {
            System.out.println("\nEmpty bag");
            return;
        }
        System.out.println("\nInside the bag:");
        for (int i = 0; i < quanitityInside; ++i) {
            System.out.println(bag[i]);
        }
        System.out.println("End of inside the bag");
    }
}

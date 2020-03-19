package Candies;

// b
public class Heidi extends CandyBox {
    private float length;
    public Heidi() {
        this.length = 0;
    }

    public Heidi(String flavor, String origin, float length) {
        super(flavor, origin);
        this.length = length;
    }

    @Override
    public float getVolume() {
        return this.length * this.length * this.length;
    }

    @Override
    public String toString() {
        return super.toString() + " has volume "+ this.getVolume();
    }

// d
    public Heidi (Heidi candy) {
        super (candy);
        this.length = candy.length;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Heidi)) return false;
        if (!super.equals(o)) return false;
        Heidi heidi = (Heidi) o;
        return Float.compare(heidi.length, length) == 0;
    }

// e
    @Override
    public void printDim() {
        System.out.println("Heidi{length: " + this.length + "}");
    }
}

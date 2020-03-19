package Candies;

// a
public abstract class CandyBox {
    private String flavor;
    private String origin;

    public CandyBox() {
        this.flavor = "";
        this.origin = "";
    }

    public CandyBox(String flavor, String origin) {
        this.flavor = flavor;
        this.origin = origin;
    }

    public float getVolume() {
        return 0;
    }

    @Override
    public String toString() {
        return "The " + origin + " " + flavor;
    }

// c
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CandyBox)) return false;
        CandyBox candyBox = (CandyBox) o;
        return flavor.equals(candyBox.flavor) &&
                origin.equals(candyBox.origin);
    }

// d

    public CandyBox (CandyBox box) {
        this (box.flavor, box.origin);
    }

// e
    public abstract void printDim ();
}

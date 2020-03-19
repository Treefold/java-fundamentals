package Candies;

// b
public class Lindt extends CandyBox {
    private float length;
    private float width;
    private float height;

    public Lindt() {
        this.length = 0;
        this.width  = 0;
        this.height = 0;
    }

    public Lindt(String flavor, String origin, float length, float width, float height) {
        super(flavor, origin);
        this.length = length;
        this.width = width;
        this.height = height;
    }

    @Override
    public float getVolume() {
        return this.length * this.width * this.height;
    }

    @Override
    public String toString() {
        return super.toString() + " has volume "+ this.getVolume();
    }

// d
    public Lindt (Lindt candy) {
        super (candy);
        this.length = candy.length;
        this.width  = candy.width;
        this.height = candy.height;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Lindt)) return false;
        if (!super.equals(o)) return false;
        Lindt lindt = (Lindt) o;
        return Float.compare(lindt.length, length) == 0 &&
                Float.compare(lindt.width, width) == 0 &&
                Float.compare(lindt.height, height) == 0;
    }

// e
    @Override
    public void printDim() {
        System.out.println("Lindt{length: " + this.length +
                               ", width: "  + this.width +
                               ", height: " + this.height + "}");
    }
}

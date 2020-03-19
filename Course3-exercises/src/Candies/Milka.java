package Candies;

// b
public class Milka extends CandyBox {
    private float height;
    private float radius;
    private static float pi = 3.14F;

    public Milka() {
        this.height = 0;
        this.radius  = 0;
    }

    public Milka(String flavor, String origin, float height, float radius) {
        super(flavor, origin);
        this.height = height;
        this.radius = radius;
    }

    @Override
    public float getVolume() {
        return this.pi * this.radius * this.radius * this.height;
    }

    @Override
    public String toString() {
        return super.toString() + " has volume "+ this.getVolume();
    }

// d
    public Milka (Milka candy) {
        super (candy);
        this.radius = candy.radius;
        this.height = candy.height;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Milka)) return false;
        if (!super.equals(o)) return false;
        Milka milka = (Milka) o;
        return Float.compare(milka.height, height) == 0 &&
                Float.compare(milka.radius, radius) == 0;
    }

// e
    @Override
    public void printDim() {
        System.out.println("Milka{radius: " + this.radius +
                               ", height: " + this.height + "}");
    }
}

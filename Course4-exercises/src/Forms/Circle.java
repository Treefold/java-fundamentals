package Forms;
// b
public class Circle extends Form {
    private float radius;
    static private float pi = 3.14F;

    public Circle() {
        radius = 0;
    }

    public Circle(String color, float radius) {
        super(color);
        this.radius = radius;
    }

    public Circle(Circle circle) {
        super((Form) circle);
        this.radius = circle.radius;
    }

    @Override
    public Form copyForm(Form form) {
        return new Circle((Circle) form);
    }
    // c
    @Override
    public float getArea() {
        return pi * radius * radius;
    }

    public  void printCircleDimensions() {
        System.out.println("{Radius: " + radius + "}");
    }

    @Override
    public void printDim() {
        printCircleDimensions();
    }

    // e
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Circle)) return false;
        if (!super.equals(o)) return false;
        Circle circle = (Circle) o;
        return Float.compare(circle.radius, radius) == 0;
    }

    // d
    @Override
    public String toString() {
        return "Circle: " + super.toString() + " " + getArea();
    }
}

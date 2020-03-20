package Forms;
// b
public class Triangle extends Form {
    private float height;
    private float base;

    public Triangle() {
        height = 0;
        base = 0;
    }

    public Triangle(String color, float height, float base) {
        super(color);
        this.height = height;
        this.base = base;
    }

    public Triangle(Triangle triangle) {
        super((Form) triangle);
        this.height = triangle.height;
        this.base = triangle.base;
    }

    @Override
    public Form copyForm(Form form) {
        return new Triangle((Triangle) form);
    }

    // c
    @Override
    public float getArea() {
        return height * base / 2;
    }
    public void printTriangleDimensions() {
        System.out.println("{Hight: " + height + ", Base: " + base + "}");
    }

    @Override
    public void printDim() {
        printTriangleDimensions();
    }

    // e
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Triangle)) return false;
        if (!super.equals(o)) return false;
        Triangle triangle = (Triangle) o;
        return Float.compare(triangle.height, height) == 0 &&
                Float.compare(triangle.base, base) == 0;
    }

    // d
    @Override
    public String toString() {
        return "Triangle: " + super.toString() + " " + getArea();
    }
}

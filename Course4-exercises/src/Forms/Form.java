package Forms;

import java.util.Objects;

// a
public abstract class Form {
    private String color;

    public Form() {
        color = "None";
    }

    public Form(String color) {
        this.color = color;
    }

    public Form(Form form) {
        this.color = form.color;
    }

    public abstract Form copyForm (Form form);
    public abstract float getArea();
    public abstract void printDim();

    // e
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Form)) return false;
        Form form = (Form) o;
        return Objects.equals(color, form.color);
    }

    //d
    @Override
    public String toString() {
        return color;
    }
}

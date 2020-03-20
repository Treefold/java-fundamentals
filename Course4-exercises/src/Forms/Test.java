package Forms;

import java.util.Random;

// f
public class Test {
    public static void main (String[] args) {
        Random random = new Random();
        Form[] forms = new Form[10];
        String[] colors = {"red", "blue", "black"};
        int r, i;
        for (i = 0; i < 5; ++i) {
            r  = random.nextInt(666013);
            forms[i] = (r%2 == 0) ? new Triangle(colors[r%3], random.nextFloat()*100, random.nextFloat() * 100):
                    new Circle(colors[r%3], random.nextFloat() * 100);
        }
        for (i = 0; i < 5; ++i) {
            forms[i+5] = forms[i].copyForm(forms[i]);
            System.out.println(forms[i]);
            forms[i].printDim(); // h
            // g
            if (forms[i] instanceof Triangle) {
                ((Triangle) forms[i]).printTriangleDimensions();
            }
            if (forms[i] instanceof Circle) {
                ((Circle) forms[i]).printCircleDimensions();
            }
        }
        for (i = 0; i < 10; ++i) {
            System.out.println(forms[i].equals(forms[0])); // only 2 of truth
        }
    }
}

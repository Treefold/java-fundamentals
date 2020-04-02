package exceptii.ex1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Ex3 {
    public static void main(String[] args) {
        try {
            String fileName = "test.txt";
            Scanner scanner = new Scanner(createFile (fileName));
            int a, b;
            a = scanner.nextInt();
            b = scanner.nextInt();
            scanner.close();
            double result = a / b;
            System.out.println(result);
        } catch (ArithmeticException | InputMismatchException | IOException e) {
            System.out.println(e);
        } finally {
            System.out.println("Out Main");
        }
    }

    static File createFile (String fileName) throws IOException {
        File file = new File(fileName);
        if (file.createNewFile()) {
            System.out.println("File created");
        } else {
            System.out.println("File existed");
        }
        return file;
    }
}

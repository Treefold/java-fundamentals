package Exceptions.Ex2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static final String BASE = "./src/Exceptions/Ex2/";
    public static void main(String[] args) throws RuntimeException {
        ArrayList<Integer> nums = new ArrayList<>();
        ArrayList<Double>  others = new ArrayList<>();
        Integer x = 0;
        Double  d = 0D;
        try (Scanner scanner = new Scanner(new File(BASE, "date.in"))) {
            System.out.println("Open");
            while (scanner.hasNext()) {
                if (scanner.hasNextLong()) {
                    x = scanner.nextInt();
                    System.out.println(x);
                    nums.add(x);
                }
                else if (scanner.hasNextDouble()) {
                    d = scanner.nextDouble();
                    System.out.println(d);
                    others.add(d);
                }
                else {
                    throw new RuntimeException("UnknowType");
                }
            }
            nums.sort(Integer::compareTo);
            others.sort(Double::compareTo);
            System.out.println(nums);
            System.out.println(others);
        } catch (FileNotFoundException e) {
            System.out.println("could not find specified file");
        } catch (IOException e) {
            System.out.println("cannot open the file");
        }
    }
}
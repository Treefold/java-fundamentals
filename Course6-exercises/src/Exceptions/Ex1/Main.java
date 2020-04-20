package Exceptions.Ex1;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Calculator calc = new CalculatorDouble();
        for (int i = 0; i < 6; ++i){
            try {
                switch (i) {
                    case 1:
                        calc.add(null, 10);
                        break;
                    case 2:
                        calc.add(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);
                        break;
                    case 3:
                        calc.add(Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY);
                        break;
                    case 4:
                        calc.divide(10.13, 0.0);
                        break;
                    case 5:
                        try (Scanner scanner = new Scanner(new File("./src/Exceptions/Ex1/ex1.in"))) {
                            int n = scanner.nextInt();
                            double[] arr = new double[n];
                            for (i = 0; i < n; ++i) {
                                arr[i] = scanner.nextDouble();
                            }
                            System.out.println(calc.average(arr));
                        }catch (IOException e) {
                            e.printStackTrace();
                        }
                        break;
                    default:
                        break;
                }
            } catch (MyExceptions e) {
                e.printStackTrace();
            }
        }
    }
}

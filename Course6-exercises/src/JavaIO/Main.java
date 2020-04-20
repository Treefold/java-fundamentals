package JavaIO;

import java.io.*;

public class Main {
    private static final String BASE = "./src/JavaIO/";
    private static void ex1 () {
        try (FileInputStream in = new FileInputStream(BASE + "necriptat.txt")) {
            int maxLen = 0;
            String maxLenCuv = "";
            // returneaza nr de octeti din fisier
            int size = in.available();
            byte[] buffer = new byte[size];
            in.read(buffer);
            String s = new String(buffer);
            String[] ignore = {".", ",", "?", "!", ";"};
            for (String c: ignore) {
                s = s.replace(c, "");
            }
            for (String i : s.split("[ |\n]")) {
                if (i.length() > maxLen) {
                    maxLen = i.length();
                    maxLenCuv = i;
                }
            }
            System.out.println("Cel mai lung cuvant este: " + maxLenCuv);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static void ex2 () {
        try (FileWriter fw = new FileWriter(BASE + "necriptat.txt", true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
            // nu stiu ce inseamna "continutul clasei curente Java" si nici nu am gasit pe net
            out.write("\n\"Glossa\" de Mihai Eminescu");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static void ex3 () {
        try (FileInputStream in = new FileInputStream(BASE + "necriptat.txt")) {
            int size = in.available();
            byte[] buffer = new byte[size];
            in.read(buffer);
            String[] arr = (new String (buffer)).split("\n");
            for (String s : arr) {
                System.out.println(s + "\n...");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static String ex4Converter (String txtInit, int key) {
        String alfaLower = "aăâbcdefghiîjklmnopqrsștțuvwxyz";
        String alfaUpper = alfaLower.toUpperCase();
        int alfaLen = alfaLower.length();
        key %= alfaLen;
        if (key < 0) {
            key += alfaLen;
        }
        key += alfaLen;
        String txtFin = "";
        int poz = -1;
        char c = '\0';
        for (int i = 0; i < txtInit.length(); ++i) {
            c = txtInit.charAt(i);
            poz = alfaLower.indexOf(c);
            if (poz != -1) {
                txtFin += alfaLower.charAt((poz+key)%alfaLen);
                continue;
            }
            poz = alfaUpper.indexOf(c);
            if (poz != -1) {
                txtFin += alfaUpper.charAt((poz+key)%alfaLen);
                continue;
            }
            txtFin += (char) c;
        }
        return txtFin;
    }
    private static void ex4Helper(int key, String inFileName, String outFileName) {
        try (FileInputStream in = new FileInputStream(BASE + inFileName);
             FileWriter fw = new FileWriter(BASE + outFileName, false);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
            int size = in.available();
            byte[] buffer = new byte[size];
            in.read(buffer);
            out.write(ex4Converter(new String (buffer), key));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
//        ex1();
//        ex2();
//        ex3();
        ex4(13);
    }
    public static void ex4 (int key) {
        ex4Helper( key, "necriptat.txt", "criptat.txt"); // a
        ex4Helper(-key, "criptat.txt",   "decriptat.txt"); // b
        try (FileInputStream inInit = new FileInputStream(BASE + "necriptat.txt");
             FileInputStream inFin  = new FileInputStream(BASE + "decriptat.txt")) {
            int sizeInit = inInit.available();
            byte[] bufferInit = new byte[sizeInit];
            inInit.read(bufferInit);
            int sizeFin = inFin.available();
            byte[] bufferFin = new byte[sizeFin];
            inFin.read(bufferFin);

            System.out.println("Are the files identical?\n" + (new String(bufferFin)).equals(new String(bufferInit)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

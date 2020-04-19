package streams;

import java.io.File;
import java.io.IOException;
import java.sql.SQLOutput;
import java.util.Arrays;

public class Ex1 {
    private static final String BASE_PATH = "./src/streams";

    public static void main(String[] args) {
        // get file separator
        System.out.println(System.getProperty("file.separator"));
        System.out.println(File.separator);

        // create directory
        File dir1 = new File (BASE_PATH, "/dir1");
        System.out.println(dir1.mkdir());

        File dir2 = new File (BASE_PATH, "a/b/c/dir2");
        System.out.println(dir2.mkdirs());

        // create new file
        File file1 = new File (BASE_PATH, "file1.txt");
        try {
            if (file1.createNewFile()) {
                System.out.println("file created");
            } else {
                System.out.println(file1.exists());
                System.out.println(file1 + "already existent");
            }
        } catch (IOException e) {
            System.out.println(e);
        }

        System.out.println(file1.getName());
        System.out.println(file1.length());
        System.out.println(file1.getAbsolutePath());

        File f1 = new File("./src/exceptii");
        System.out.println(Arrays.toString(f1.listFiles()));
    }
}

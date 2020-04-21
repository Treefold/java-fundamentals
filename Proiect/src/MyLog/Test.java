package MyLog;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Test {
    public static void main(String[] args) throws InterruptedException {

        ArrayList<List<String>> rows = new ArrayList<>();
        rows.add(Arrays.asList("Jean", "author", "Java", (new Timestamp(System.currentTimeMillis())).toString()));
        TimeUnit.SECONDS.sleep(1);
        rows.add(Arrays.asList("David", "editor", "Python", (new Timestamp(System.currentTimeMillis())).toString()));
        TimeUnit.SECONDS.sleep(1);
        rows.add(Arrays.asList("Scott", "editor", "Node.js", (new Timestamp(System.currentTimeMillis())).toString()));

        try(FileWriter csvWriter = new FileWriter("test.csv")) {
            csvWriter.append("Name");
            csvWriter.append(",");
            csvWriter.append("Role");
            csvWriter.append(",");
            csvWriter.append("Topic");
            csvWriter.append(",");
            csvWriter.append("Time");
            csvWriter.append("\n");

            for (List<String> rowData : rows) {
                csvWriter.append("\n" + String.join(",", rowData));
            }

            csvWriter.flush();
//            csvWriter.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}

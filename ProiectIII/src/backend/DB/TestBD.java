package backend.DB;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TestBD {
    public static void main(String[] args) {
        testRead();
        testDelete(); // 0 rows affected
        testRead();
        testUpdate(); // 0 rows affected
        testRead();
        testInsert(); // 1 row inserted: 100 Geometrie
        testRead();
        testUpdateSolo(); // 1 row updated: 100 Geometrie -> 100 Algebra2
        testRead();
        testUpdate(); // 1 row updated: 100 Algebra2 -> 101 Analiza2
        testRead();
        testDelete(); // 1 row deleted: 101 Analiza2
        testRead();

        DB.close();
    }

    public static void testDelete() {
        System.out.println(DB.deleteData("departments", "dept_id = 101"));
    }

    public static void testUpdateSolo () {
        System.out.println(DB.updateData("departments", "name", "'Algebra2'", "dept_id = 100"));
    }

    public static void testUpdate () {
        String[] cols = {"dept_id", "name"};
        String[] vals = {"101", "'Analiza2'"};
        System.out.println(DB.updateData("departments", cols, vals, "dept_id = 100"));
    }

    public static void testInsert () {
        String[] cols = {"dept_id", "name"};
        String[] vals = {"100", "'Geometrie'"};
        System.out.println(DB.insertData("departments", cols, vals));
    }

    public static void testRead () {
        ResultSet rsp = DB.fetchData("departments");
        if (rsp != null) {
            try {
                while (rsp.next()) {
                    System.out.println(rsp.getString("dept_id") + ' ' + rsp.getString("name"));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("No data available");
        }
    }
}


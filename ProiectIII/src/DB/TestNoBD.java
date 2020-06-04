package DB;

import java.sql.*;

public class TestNoBD {
    public static void main(String[] args) {
        String db_url      = "jdbc:oracle:thin:@localhost:1521:XE";
        String db_username = "system";
        String db_password = "md";

        Connection connection = null;
        Statement statement = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DriverManager.getConnection(db_url, db_username, db_password);
            System.out.println("Connected to the database");
            statement = connection.createStatement();
//            preparedStatement = connection.prepareStatement("select * from test");
        } catch (SQLException e) {
            System.out.println("Could not connect to the database");
            e.printStackTrace();
        }

        if (statement != null) {
            //String sql = "INSERT INTO TEST VALUES (10)";
            try {
                ResultSet rsp = statement.executeQuery("select student_id from students");
                while (rsp.next()) {
                    System.out.println(rsp.getString("student_id"));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (preparedStatement != null) {
            try {
                ResultSet rsp = preparedStatement.executeQuery();
                while (rsp.next()) {
                    System.out.println(rsp.getString("x"));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        try {
            if (statement != null) {
                statement.close();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
                System.out.println("Connection to the database closed");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


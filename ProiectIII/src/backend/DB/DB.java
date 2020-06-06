package backend.DB;

import java.sql.*;

// singleton for reading/writing in the database
public class DB {
    private static DB ourInstance = null;
    private Connection connection = null;

    private DB() {}

    private static DB getInstance() throws SQLException {
        if (ourInstance == null) {
            ourInstance = new DB();
            String db_url      = "jdbc:oracle:thin:@localhost:1521:XE";
            String db_username = "system";
            String db_password = "md";
            try {
                ourInstance.connection = DriverManager.getConnection(db_url, db_username, db_password);
                System.out.println("Connected to the database");
            } catch (SQLException e) {
                System.out.println("Failed to connect to the database");
                throw e;
            }
        }
        return ourInstance;
    }

    public static void close() {
        if (ourInstance != null && ourInstance.connection != null) {
            try {
                ourInstance.connection.close();
                System.out.println("Connection to the database closed");
            } catch (SQLException e) {
                System.out.println("Couldn't close the connection to the database");
            }
        } // else {System.out.println("Connection to the database closed");}
    }

    public static ResultSet fetchData (String tableName) {
        try {
            Statement statement = getInstance().connection.createStatement();
            String sql = "select * from " + tableName;
            return statement.executeQuery(sql);
        } catch (SQLException e) {
            System.out.println("Failed to fetch data from " + tableName);
            return null;
        } catch (NullPointerException e) {
            System.out.println("No database connetion");
            return null;
        }
    }

    public static boolean insertData (String tableName, String[] cols, String[] vals) {
        if (vals == null) {
            return true; // no values to insert
        }
        if (cols != null && cols.length != vals.length) {
            System.out.println("Different length for cols an vals");
            return false; // incorrect number of columns and values provided
        }
        try {
            Statement statement = getInstance().connection.createStatement();
            String sql = "insert into " + tableName;
            if (cols != null) {
                sql += "(" + String.join(", ", cols) + ")";
            }
            sql += " values (" + String.join(", ", vals) + ")";
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            System.out.println("Failed to insert into " + tableName);
            e.printStackTrace();
            return ourInstance.connection == null; // when connection is null -> save the changes locally
        } catch (NullPointerException e) {
            System.out.println("No database connetion");
        }
        return true;
    }

    public static boolean updateData (String tableName, String[] cols, String[] vals, String condition) {
        if (cols == null && vals == null) {
            return true; // both null, no data to update
        }
        if (cols == null || vals == null || cols.length != vals.length) {
            return false; // incorrect format
        }
        try {
            Statement statement = getInstance().connection.createStatement();
            String sql = "update " + tableName + " set";
            for (int i = 0; i < cols.length; ++i) {
                sql += " " + cols[i] + " = " + vals[i];
                if (i+1 < cols.length) {
                    sql += ",";
                }
            }
            if (condition != null) {
                sql += " where " + condition;
            }
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            System.out.println("Failed to update " + tableName);
            e.printStackTrace();
            return ourInstance.connection == null; // when connection is null -> save the changes locally
        } catch (NullPointerException e) {
            System.out.println("No database connetion");
        }
        return true;
    }

    // specific for only one column update
    public static boolean updateData (String tableName, String col, String val, String condition) {
        try {
            Statement statement = getInstance().connection.createStatement();
            String sql = "update " + tableName + " set " + col + " = " + val;
            if (condition != null) {
                sql += " where " + condition;
            }
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            System.out.println("Failed to update " + tableName);
            e.printStackTrace();
            return ourInstance.connection == null; // when connection is null -> save the changes locally
        } catch (NullPointerException e) {
            System.out.println("No database connetion");
        }
        return true;
    }

    public static boolean deleteData (String tableName, String condition) {
        try {
            Statement statement = getInstance().connection.createStatement();
            String sql = "delete from " + tableName;
            if (condition != null) {
                sql += " where " + condition;
            }
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            System.out.println("Failed to delete data from " + tableName);
            e.printStackTrace();
            return ourInstance.connection == null; // when connection is null -> save the changes locally
        } catch (NullPointerException e) {
            System.out.println("No database connetion");
        }
        return true;
    }
}

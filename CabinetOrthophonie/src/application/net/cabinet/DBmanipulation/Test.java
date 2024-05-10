package jdbc.test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Test {

    public static void main(String[] args) {
        try {
            // Establishing the database connection
            Connection con = DatabaseUtil.getConnection();
            if (con != null) {
                // Query execution
                try (Statement stmt = con.createStatement();
                     ResultSet rs = stmt.executeQuery("SELECT * FROM employees")) {
                    // Displaying results
                    DatabaseUtil.displayResultSet(rs);
                }
                // Closing the connection
                DatabaseUtil.closeConnection(con);
            } else {
                System.out.println("Failed to establish database connection.");
            }
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
        }
    }
}


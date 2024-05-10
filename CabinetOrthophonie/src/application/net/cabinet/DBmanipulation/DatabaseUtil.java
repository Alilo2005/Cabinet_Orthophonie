package jdbc.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseUtil {
	
	/**
	 *  The next constants may change according to the user DB
	 */
    private static final String DB_URL = "jdbc:mysql://127.0.0.1:3305/testdb";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "31415926";

    /**
     * Establishes a connection to the database.
     * 
     * @return Connection object representing the database connection
     * @throws SQLException if a database access error occurs or the JDBC driver
     *                      class is not found
     */
    static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        } catch (ClassNotFoundException e) {
            throw new SQLException("MySQL JDBC Driver not found", e);
        }
    }

    /**
     * Displays the contents of a ResultSet in a structured format.
     * 
     * @param rs ResultSet containing the query results
     * @throws SQLException if a database access error occurs
     */
    static void displayResultSet(ResultSet rs) throws SQLException {
        ResultSetMetaData metaData = rs.getMetaData();
        int columnCount = metaData.getColumnCount();
        
        // Print column headers
        for (int i = 1; i <= columnCount; i++) {
            System.out.print(metaData.getColumnName(i) + "\t");
        }
        System.out.println();
        
        // Print data rows
        while (rs.next()) {
            for (int i = 1; i <= columnCount; i++) {
                System.out.print(rs.getString(i) + "\t");
            }
            System.out.println();
        }
    }


    /**
     * Executes an update query (e.g., INSERT, UPDATE, DELETE) on the database.
     * 
     * @param con   Connection object representing the database connection
     * @param query SQL query to be executed
     * @return the number of rows affected by the query
     * @throws SQLException if a database access error occurs
     */
    static int executeUpdate(Connection con, String query) throws SQLException {
        try (Statement stmt = con.createStatement()) {
            return stmt.executeUpdate(query);
        }
    }

    /**
     * Closes the database connection.
     * 
     * @param con Connection object representing the database connection to be closed
     */
    static void closeConnection(Connection con) {
        if (con != null) {
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println("Failed to close connection: " + e.getMessage());
            }
        }
    }

    /**
     * Executes a SQL query and returns the ResultSet.
     * 
     * @param con   Connection object representing the database connection
     * @param query SQL query to be executed
     * @return ResultSet containing the query results
     * @throws SQLException if a database access error occurs
     */
    static ResultSet executeQuery(Connection con, String query) throws SQLException {
        Statement stmt = con.createStatement();
        return stmt.executeQuery(query);
    }

    /**
     * Checks if a table exists in the database.
     * 
     * @param con       Connection object representing the database connection
     * @param tableName name of the table to check
     * @return true if the table exists, false otherwise
     * @throws SQLException if a database access error occurs
     */
    static boolean tableExists(Connection con, String tableName) throws SQLException {
        java.sql.DatabaseMetaData metaData = con.getMetaData();
        try (ResultSet rs = metaData.getTables(null, null, tableName, null)) {
            return rs.next();
        }
    }
}

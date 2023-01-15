import java.sql.*;


public class RemoteDatabaseController {

    public static int totalUserNumber = 1;
    /**
     * This method is used to connect to a remote PostgreSQL database on my server.
     * It uses the
     *
     * @param SQLStatement to fetch data from the DB using the SQLStatement provided.
     */

    public void insertData(String SQLStatement) {
        String dbName = "whizartc_Info_III_conjugator_DB";
        String userName = "whizartc_marlinjai";
        String password = "C5D7X7iNun%!@MHqm";

        // The URL that will connect to the database.
        // You may need to change "localhost" to the name or IP address of your server.
        String url = "jdbc:postgresql://nl1-ts101.a2hosting.com/" + dbName;
        ResultSet rs = null;
        try {
            // Load the PostgreSQL JDBC driver
            Class.forName("org.postgresql.Driver");

            // Connect to the database
            Connection conn = DriverManager.getConnection(url, userName, password);
            System.out.println("connection established successfully");
            // Do something with the connection here...
            Statement stmt = conn.createStatement();

            // Execute a query
            String query = SQLStatement;

            rs = stmt.executeQuery(query);

            // Close the connection
            conn.close();
        } catch (ClassNotFoundException e) {
            // Could not find the database driver
        } catch (SQLException e) {
            // Could not connect to the database
        }
    }

    public ResultSet fetchData(String SQLStatement) {
        String dbName = "whizartc_Info_III_conjugator_DB";
        String userName = "whizartc_marlinjai";
        String password = "C5D7X7iNun%!@MHqm";

        // The URL that will connect to the database.
        // You may need to change "localhost" to the name or IP address of your server.
        String url = "jdbc:postgresql://nl1-ts101.a2hosting.com/" + dbName;
        ResultSet rs = null;
        try {
            // Load the PostgreSQL JDBC driver
            Class.forName("org.postgresql.Driver");

            // Connect to the database
            Connection conn = DriverManager.getConnection(url, userName, password);
            System.out.println("connection established successfully");
            // Do something with the connection here...
            Statement stmt = conn.createStatement();

            // Execute a query
            String query = SQLStatement;

            rs = stmt.executeQuery(query);

            // Close the connection
            conn.close();
        } catch (ClassNotFoundException e) {
            // Could not find the database driver
        } catch (SQLException e) {
            // Could not connect to the database
        }

        return rs;

    }

    public void insertNewUserToDB(String firstNameInit, String lastNameInit, String passwordInit, String emailInit, String salt) {

        String statement = "'"+totalUserNumber+"', "+"'"+emailInit+"', "+"'"+salt+"', "+"'"+passwordInit+"', "+"'"+firstNameInit+"', "+"'"+lastNameInit+"'";
        this.insertData(
                "insert into users.userdata values (\n" +
                        statement +
                        ");"
        );
        totalUserNumber++;
    }

    public void createInitialUserTable() {
        // Replace with your database name, username, and password
        String dbName = "whizartc_Info_III_conjugator_DB";
        String userName = "whizartc_marlinjai";
        String password = "C5D7X7iNun%!@MHqm";

        // The URL that will connect to the database.
        // You may need to change "localhost" to the name or IP address of your server.
        String url = "jdbc:postgresql://nl1-ts101.a2hosting.com/" + dbName;

        try {
            // Load the PostgreSQL JDBC driver
            Class.forName("org.postgresql.Driver");

            // Connect to the database
            Connection conn = DriverManager.getConnection(url, userName, password);
            System.out.println("connection established successfully");
            // Do something with the connection here...
            Statement stmt = conn.createStatement();

            // Execute a query
            String query = "create table users.userdata (\n" +
                    "userid int Primary Key,\n" +
                    "Email varchar(30),\n" +
                    "Salt varchar(25),\n" +
                    "password varchar(75),\n" +
                    "firstname varchar(30),\n" +
                    "lastname varchar(30)\n" +
                    "); ";

            ResultSet rs = stmt.executeQuery(query);

            // Close the connection
            conn.close();
        } catch (ClassNotFoundException e) {
            // Could not find the database driver
        } catch (SQLException e) {
            // Could not connect to the database
        }
    }

    public static void main(String[] args) {
        RemoteDatabaseController tableCreator = new RemoteDatabaseController();
        tableCreator.createInitialUserTable();
    }
}


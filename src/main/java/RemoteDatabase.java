import java.sql.*;


public class RemoteDatabase {
    /**
     * This method is used to connect to a remote PostgreSQL database on my server.
     * @param args
     */
        public static void main(String[] args) {
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
                        "username varchar(30),\n" +
                        "password varchar(25)\n" +
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
    }


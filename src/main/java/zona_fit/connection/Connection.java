package zona_fit.connection;

import java.sql.DriverManager;

public class Connection {
    public static java.sql.Connection getConnection(){
        java.sql.Connection connection = null;
        var database = "zona_fit_db";
        var url = "jdbc:mysql://localhost:3306/" + database;
        var user = "";
        var password = "";

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            System.out.println("Error to connect database.");
        }
        return connection;
    }

    public static void main(String[] args) {
        var connection = Connection.getConnection();
        if (connection != null)
            System.out.println("Connection success: " + connection);
        else
            System.out.println("Error to connect.");
    }
}

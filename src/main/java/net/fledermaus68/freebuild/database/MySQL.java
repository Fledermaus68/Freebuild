package net.fledermaus68.freebuild.database;


import java.sql.*;


public class MySQL {

    public static Connection con;
    String host;
    String name;
    String password;
    String database;

    public MySQL(String host, String user, String pw, String db) {
        this.host = host;
        this.name = user;
        this.password = pw;
        this.database = db;
    }

    public static ResultSet getResult(String query) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = MySQL.con.prepareStatement(query);
            rs = ps.executeQuery();
            return rs;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void connect() throws ClassNotFoundException {
        if (!isConnected()) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                con = DriverManager.getConnection("jdbc:mysql://" + host + ":3306/" + database + "?autoReconnect=true&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=Europe/Berlin&wait_timeout=999999&connect_timeout=86400", name, password);
                System.out.println("Successfully connected to the mysql database");
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("Error with the Database: " + e.getMessage());
            }
        }
    }

    public void close() {
        if (isConnected()) {
            try {
                con.close();
                System.out.println("Successfully disconnect from the mysql database");

            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("Error with the Database: " + e.getMessage());
            }
        }
    }

    public boolean isConnected() {
        return con != null;
    }

    public void update(String qry) {
        if (isConnected()) {
            try {
                con.createStatement().executeUpdate(qry);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public Connection getConnection() {
        return con;
    }

}

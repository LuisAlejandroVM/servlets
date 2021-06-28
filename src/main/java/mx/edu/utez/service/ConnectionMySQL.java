package mx.edu.utez.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionMySQL {
    public static Connection getConnection() throws SQLException {
        String host = "127.0.0.1";
        String port = "3306";
        String database = "mitiendita";
        String useSSL = "false";
        String timezone = "UTC";
        String url = String.format("jdbc:mysql://%s:%s/%s?useSSL=%s&serverTimezone=%s", host, port, database, useSSL, timezone);
        DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
        return DriverManager.getConnection(url, "root", "");
    }
}

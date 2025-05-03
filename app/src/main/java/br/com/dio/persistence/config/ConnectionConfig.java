package br.com.dio.persistence.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import lombok.NoArgsConstructor;
import lombok.experimental.var;

@NoArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public final class ConnectionConfig {
    public static Connection getConnection() throws SQLException{
        var url = "jdbc:mysql://localhost:3306/board";
        var user = "marcos";
        var pass= "123456";
        var connection = DriverManager.getConnection(url,user, pass);
        connection.setAutoCommit(false);
        return connection;
    }

}
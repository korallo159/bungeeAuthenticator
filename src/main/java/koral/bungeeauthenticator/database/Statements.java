package koral.bungeeauthenticator.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static koral.bungeeauthenticator.database.DatabaseConnection.hikari;

public class Statements {


    public static boolean isPremium(String name){
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Boolean premium = false;
        try{
            connection = hikari.getConnection();
            statement = connection.prepareStatement("SELECT PREMIUM FROM Players WHERE NICK=?");
            statement.setString(1, name);
            resultSet = statement.executeQuery();
            while(resultSet.next()){
                premium = resultSet.getBoolean("PREMIUM");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            closeAll(connection, statement, resultSet);
        }
        return premium;
    }

    public static boolean isRegistered(String name){
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Boolean registered = false;
        try{
            connection = hikari.getConnection();
            statement = connection.prepareStatement("SELECT REGISTERED FROM Players WHERE NICK=?");
            statement.setString(1, name);
            resultSet = statement.executeQuery();
            while(resultSet.next()){
                registered = resultSet.getBoolean("REGISTERED");
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        finally {
            closeAll(connection, statement, resultSet);
        }
        return registered;
    }

    public static void updatePremium(String name) {
        Connection connection = null;
        String update = "UPDATE Players SET PREMIUM=? WHERE NICK=?";
        PreparedStatement statement = null;
        try {
            connection = hikari.getConnection();
            statement = connection.prepareStatement("SELECT * FROM Players WHERE NICK=?");
            statement.setString(1, name);
            statement = connection.prepareStatement(update);
            statement.setBoolean(1, true);
            statement.setString(2, name);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll(connection, statement);
        }
    }

    public static void closeAll(AutoCloseable... toClose) {
        for (AutoCloseable closeable : toClose)
            if (closeable != null)
                try {
                    closeable.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
    }
}

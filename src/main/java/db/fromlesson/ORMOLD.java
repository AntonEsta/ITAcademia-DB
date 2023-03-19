package db.fromlesson;

import db.Config;

import java.sql.*;

public class ORMOLD {
    static Connection connection;

    private ORMOLD(){}

    public static Connection getConnection() throws SQLException {
        if(connection == null){
            String url = "jdbc:mysql://" + Config.SERVER + "/" + Config.DB;
            connection = DriverManager.getConnection(url,Config.LOGIN,Config.PASSWORD);
        }
        return connection;
    }

    public static ResultSet select(String sql) throws SQLException {
        PreparedStatement statement = getConnection().prepareStatement(sql);
        return statement.executeQuery();
    }

    public static int execute(String sql) throws SQLException {
        PreparedStatement statement = getConnection().prepareStatement(sql);
        return statement.executeUpdate();
    }
}

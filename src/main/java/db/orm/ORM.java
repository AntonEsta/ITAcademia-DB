package db.orm;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class ORM {
    static ORM object;
    static Connection connection;
    static Statement statement;

    private ORM() throws SQLException {
        if (connection == null) {
            String url = "jdbc:mysql://" + Config.SERVER + "/" + Config.DB;
            DriverManager.setLoginTimeout(10);
            connection = DriverManager.getConnection(url, Config.LOGIN, Config.PASSWORD);
            statement = connection.createStatement();
        }
    }

    public static ORM getInstance() throws SQLException {
        if (object == null) {
            object = new ORM();
        }
        return object;
    }

    public ResultSet select(String table, String[] fields, String where) throws SQLException {
        String selectFields = "";
        if (fields.length == 0) {
            selectFields += "*";
        } else {
            for (int i = 0; i < fields.length; i++) {
                selectFields += fields[i] + (i < fields.length - 1 ? "," : "");
            }
        }
        return connection.createStatement().executeQuery("SELECT " + selectFields + " FROM " + table + " " + where);
//        return statement.executeQuery("SELECT " + selectFields + " FROM " + table + " " + where);
    }
    
    public int insert(String table, HashMap<String, String> values) throws SQLException {
        String sql = "INSERT INTO " + table, columns = "", sqlValues = "";
        if (!values.isEmpty()) {
            var i = 0;
            for (var item : values.entrySet()) {
                columns += "`" + item.getKey() + "`" + (i < values.size() - 1 ? "," : "");
                sqlValues += "'" + item.getValue() + "'" + (i < values.size() - 1 ? "," : "");
                i++;
            }
            sql += "(" + columns + ") VALUES(" + sqlValues + ")";

        }
        System.out.println("ORM.insert sql = " + sql);
        return statement.executeUpdate(sql);
    }

    public int update(String table, HashMap<String,String> values, String where) throws SQLException {
        String sql = "UPDATE " + table + " SET ";
//        TABLE SET f=v,f2=v WHERE ... = ...";
        if (!values.isEmpty()) {
            int i = 1;
            for (Map.Entry<String, String> entry : values.entrySet()) {
                sql = sql.concat(entry.getKey()).concat(" = ")
                        .concat(entry.getValue())
                        .concat(i < values.size() ? "," : "");
                i++;
            }
            sql = sql.concat(" ").concat(where);
        } else {
            return 0;
        }
        return statement.executeUpdate(sql);
    }

}

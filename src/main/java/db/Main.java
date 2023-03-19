package db;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

public class Main {

    private Connection connection;
    private Statement statement;

    public static void main(String[] args) throws SQLException {
//        var valuesMap = new HashMap<String, String>();
//        valuesMap.put("price", "1250");
//        System.out.println(ORM.getInstance().update("goods", valuesMap, "good_id = 1"));
        System.out.println(CartTableGateway.addGood(1));
        System.out.println(CartTableGateway.addGood(3, 10));
        System.out.println(CartTableGateway.addGood(3, 3));
    }

}

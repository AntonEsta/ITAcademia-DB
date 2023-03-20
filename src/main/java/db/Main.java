package db;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {

    private Connection connection;
    private Statement statement;
    private static final BufferedReader reader = new BufferedReader(System.in);

    public static void main(String[] args) throws SQLException {
//        var valuesMap = new HashMap<String, String>();
//        valuesMap.put("price", "1250");
//        System.out.println(ORM.getInstance().update("goods", valuesMap, "good_id = 1"));

        showAllGoods();

        CartGateway.addGood(3);
//        System.out.println(CartGateway.addGood(1));
//        System.out.println(CartGateway.addGood(3, 10));
//        System.out.println(CartGateway.addGood(3, 3));
    }

    private static void showAllGoods() throws SQLException {
        var goods = GoodGateway.getAllGoods();
        for (Good good : goods) {
            System.out.printf("%d %s %d\n", good.getId(), good.getTitle(), good.getPrice());
        }
    }

}

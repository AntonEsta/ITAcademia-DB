package db;

import java.io.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {

    private Connection connection;
    private Statement statement;
    private static final BufferedReader scanner = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws SQLException, IOException {
//        var valuesMap = new HashMap<String, String>();
//        valuesMap.put("price", "1250");
//        System.out.println(ORM.getInstance().update("goods", valuesMap, "good_id = 1"));
        System.out.println("Перечень товаров:");
        showAllGoods();
        System.out.println("Укажите индекс товара для его добавления в корзину:");
        int goodIndex = Integer.parseInt(scanner.readLine());
        CartGateway.addGood(goodIndex);
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

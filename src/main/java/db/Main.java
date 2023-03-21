package db;

import db.gateways.CartGateway;
import db.gateways.GoodGateway;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;

public class Main {

    private static final BufferedReader scanner = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws SQLException, IOException {
        var cart = new Cart();

        System.out.println("Перечень товаров:");
        showAllGoods();

        System.out.println("Укажите индекс товара для его добавления в корзину:");
        int goodIndex = Integer.parseInt(scanner.readLine());
        CartGateway.addGood(goodIndex);

        System.out.println();
        System.out.println("Состав корзины:");
        cart.update();
        for (Cart.CartPosition position : cart.getPositions()) {
            System.out.printf("\n\tid: %d\n\ttitle: %s\n\tprice: %d\n\tinfo: %s\n\tcount: %d\n",
                    position.getGood().getId(),
                    position.getGood().getTitle(),
                    position.getGood().getPrice(),
                    position.getGood().getInfo(),
                    position.getCount());
        }
        System.out.println();
        System.out.printf("\tВсего наименований товаров: %d\n", cart.getPositions().length);
        System.out.printf("\tВсего товаров: %d\n", cart.getGoodCount());
        System.out.printf("\tОбщая стоимость: %d\n", cart.getSumPrice());
    }

    private static void showAllGoods() throws SQLException {
        var goods = GoodGateway.getAllGoods();
        for (Good good : goods) {
            System.out.printf("%d %s %d\n", good.getId(), good.getTitle(), good.getPrice());
        }
    }

}

package db.fromlesson;

import db.orm.Config;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;

public class MainOld {
    private Connection connection;
    private Statement statement;

    public MainOld() throws ClassNotFoundException, SQLException {
        String url = "jdbc:mysql://" + Config.SERVER + "/" + Config.DB;
        connection = DriverManager.getConnection(url,Config.LOGIN,Config.PASSWORD);
//        С помощью объекта connection можно создать объект класса Statement с помощью которого
//                можно создавать запросы к базе данных
        statement = connection.createStatement();
    }

    void showData(String table) throws SQLException {
        ResultSet rs = statement.executeQuery("select * from " + table);
        while (rs.next()){
            System.out.printf("Автомобиль %s стоит %d\n",
                    rs.getString("title"),
                    rs.getInt("price"));
        }
        rs.close();
    }

    /**
     * В этом методе выполняется обновление стоимости авто
     * @param title
     * @param price
     * @return
     */
    int updatePrice(String title,int price) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(
                "UPDATE goods SET price = ? where title = ?");
        preparedStatement.setInt(1,price);
        preparedStatement.setString(2,title);
        return preparedStatement.executeUpdate();

//        return statement.executeUpdate("UPDATE goods " +
//                "SET price = " + price + " WHERE title ='" + title + "'");
    }

    public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {
        MainOld main = new MainOld();
        main.showData("goods");
        System.out.printf("Введите название авто и новую цену через пробел");
        String info[] = (new BufferedReader(new InputStreamReader(System.in)).readLine()).split(" ");
        main.updatePrice(info[0], Integer.parseInt(info[1]));
        System.out.println("Информация о товарах после изменения стоимости");
        main.showData("goods");
        main.statement.close();
        main.connection.close();

    }
}

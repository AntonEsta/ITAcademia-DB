package db.gateways;

import db.Good;
import db.orm.ORM;
import lombok.NonNull;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class GoodGateway {

    private static final String TABLE_NAME = "goods";

    public static int addGood(@NonNull String title, int price, @NonNull String info) throws SQLException {
        var valuesMap = new HashMap<String, String>();
        valuesMap.put("title", title);
        valuesMap.put("price", String.valueOf(price));
        valuesMap.put("info", info);
        return ORM.getInstance().insert(TABLE_NAME, valuesMap);

    }

    public static Good[] getAllGoods() throws SQLException {
        var rs = ORM.getInstance().select(TABLE_NAME, new String[0], "");
        return getGoodsFromResultSet(rs);
    }


    public static Good findById(int goodId) throws SQLException {
        var rs = ORM.getInstance().select(TABLE_NAME, new String[0], "WHERE good_id =" + goodId);
        var goods = getGoodsFromResultSet(rs);
        if (goods.length > 0) {
            return goods[0];
        }
        return null;
    }

    private static Good[] getGoodsFromResultSet(@NonNull ResultSet rs) throws SQLException {
        var goods = new ArrayList<>();
        while (rs.next()) {
            goods.add(new Good(
                    rs.getInt("good_id"),
                    rs.getString("title"),
                    rs.getInt("price"),
                    rs.getString("info")));
        }
        return goods.toArray(new Good[0]);
    }
}

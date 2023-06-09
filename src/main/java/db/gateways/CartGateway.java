package db.gateways;

import db.Good;
import db.orm.ORM;
import lombok.NonNull;

import javax.xml.xpath.XPathExpressionException;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CartGateway {

    private static final String TABLE_NAME = "cart";

    public static int addGood(int goodId) throws SQLException {
        return addGood(goodId, 1);
    }

    public static int addGood(int goodId, int goodCount) throws SQLException {
        var valuesMap = new HashMap<String, String>(2);
        valuesMap.put("good_id", String.valueOf(goodId));
        valuesMap.put("count", "count+" + goodCount);
        int resCount = ORM.getInstance().update(TABLE_NAME, valuesMap, "WHERE good_id = " + goodId);
        if (resCount == 0) {
            valuesMap.remove("count");
            return ORM.getInstance().insert(TABLE_NAME, valuesMap);
        } else {
            return resCount;
        }
    }

    public static @NonNull Good[] getAllGoods() throws SQLException {
        var goodIds = ORM.getInstance().select(TABLE_NAME, new String[]{"good_id"}, "");
        List<Good> goods = new ArrayList<>();
        while (!goodIds.isClosed() && goodIds.next()) {
            goods.add(GoodGateway.findById(goodIds.getInt("good_id")));
        }
        return goods.toArray(new Good[0]);
    }


    public static int getGoodCountById(int goodId) throws SQLException {
        var rs = ORM.getInstance().select(TABLE_NAME, new String[]{"count"}, "WHERE good_id=" + goodId);
        rs.next();
        return rs.getInt("count");
    }
}

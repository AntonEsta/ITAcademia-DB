package db;

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
        var valuesMap = new HashMap<String,String>(2);
        valuesMap.put("good_id", String.valueOf(goodId));
        ResultSet rs = ORM.getInstance().select(TABLE_NAME, new String[]{"count"}, "WHERE good_id = " + goodId);
        if (rs.next()) {
            goodCount += rs.getInt("count");
        }
        if (goodCount > 1) {
            valuesMap.put("count", String.valueOf(goodCount));
            System.out.println("count = " + valuesMap.get("count"));
            return ORM.getInstance().update(TABLE_NAME, valuesMap, "WHERE good_id = " + goodId);
        }
        return ORM.getInstance().insert(TABLE_NAME, valuesMap);
    }

    public static Good[] getAllGoods() throws SQLException {
//        var goodIds = ORM.getInstance().select(TABLE_NAME, new String[]{"good_id"},"");
        var tabFields = new HashMap<String, String[]>();
        var goodIds = ORM.getInstance().join();
        List<Good> goods = new ArrayList<>();
        while (goodIds.next()) {
            goods.add(GoodGateway.findById(goodIds.getInt("good_id")));
        }

    }

}

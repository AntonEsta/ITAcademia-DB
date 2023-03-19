package db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class CartTableGateway {

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

}

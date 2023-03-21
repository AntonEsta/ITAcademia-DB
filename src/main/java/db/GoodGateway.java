package db;

import java.security.cert.CollectionCertStoreParameters;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class GoodGateway {

    private static final String TABLE_NAME = "goods";
    private static final String[] SELECT_ALL_COLUMNS = new String[]{"good_id","title","price","info","date_create"};

    public static int addGood(String title, int price, String info) throws SQLException {
        var valuesMap = new HashMap<String,String>();
        valuesMap.put("title", String.valueOf(title));
        valuesMap.put("price", String.valueOf(price));
        valuesMap.put("info", String.valueOf(info));
        return ORM.getInstance().insert(TABLE_NAME, valuesMap);

    }

    public static Good[] getAllGoods() throws SQLException {
        var rs = ORM.getInstance().select(TABLE_NAME, SELECT_ALL_COLUMNS, "");
        return getGoodsFromResultSet(rs);
    }


    public static Good findById(int goodId) throws SQLException {
        var rs = ORM.getInstance().select(TABLE_NAME, SELECT_ALL_COLUMNS, "WHERE good_id =" + goodId);
        var goods = getGoodsFromResultSet(rs);
        if (goods.length > 0) {
            return goods[0];
        }
        return null;

    }

    private static Good[] getGoodsFromResultSet(ResultSet rs) throws SQLException {
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

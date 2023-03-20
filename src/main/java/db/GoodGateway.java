package db;

import java.security.cert.CollectionCertStoreParameters;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class GoodGateway {

    private static final String TABLE_NAME = "goods";

//    public static int addGood(String title, int price, String info) throws SQLException {
//        return addGood(goodId, 1);
//    }
    public static int addGood(String title, int price, String info) throws SQLException {

        var valuesMap = new HashMap<String,String>();
        valuesMap.put("title", String.valueOf(title));
        valuesMap.put("price", String.valueOf(price));
        valuesMap.put("info", String.valueOf(info));
        return ORM.getInstance().insert(TABLE_NAME, valuesMap);

    }

    public static Good[] getAllGoods() throws SQLException {
        var rs = ORM.getInstance().select(TABLE_NAME, new String[]{"good_id","title","price","info","date_create"}, "");
        var goods = new ArrayList<Good>();
        while (rs.next()) {
            goods.add(new Good(rs.getInt("good_id"),
                    rs.getString("title"),
                    rs.getInt("price"),
                    rs.getString("info")));
        }
        return goods.toArray(new Good[0]);
    }



}

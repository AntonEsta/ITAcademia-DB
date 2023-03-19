package db.fromlesson;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DemoUseOrm {
    public static void main(String[] args) throws SQLException {
        ResultSet rs = ORMOLD.select("select * from goods");
        while (rs.next()){
            System.out.printf("Автомобиль %s стоит %d\n",
                    rs.getString("title"),
                    rs.getInt("price"));
        }
    }

}

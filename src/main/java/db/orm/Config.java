package db.orm;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.HashMap;

public class Config {

    private static Properties databaseProperties;

    class Properties {
        HashMap<String,String> properties;
        String fileName;
        void update() {

        }
    }

    private Config() {

    }

    public static Properties getDatabaseProperties() {
        if (databaseProperties == null) {
            databaseProperties = new Properties();
        }
        return databaseProperties;
    }

    public static final String DB = "shop"; // my DB name
//    public static final String DB = "lesson14"; // for all
    public static final String SERVER = "172.18.0.2"; // для docker
//    public static final String SERVER = "localhost"; // для localhost server
    public static final String LOGIN = "root";
    public static final String PASSWORD = "root";


    public


}

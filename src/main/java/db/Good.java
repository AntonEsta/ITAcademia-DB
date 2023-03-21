package db;

import lombok.Data;

@Data
public class Good {
    private final int id;
    private final String title;
    private final int price;
    private final String info;
}

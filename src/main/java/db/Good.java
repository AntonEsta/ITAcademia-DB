package db;

import java.time.LocalDateTime;

public class Good {

    private int id;
    private String title;
    private int price;
    private String info;
    //    private LocalDateTime createTime;


    public Good(int id, String title, int price, String info) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.info = info;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getPrice() {
        return price;
    }

    public String getInfo() {
        return info;
    }

    @Override
    public String toString() {
        return "Good{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", price=" + price +
                ", info='" + info + '\'' +
                '}';
    }
}

package cz.borcizfitu.hackbest.domain.model;

import java.io.Serializable;

/**
 * HackerNews Item.
 * Created by Jan Stanek[st.honza@gmail.com] on {12.11.16}
 **/
public class Item implements Serializable{
    public static final String TAG = Item.class.getName();

    private String name;
    private String url;
    private long expired;
    private String author;
    private int type;

    public Item(String name, String url, long expired, String author, int type) {
        this.name = name;
        this.url = url;
        this.expired = expired;
        this.author = author;
        this.type = type;
    }

    public Item() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public long getExpired() {
        return expired;
    }

    public void setExpired(long expired) {
        this.expired = expired;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}

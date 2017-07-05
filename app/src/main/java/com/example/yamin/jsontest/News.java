package com.example.yamin.jsontest;

/**
 * Created by yamin on 2017/5/28.
 */

public class News {

    private String title;

    private String url;

    private String data;

    private  String author;

    private String imageUrl;

    public News(String title , String data , String url,String author,String imageUrl){
        this.title = title;
        this.data = data;
        this.url = url;
        this.author = author;
        this.imageUrl = imageUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getAuthor() {
        return author;
    }

    public String getUrl() {
        return url;
    }

    public String getTitle() {
        return title;
    }

    public String getData() {
        return data;
    }
}

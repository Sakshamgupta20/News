package com.example.android.news;

/**
 * Created by Saksham on 03-01-2018.
 */

public class word {
    private String Title;
    private String Author;
    private String Description;
    private String Time;
    private String murl;
    private String imageurl;

    public word(String title, String author, String description, String time,String url,String img) {
        Title = title;
        Author = author;
        Description = description;
        Time = time;
        murl=url;
        imageurl=img;
    }

    public String getTitle() {
        return Title;
    }

    public String getAuthor() {
        return Author;
    }

    public String getDescription() {
        return Description;
    }

    public String getTime() {
        return Time;
    }
    public String getMurl() {
        return murl;
    }

    public String getImageurl() {
        return imageurl;
    }
}

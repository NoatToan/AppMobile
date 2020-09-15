package com.example.rssfeed;


import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class Category {
    private String name;
    private String urlRss;

    public Category(String name, String urlRss) {
        this.name = name;
        this.urlRss = urlRss;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getUrlRss() {
        return urlRss;
    }

    public void setUrlRss(String urlRss) {
        this.urlRss = urlRss;
    }

}

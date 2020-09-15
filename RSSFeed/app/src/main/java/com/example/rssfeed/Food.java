package com.example.rssfeed;

import android.os.Parcel;
import android.os.Parcelable;

public class Food  {

    private String img;
    private String name;
    private String des;
    private String link;
    private String pubDate;


    public Food(String name, String des, String link, String img,String pubDate) {
        this.name = name;
        this.des = des;
        this.link = link;
        this.img= img;
        this.pubDate=pubDate;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }
}

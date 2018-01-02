package com.example.dylan.module.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by dylan on 2017/8/19.
 */
@Entity
public class Pictures {
    @Id
    private long id;

    private String pictureUrl;

    @Generated(hash = 1567207527)
    public Pictures(long id, String pictureUrl) {
        this.id = id;
        this.pictureUrl = pictureUrl;
    }

    @Generated(hash = 868286023)
    public Pictures() {
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPictureUrl() {
        return this.pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }
}

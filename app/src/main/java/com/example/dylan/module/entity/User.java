package com.example.dylan.module.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;


/**
 * Created by dylan on 2017/8/19.
 */
@Entity
public class User {
    @Id
    private long id;

    private String name;

    private int age;

    private long pictureId;
    
    @Generated(hash = 2010354198)
    public User(long id, String name, int age, long pictureId) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.pictureId = pictureId;
    }

    @Generated(hash = 586692638)
    public User() {
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public long getPictureId() {
        return this.pictureId;
    }

    public void setPictureId(long pictureId) {
        this.pictureId = pictureId;
    }
}

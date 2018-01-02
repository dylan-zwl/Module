package com.example.dylan.module.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.ToOne;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.annotation.NotNull;

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

    @ToOne(joinProperty = "pictureId")
    private Pictures pictures;

    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    @Generated(hash = 1507654846)
    private transient UserDao myDao;

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

    @Generated(hash = 246062294)
    private transient Long pictures__resolvedKey;

    /** To-one relationship, resolved on first access. */
    @Generated(hash = 756440779)
    public Pictures getPictures() {
        long __key = this.pictureId;
        if (pictures__resolvedKey == null || !pictures__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            PicturesDao targetDao = daoSession.getPicturesDao();
            Pictures picturesNew = targetDao.load(__key);
            synchronized (this) {
                pictures = picturesNew;
                pictures__resolvedKey = __key;
            }
        }
        return pictures;
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 1933514914)
    public void setPictures(@NotNull Pictures pictures) {
        if (pictures == null) {
            throw new DaoException(
                    "To-one property 'pictureId' has not-null constraint; cannot set to-one to null");
        }
        synchronized (this) {
            this.pictures = pictures;
            pictureId = pictures.getId();
            pictures__resolvedKey = pictureId;
        }
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#delete(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 128553479)
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.delete(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#refresh(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 1942392019)
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.refresh(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#update(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 713229351)
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.update(this);
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 2059241980)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getUserDao() : null;
    }
}

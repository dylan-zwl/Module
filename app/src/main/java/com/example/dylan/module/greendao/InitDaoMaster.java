package com.example.dylan.module.greendao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by dylan on 2017/8/15.
 */
@Entity
public class InitDaoMaster {
    @Id
    private Long id;

    @Generated(hash = 1287272481)
    public InitDaoMaster(Long id) {
        this.id = id;
    }

    @Generated(hash = 904018014)
    public InitDaoMaster() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
package com.example.dylan.module;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.dylan.module.entity.Pictures;
import com.example.dylan.module.entity.User;
import com.example.dylan.module.greendao.AbstractDatabaseManager;

import org.greenrobot.greendao.AbstractDao;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AbstractDatabaseManager.initOpenHelper(this);

        User user = new User();
        user.setName("zwl");
        user.setAge(18);
        user.setPictureId(1);

        Pictures picture = new Pictures();
        picture.setPictureUrl("####");
        user.setPictures(picture);

        UserManager userManager = new UserManager();
        userManager.insert(user);
        PictureManager pictureManager = new PictureManager();
        pictureManager.insert(picture);

        List<User> userList = userManager.loadAll();
        if (userList != null) {
            for (User userTemp : userList) {
                Log.d("db data", "" + userTemp.getId() + " " + userTemp.getName() + " " + userTemp.getAge() + " " +
                        userTemp.getPictures().getPictureUrl());
            }
        }
    }

    public class UserManager extends AbstractDatabaseManager<User, Long> {

        @Override
        public AbstractDao<User, Long> getAbstractDao() {
            return mDaoSession.getUserDao();
        }
    }

    public class PictureManager extends AbstractDatabaseManager<Pictures, Long> {

        @Override
        public AbstractDao<Pictures, Long> getAbstractDao() {
            return mDaoSession.getPicturesDao();
        }
    }

}

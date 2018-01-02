package com.example.dylan.module;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
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

        //        AbstractDatabaseManager.initOpenHelper(this,"dfa.db");

        User user = new User();
        user.setName("zwl");
        user.setAge(18);
        user.setPictureId(1);

        Pictures picture = new Pictures();
        picture.setPictureUrl("####");

        UserManager userManager = new UserManager(this);
        userManager.insert(user);
        PictureManager pictureManager = new PictureManager(this);
        pictureManager.insert(picture);

        List<User> userList = userManager.loadAll();
        if (userList != null) {
            for (User userTemp : userList) {
                Log.d("db data", "" + userTemp.getId() + " " + userTemp.getName() + " " + userTemp.getAge());
            }
        }
    }

    public class UserManager extends AbstractDatabaseManager<User, Long> {

        public UserManager(@NonNull Context context) {
            super(context);
        }

        @Override
        public AbstractDao<User, Long> getAbstractDao() {
            return mDaoSession.getUserDao();
        }

        @Override
        public String getDatabaseName() {
            return null;
        }
    }

    public class PictureManager extends AbstractDatabaseManager<Pictures, Long> {

        public PictureManager(@NonNull Context context) {
            super(context);
        }

        @Override
        public AbstractDao<Pictures, Long> getAbstractDao() {
            return mDaoSession.getPicturesDao();
        }

        @Override
        public String getDatabaseName() {
            return null;
        }
    }

}

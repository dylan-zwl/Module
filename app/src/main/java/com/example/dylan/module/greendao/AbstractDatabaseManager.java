package com.example.dylan.module.greendao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.example.dylan.module.entity.DaoMaster;
import com.example.dylan.module.entity.DaoSession;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.query.Query;
import org.greenrobot.greendao.query.QueryBuilder;

import java.util.Collection;
import java.util.List;

/**
 * Created by dylan on 2017/8/15.
 */

public abstract class AbstractDatabaseManager<M, K> implements IDatabase<M, K> {
    private static final String DEFAULT_DATABASE_NAME = "default.db";
    /**
     * The Android Activity reference for access to DatabaseManager.
     */
    private static DaoMaster.DevOpenHelper mHelper;
    protected static DaoSession mDaoSession;

    public static void initOpenHelper(@NonNull Context context) {
        mHelper = getOpenHelper(context, DEFAULT_DATABASE_NAME);
        openWriteableDb();
    }

    public static void initOpenHelper(@NonNull Context context, @NonNull String dataBaseName) {
        mHelper = getOpenHelper(context, dataBaseName);
        openWriteableDb();
    }

    private static DaoMaster.DevOpenHelper getOpenHelper(@NonNull Context context, @NonNull String dataBaseName) {
        closeDbConnections();
        return new DaoMaster.DevOpenHelper(context, dataBaseName, null);
    }

    private static void closeDbConnections() {
        if (mHelper != null) {
            mHelper.close();
            mHelper = null;
        }

        if (mDaoSession != null) {
            mDaoSession.clear();
            mDaoSession = null;
        }
    }

    private static SQLiteDatabase getReadableDataBase() {
        return mHelper.getReadableDatabase();
    }

    protected static void openReadableDb() {
        mDaoSession = new DaoMaster(getReadableDataBase()).newSession();
    }

    private static SQLiteDatabase getWriteableDataBase() {
        return mHelper.getWritableDatabase();
    }

    protected static void openWriteableDb() {
        mDaoSession = new DaoMaster(getWriteableDataBase()).newSession();
    }

    @Override
    public boolean insert(M m) {
        try {
            if (m != null) {
                openWriteableDb();
                getAbstractDao().insert(m);
                return true;
            }
        } catch (SQLiteException e) {
        }
        return false;
    }

    @Override
    public boolean insertList(List<M> list) {
        try {
            if (list != null && list.size() > 0) {
                openWriteableDb();
                getAbstractDao().insertInTx(list);
                return true;
            }
        } catch (SQLiteException e) {
        }
        return false;
    }

    @Override
    public boolean insertOrReplace(@NonNull M m) {
        try {
            if (m != null) {
                openWriteableDb();
                getAbstractDao().insertOrReplace(m);
                return true;
            }
        } catch (SQLiteException e) {
        }
        return false;
    }

    @Override
    public boolean insertOrReplaceList(List<M> list) {
        try {
            if (list != null && list.size() > 0) {
                openWriteableDb();
                getAbstractDao().insertOrReplaceInTx(list);
                return true;
            }
        } catch (SQLiteException e) {
        }
        return false;
    }

    @Override
    public boolean delete(M m) {
        try {
            if (m != null) {
                openWriteableDb();
                getAbstractDao().delete(m);
                return true;
            }
        } catch (SQLiteException e) {
        }
        return false;
    }

    @Override
    public boolean deleteByKey(K k) {
        try {
            if (TextUtils.isEmpty(k.toString())) {
                openWriteableDb();
                getAbstractDao().deleteByKey(k);
                return true;
            }
        } catch (SQLiteException e) {
        }
        return false;
    }

    @Override
    public boolean deleteList(List<M> list) {
        try {
            if (list != null && list.size() > 0) {
                openWriteableDb();
                getAbstractDao().deleteInTx(list);
                return true;
            }
        } catch (SQLiteException e) {
        }
        return false;
    }

    @Override
    public boolean deleteAll() {
        try {
            openWriteableDb();
            getAbstractDao().deleteAll();
            return true;
        } catch (SQLiteException e) {
        }
        return false;
    }

    @Override
    public boolean update(M m) {
        try {
            if (m != null) {
                openWriteableDb();
                getAbstractDao().update(m);
                return true;
            }
        } catch (SQLiteException e) {
        }
        return false;
    }

    @Override
    public boolean updateList(List<M> list) {
        try {
            if (list != null && list.size() > 0) {
                openWriteableDb();
                getAbstractDao().updateInTx(list);
                return true;
            }
        } catch (SQLiteException e) {
        }
        return false;
    }

    @Override
    public M load(K key) {
        try {
            openReadableDb();
            return getAbstractDao().load(key);
        } catch (SQLiteException e) {
        }
        return null;
    }

    @Override
    public List<M> loadAll() {
        try {
            openReadableDb();
            return getAbstractDao().loadAll();
        } catch (SQLiteException e) {
        }
        return null;
    }

    @Override
    public List<M> queryRaw(String where, String... selectionArg) {
        try {
            openReadableDb();
            return getAbstractDao().queryRaw(where, selectionArg);
        } catch (SQLiteException e) {
        }
        return null;
    }

    @Override
    public Query<M> queryRawCreate(String where, Object... selectionArg) {
        try {
            openReadableDb();
            return getAbstractDao().queryRawCreate(where, selectionArg);
        } catch (SQLiteException e) {
        }
        return null;
    }

    @Override
    public Query<M> queryRawCreateListArgs(String where, Collection<Object> selectionArg) {
        try {
            openReadableDb();
            return getAbstractDao().queryRawCreateListArgs(where, selectionArg);
        } catch (SQLiteException e) {
        }
        return null;
    }

    @Override
    public QueryBuilder<M> getQueryBuilder() {
        openReadableDb();
        return getAbstractDao().queryBuilder();
    }

    public abstract AbstractDao<M, K> getAbstractDao();

    @Override
    public void clearDaoSession() {
        if (mDaoSession != null) {
            mDaoSession.clear();
            mDaoSession = null;
        }
    }

    @Override
    public boolean dropDatabase() {
        try {
            openWriteableDb();
            DaoMaster.dropAllTables(mDaoSession.getDatabase(), false);
            return true;
        } catch (SQLiteException e) {
        }
        return false;
    }
}

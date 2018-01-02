package com.example.dylan.module.greendao;

import android.content.Context;
import android.database.sqlite.SQLiteException;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.example.dylan.module.dao.DaoMaster;
import com.example.dylan.module.dao.DaoSession;

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
    private DaoMaster.DevOpenHelper mHelper;
    protected DaoSession mDaoSession;

    public AbstractDatabaseManager(@NonNull Context context) {
        String databaseName = getDatabaseName();
        if (TextUtils.isEmpty(databaseName)) {
            devOpenHelper(context);
        } else {
            devOpenHelper(context, databaseName);
        }
    }

    public void devOpenHelper(@NonNull Context context) {
        mHelper = getOpenHelper(context, DEFAULT_DATABASE_NAME);
        openReadableDb();
    }

    public void devOpenHelper(@NonNull Context context, @NonNull String dataBaseName) {
        mHelper = getOpenHelper(context, dataBaseName);
        openReadableDb();
    }

    private DaoMaster.DevOpenHelper getOpenHelper(@NonNull Context context, @NonNull String dataBaseName) {
        closeDbConnections();
        return new DaoMaster.DevOpenHelper(context, dataBaseName, null);
    }

    private void closeDbConnections() {
        if (mHelper != null) {
            mHelper.close();
            mHelper = null;
        }

        if (mDaoSession != null) {
            mDaoSession.clear();
            mDaoSession = null;
        }
    }

    protected void openReadableDb() {
        mDaoSession = new DaoMaster(mHelper.getReadableDatabase()).newSession();
    }

    protected void openWriteableDb() {
        mDaoSession = new DaoMaster(mHelper.getWritableDatabase()).newSession();
    }

    @Override
    public boolean insert(M m) {
        try {
            if (m != null) {
                getAbstractDao().insert(m);
                return true;
            }
        } catch (SQLiteException e) {
            log(e);
        }
        return false;
    }

    @Override
    public boolean insertList(List<M> list) {
        try {
            if (list != null && list.size() > 0) {
                getAbstractDao().insertInTx(list);
                return true;
            }
        } catch (SQLiteException e) {
            log(e);
        }
        return false;
    }

    @Override
    public boolean insertOrReplace(@NonNull M m) {
        try {
            if (m != null) {
                getAbstractDao().insertOrReplace(m);
                return true;
            }
        } catch (SQLiteException e) {
            log(e);
        }
        return false;
    }

    @Override
    public boolean insertOrReplaceList(List<M> list) {
        try {
            if (list != null && list.size() > 0) {
                getAbstractDao().insertOrReplaceInTx(list);
                return true;
            }
        } catch (SQLiteException e) {
            log(e);
        }
        return false;
    }

    @Override
    public boolean delete(M m) {
        try {
            if (m != null) {
                getAbstractDao().delete(m);
                return true;
            }
        } catch (SQLiteException e) {
            log(e);
        }
        return false;
    }

    @Override
    public boolean deleteByKey(K k) {
        try {
            if (TextUtils.isEmpty(k.toString())) {
                getAbstractDao().deleteByKey(k);
                return true;
            }
        } catch (SQLiteException e) {
            log(e);
        }
        return false;
    }

    @Override
    public boolean deleteList(List<M> list) {
        try {
            if (list != null && list.size() > 0) {
                getAbstractDao().deleteInTx(list);
                return true;
            }
        } catch (SQLiteException e) {
            log(e);
        }
        return false;
    }

    @Override
    public boolean deleteAll() {
        try {
            getAbstractDao().deleteAll();
            return true;
        } catch (SQLiteException e) {
            log(e);
        }
        return false;
    }

    @Override
    public boolean update(M m) {
        try {
            if (m != null) {
                getAbstractDao().update(m);
                return true;
            }
        } catch (SQLiteException e) {
            log(e);
        }
        return false;
    }

    @Override
    public boolean updateList(List<M> list) {
        try {
            if (list != null && list.size() > 0) {
                getAbstractDao().updateInTx(list);
                return true;
            }
        } catch (SQLiteException e) {
            log(e);
        }
        return false;
    }

    @Override
    public M load(K key) {
        try {
            return getAbstractDao().load(key);
        } catch (SQLiteException e) {
            log(e);
        }
        return null;
    }

    @Override
    public List<M> loadAll() {
        try {
            return getAbstractDao().loadAll();
        } catch (SQLiteException e) {
            log(e);
        }
        return null;
    }

    @Override
    public List<M> queryRaw(String where, String... selectionArg) {
        try {
            return getAbstractDao().queryRaw(where, selectionArg);
        } catch (SQLiteException e) {
            log(e);
        }
        return null;
    }

    @Override
    public Query<M> queryRawCreate(String where, Object... selectionArg) {
        try {
            return getAbstractDao().queryRawCreate(where, selectionArg);
        } catch (SQLiteException e) {
            log(e);
        }
        return null;
    }

    @Override
    public Query<M> queryRawCreateListArgs(String where, Collection<Object> selectionArg) {
        try {
            return getAbstractDao().queryRawCreateListArgs(where, selectionArg);
        } catch (SQLiteException e) {
            log(e);
        }
        return null;
    }

    @Override
    public QueryBuilder<M> getQueryBuilder() {
        return getAbstractDao().queryBuilder();
    }

    public abstract AbstractDao<M, K> getAbstractDao();

    public abstract String getDatabaseName();

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
            DaoMaster.dropAllTables(mDaoSession.getDatabase(), false);
            return true;
        } catch (SQLiteException e) {
            log(e);
        }
        return false;
    }

    private static void log(SQLiteException e) {
        e.printStackTrace();
    }
}

package com.example.dylan.module.greendao;

import android.support.annotation.NonNull;

import org.greenrobot.greendao.query.Query;
import org.greenrobot.greendao.query.QueryBuilder;

import java.util.Collection;
import java.util.List;

/**
 * Created by dylan on 2017/8/15.
 */

public interface IDatabase<M, K> {
    boolean insert(M m);

    boolean insertList(List<M> list);

    boolean insertOrReplace(@NonNull M m);

    boolean insertOrReplaceList(List<M> list);

    boolean delete(M m);

    boolean deleteByKey(K k);

    boolean deleteList(List<M> list);

    boolean deleteAll();

    boolean update(M m);

    boolean updateList(List<M> list);

    M load(K key);

    List<M> loadAll();

    List<M> queryRaw(String where, String... selectionArg);

    Query<M> queryRawCreate(String where, Object... selectionArg);

    Query<M> queryRawCreateListArgs(String where, Collection<Object> selectionArg);

    QueryBuilder<M> getQueryBuilder();

    void clearDaoSession();

    boolean dropDatabase();
}

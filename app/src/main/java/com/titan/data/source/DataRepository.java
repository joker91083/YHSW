package com.titan.data.source;

import android.content.Context;

import com.titan.data.source.local.LDataSource;
import com.titan.data.source.local.LocalDataSource;

/**
 * Created by whs on 2017/5/18
 */

public class DataRepository implements LDataSource {
    private Context mContext;

    private static DataRepository INSTANCE = null;



    private final LocalDataSource mLocalDataSource;

    public static DataRepository getInstance(LocalDataSource localDataSource) {
        if (INSTANCE == null) {
            INSTANCE = new DataRepository(localDataSource);
        }
        return INSTANCE;
    }

    public DataRepository(LocalDataSource localDataSource) {
        this.mLocalDataSource = localDataSource;
    }

    @Override
    public void queryPest(Integer type, String keyword, qureyCalllback callback) {
        mLocalDataSource.queryPest(type,keyword,callback);
    }

    @Override
    public void getPestImg(String keyword, qureyImgCalllback callback) {
        mLocalDataSource.getPestImg(keyword,callback);
    }
}

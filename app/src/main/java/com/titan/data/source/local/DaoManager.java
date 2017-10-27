package com.titan.data.source.local;

import android.content.Context;

import com.titan.TitanApplication;
import com.titan.data.greendao.DaoMaster;
import com.titan.data.greendao.DaoSession;

/**
 * Created by whs on 2017/5/9
 *
 */

public class DaoManager {

    /** A flag to show how easily you can switch from standard SQLite to the encrypted SQLCipher. */
    public static final boolean ENCRYPTED = false;

    private Context mContext;

    private DaoMaster mDaoMaster;
    private DaoSession mDaoSession;
    private static volatile DaoManager mInstance = null;
    private DaoManager(String dbpath){
        if (mInstance == null) {
            DaoMaster.DevOpenHelper devOpenHelper = new
                    DaoMaster.DevOpenHelper(TitanApplication.getContext(), dbpath);
            mDaoMaster = new DaoMaster(devOpenHelper.getWritableDatabase());
            mDaoSession = mDaoMaster.newSession();
        }
    }
    public static DaoManager getInstance(String dbpath) {
        if (mInstance == null) {
            synchronized (DaoManager.class) {
                if (mInstance == null) {
                mInstance = new DaoManager(dbpath);
            }
            }
        }
        return mInstance;
    }
    public DaoMaster getMaster() {
        return mDaoMaster;
    }
    public DaoSession getSession() {
        return mDaoSession;
    }
    public DaoSession getNewSession() {
        mDaoSession = mDaoMaster.newSession();
        return mDaoSession;
    }
}

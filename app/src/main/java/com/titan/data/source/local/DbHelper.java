/*
 * Copyright 2016, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.titan.data.source.local;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * 数据库访问帮助类
 */
public class DbHelper extends SQLiteOpenHelper {
    //数据库版本
    public static final int DATABASE_VERSION = 1;
    //数据库名称
    public static final String DATABASE_NAME = "db_pest.sqlite";

    private static final String TEXT_TYPE = " TEXT";

    private static final String BOOLEAN_TYPE = " INTEGER";

    private static final String COMMA_SEP = ",";

    /**
     * 数据库句柄
     */
    private SQLiteDatabase mDatabase;

    private static final String SQL_CREATE_ENTRIES = "";

    public DbHelper(Context context,String path) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        try {
            InputStream inputStream=context.getResources().getAssets().open("db_pest.sqlite");
            File dbfile=new File(path);
            SQLiteDatabase database=SQLiteDatabase.openDatabase(dbfile.getAbsolutePath(),null,1);
            database.execSQL("");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    @Override
    public SQLiteDatabase getWritableDatabase() {
        return super.getWritableDatabase();
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Not required as at version 1
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Not required as at version 1
    }
}

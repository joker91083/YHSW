package com.titan.data.source;

/**
 * Created by whs on 2017/5/18
 */

import android.content.Context;
import android.support.annotation.NonNull;

import com.titan.data.source.local.LocalDataSource;

/**
 * Enables injection of mock implementations for
 * {@link com.titan.data.source.DataRepository} at compile time. This is useful for testing, since it allows us to use
 * a fake instance of the class to isolate the dependencies and run a test hermetically.
 */
public class Injection {

    public static DataRepository provideDataRepository(@NonNull Context context) {
        //checkNotNull(context);
        return DataRepository.getInstance(LocalDataSource.getInstance(context));
    }
}

package com.titan;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;

import com.titan.data.source.DataRepository;


/**
 * Created by whs on 2017/5/5
 */

public class BaseViewModel extends BaseObservable {

    protected Context mContext;
    protected DataRepository mDataRepository;
    //数据加载中
    public final ObservableBoolean dataLoading = new ObservableBoolean(false);
    //提示信息
    public final ObservableField<String> snackbarText = new ObservableField<>();
    public String getSnackbarText() {
        return snackbarText.get();
    }



}

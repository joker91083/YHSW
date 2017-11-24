package com.titan;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;


/**
 * Created by whs on 2017/5/17
 * 基础Activity
 */

public  abstract class BaseActivity extends AppCompatActivity {
    //
    protected Context mContext;

    /** 日志输出标志 **/
    protected final String TAG = this.getClass().getSimpleName();
    /** ViewModel标志 **/
    protected final String VIEWMODEL_TAG = this.getClass().getSimpleName();

    protected BaseViewModel mViewModel;


    protected Fragment mFragment;


    public abstract Fragment findOrCreateViewFragment();
    public abstract BaseViewModel findOrCreateViewModel();

    /**
     * [页面跳转]
     *
     * @param clz
     */
    public void startActivity(Class<?> clz) {
        startActivity(new Intent(BaseActivity.this,clz));
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //TitanApplication.getInstance().addActivity(this);
        mContext=this;
        //如果是平板使用横屏模式
        /*if(DeviceUtil.isTablet(mContext)){
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }else {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }*/

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart()");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy()");
    }

}

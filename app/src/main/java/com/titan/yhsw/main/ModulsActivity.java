package com.titan.yhsw.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.widget.Toast;

import com.titan.ActivityUtils;
import com.titan.BaseActivity;
import com.titan.TitanApplication;
import com.titan.ViewModelHolder;
import com.titan.yhsw.R;


/**
 * Created by hanyw on 2017/9/13/013.
 * 主界面
 */

public class ModulsActivity extends BaseActivity {
    public static final String LOGIN_VIEWMODEL_TAG = "LOGIN_VIEWMODEL_TAG";

    private ModuleViewModel mViewModel;
    private ModuleFragment mFragment;
    //private SharedPreferences sharedPreferences;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TitanApplication.addActivity(this);
        setContentView(R.layout.activity_login);
        mFragment = findOrCreateViewFragment();
        mViewModel = findOrCreateViewModel();
        mFragment.setViewModel(mViewModel);

    }


    public ModuleFragment findOrCreateViewFragment() {
        ModuleFragment fragment = (ModuleFragment) getSupportFragmentManager().findFragmentById(R.id.login_frame);
        if (fragment == null) {
            fragment = ModuleFragment.newInstance();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), fragment, R.id.login_frame);
        }
        return fragment;
    }

    public ModuleViewModel findOrCreateViewModel() {
        @SuppressWarnings("unchecked")
        ViewModelHolder<ModuleViewModel> holder =
                (ViewModelHolder<ModuleViewModel>) getSupportFragmentManager()
                        .findFragmentByTag(VIEWMODEL_TAG);
        if (holder == null || holder.getViewmodel() == null) {
            ModuleViewModel viewModel = new ModuleViewModel(mContext, mFragment);
            ActivityUtils.addFragmentToActivity
                    (getSupportFragmentManager(), ViewModelHolder.createContainer(viewModel), VIEWMODEL_TAG);
            return viewModel;
        }
        return holder.getViewmodel();
    }

    private long mExitTime;
    /**
     * 按两次退出程序
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode==KeyEvent.KEYCODE_BACK){
            if ((System.currentTimeMillis() - mExitTime) > 1500){
                Toast.makeText(mContext,"再按一次退出程序",Toast.LENGTH_SHORT).show();
                mExitTime = System.currentTimeMillis();
            }else {
                //finish();
                //System.exit(0);
                TitanApplication.getInstance().exit();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }}

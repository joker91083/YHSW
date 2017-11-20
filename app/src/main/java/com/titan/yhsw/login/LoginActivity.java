package com.titan.yhsw.login;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatDelegate;

import com.titan.ActivityUtils;
import com.titan.BaseActivity;
import com.titan.TitanApplication;
import com.titan.ViewModelHolder;
import com.titan.yhsw.R;


/**
 * Created by hanyw on 2017/9/13/013.
 * 登录
 */

public class LoginActivity extends BaseActivity {
    public static final String LOGIN_VIEWMODEL_TAG = "LOGIN_VIEWMODEL_TAG";

    private LoginViewModel mViewModel;
    private LoginFragment mFragment;
    private SharedPreferences sharedPreferences;

    //矢量支持
    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        sharedPreferences = TitanApplication.mSharedPreferences;
        mFragment = findOrCreateViewFragment();
        mViewModel = findOrCreateViewModel();
        mFragment.setViewModel(mViewModel);

        initData();
    }

    /**
     * 初始化数据
     */
    public void initData() {
        mViewModel.isremember.set(sharedPreferences.getBoolean("isremember", false));
        if (mViewModel.isremember.get()) {
            mViewModel.username.set(sharedPreferences.getString("ic_username", ""));
            mViewModel.password.set(sharedPreferences.getString("ic_password", ""));
        }
    }

    public LoginFragment findOrCreateViewFragment() {
        LoginFragment fragment = (LoginFragment) getSupportFragmentManager().findFragmentById(R.id.login_frame);
        if (fragment == null) {
            fragment = LoginFragment.newInstance();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), fragment, R.id.login_frame);
        }
        return fragment;
    }

    public LoginViewModel findOrCreateViewModel() {
        @SuppressWarnings("unchecked")
        ViewModelHolder<LoginViewModel> holder =
                (ViewModelHolder<LoginViewModel>) getSupportFragmentManager()
                        .findFragmentByTag(LOGIN_VIEWMODEL_TAG);
        if (holder == null || holder.getViewmodel() == null) {
            LoginViewModel viewModel = new LoginViewModel(mContext, mFragment);
            ActivityUtils.addFragmentToActivity
                    (getSupportFragmentManager(), ViewModelHolder.createContainer(viewModel), LOGIN_VIEWMODEL_TAG);
            return viewModel;
        }
        return holder.getViewmodel();
    }
}

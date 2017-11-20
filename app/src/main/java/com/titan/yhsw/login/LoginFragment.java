package com.titan.yhsw.login;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.titan.yhsw.MainActivity;
import com.titan.yhsw.databinding.FragLoginBinding;


/**
 * Created by hanyw on 2017/9/13/013.
 * 用户登录
 */

public class LoginFragment extends Fragment implements Login {
    private Context mContext;
    private FragLoginBinding binding;
    private LoginViewModel loginViewModel;
    private Dialog dialog;
    private static LoginFragment singleton;

    public static LoginFragment newInstance() {
        if (singleton == null) {
            singleton = new LoginFragment();
        }
        return singleton;
    }

    public void setViewModel(LoginViewModel viewModel) {
        loginViewModel = viewModel;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mContext = getActivity();
        binding = FragLoginBinding.inflate(inflater, container, false);
        ///String appName = getString(R.string.app_version) + AppUtil.getAppVersion(mContext);
        //fragLoginBinding.tvAppversion.setText(appName);
        binding.setViewmodel(loginViewModel);
        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onNext() {
        Intent intent = new Intent(mContext, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void showProgress() {
        /*dialog = new MaterialDialog.Builder(mContext)
                .content(mContext.getString(R.string.logging))
                .progress(true, 0)
                .cancelable(false)
                .build();
        dialog.show();*/
    }

    @Override
    public void stopProgress() {
        /*if (dialog != null&&dialog.isShowing()) {
            dialog.dismiss();
        }*/
    }

    @Override
    public void showToast(String info) {
        Toast.makeText(mContext,info,Toast.LENGTH_SHORT).show();
        //ToastUtil.setToast(getActivity(), info);
    }
}

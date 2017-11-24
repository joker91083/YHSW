package com.titan.yhsw.login;

/**
 * Created by hanyw on 2017/9/13/013.
 * 登录接口
 */

public interface Login {
    //跳转
    void  onNext();
    void  showProgress();
    void  stopProgress();
    void  showToast(String info);
}

package com.titan.yhsw.main;

/**
 * Created by hanyw on 2017/9/13/013.
 * 主界面接口
 */

public interface Main {
    //林业有害生物
    void  onPest();
    //寄主植物
    void  onHost();
    //常见
    void  onCommon();
    //系统说明
    void description();
}

package com.titan;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Vibrator;
import android.util.Log;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.titan.data.source.local.DaoManager;
import com.titan.util.FileUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

//import com.titan.data.source.local.GreenDaoManager;

/**
 * Created by Whs on 2016/12/9 0009
 */
public class TitanApplication extends Application {

    //野狗视频id
    //public static final String WILDDOG_VIDEO_ID = "wd0634562361hwiiyl";
    /** 是否有网络 */
    public static boolean IntetnetISVisible = false;
    /** 获取设备号序列号信息 */
    public static String SBH, XLH;
    public static String MOBILE_MODEL;

    /** 百度位置监听服务 */
    //public static LocationService locationService;
    /** 自定义位置监听服务 */
    //public static MyLocationService myLocationService;
    /** 震动器（百度定位） */
    Vibrator mVibrator;
    static Context mContext;
    public  static  TitanApplication singleton;

    //初始化数据库名
    public static String DBNAME="db_pest.sqlite";
    //初始化图片文件
    public static String IMGS="imgs";
    //初始化图片文件
    public static String DB="db";


    public static String dbpath="";
    public static String imgpath="";
    //Activitylist
    public static List<Activity> activityList = new ArrayList<>();




    /** 数据存储路径 */
    static  String filePath = null;
    /** 是否首次定位 */
    boolean isfirstlogin=true;
    private LinkedList<Activity> activityLinkedList = new LinkedList<Activity>();
    public static ActivityManager instance;
    /** 推送*/
    //private static GTHandler handler;
    //透传数据
    public static String  payloadData;
    /** 用户信息 */
    public static final String PREFS_NAME = "MyPrefsFile";
    //是否记住用户信息
    public static final String KEYNAME_REMEMBER = "isremember";
    public static final String KEYNAME_USERNAME = "username";
    public static final String KEYNAME_PSD = "password";
    public static final String KEYNAME_ISTRACK = "istrack";
    public static final String KEYNAME_UPTRACKPOINT = "uptrackpoint";
    public static SharedPreferences mSharedPreferences;

    public static TitanApplication getInstance() {
        if(singleton!=null){
            return singleton;
        }else {
            return new TitanApplication();
        }

    }
    @Override
    public void onCreate() {
        super.onCreate();
        mContext=this.getApplicationContext();
        singleton=this;
        /** Bugly SDK初始化
         * 参数1：上下文对象
         * 参数2：APPID，平台注册时得到,注意替换成你的appId
         * 参数3：是否开启调试模式，调试模式下会输出'CrashReport'tag的日志
         * 发布新版本时需要修改以及bugly isbug需要改成false等部分
         */
        //CrashReport.initCrashReport(getApplicationContext(), "900039321", false);
        /** 百度定位初始化 */
        //locationService = new LocationService(getApplicationContext());
        //mVibrator =(Vibrator)getApplicationContext().getSystemService(Service.VIBRATOR_SERVICE);
        //SDKInitializer.initialize(getApplicationContext());
        /**野狗视频会议*/
        //WilddogOptions options = new WilddogOptions.Builder().setSyncUrl("https://"+WILDDOG_VIDEO_ID+".wilddogio.com").build();
        //WilddogApp.initializeApp(this,options);
        //intiData();
        //图片加载库初始化
        Fresco.initialize(this);
        mSharedPreferences=getSharedPreferences(PREFS_NAME,0);
        iniiData();
        /** 获取当前网络状态 */
        //数据库初始化
        DaoManager.getInstance(dbpath+"/"+DBNAME);
    }

    /**
     * 初始化数据
     */
    private void iniiData() {
        //Environment.getExternalStorageDirectory().getPath()
        dbpath=mContext.getDatabasePath(DB).getPath();
        imgpath=mContext.getDatabasePath(IMGS).getPath();
       /* dbpath=mContext.getFilesDir().getAbsolutePath()+"/"+DBNAME;
        imgpath=mContext.getFilesDir().getAbsolutePath()+"/"+IMGS;*/
        /*dbpath=mContext.getExternalFilesDir(DB).getAbsolutePath()+"/"+DBNAME;
        imgpath=mContext.getExternalFilesDir(IMGS).getAbsolutePath();*/

        /*dbpath=mContext.getExternalCacheDir().getPath();
        imgpath=mContext.getExternalCacheDir().getPath()+"/"+IMGS;*/


        /*dbpath=mContext.getFilesDir().getAbsolutePath()+"/"+DBNAME;
        imgpath=mContext.getFilesDir().getAbsolutePath()+"/"+IMGS;*/
        //Log.e("initdb",dbpath);
        try {
            //初始化数据库
            FileUtil.copyAssetstoLoc(mContext,dbpath+"/"+TitanApplication.DBNAME,DBNAME);
            //初始化图片库
            FileUtil.copyAssetstoLoc(mContext,imgpath,IMGS);
        } catch (IOException e) {
            //e.printStackTrace();
            Log.e("初始化数据异常:",e.toString());
        }
    }




    public static Context getContext() {
        return mContext;
    }


    public void exit() {
        for (Activity activity : activityList) {
            if (activity != null) {
                activity.finish();
            }
        }

    }

    /**
     * 添加
     * @param activity
     */
    public static void addActivity(Activity activity) {
        activityList.add(activity);
    }

}

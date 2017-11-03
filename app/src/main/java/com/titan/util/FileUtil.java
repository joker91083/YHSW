package com.titan.util;

import android.content.Context;
import android.content.res.AssetManager;

import com.titan.TitanApplication;
import com.titan.yhsw.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by whs on 2017/10/26
 * 文件工具类
 */

public class FileUtil {
    /**
     * 复制默认数据到本地
     */
    public static void copyAssetstoLoc(Context context,String filename, String assetPath) throws IOException {
        File file = new File(filename);
        if (file.isFile()&&file.exists()) {
            return;
        }else {
            File filedir=new File(TitanApplication.dbpath);
            boolean ismake=filedir.mkdirs();
        }
        AssetManager assetManager=context.getAssets();
        //判断是拷贝的是文件还是文件夹
        String[] asset=assetManager.list(assetPath);
        if(asset.length==0){
            //InputStream is = context.getResources().openRawResource(R.raw.db_pest);
            InputStream is = context.getResources().getAssets().open(assetPath);
            FileOutputStream fos = new FileOutputStream(filename);
            byte[] buffer = new byte[1024];//8129
            int count = 0;

            while ((count = is.read(buffer)) >= 0) {
                fos.write(buffer, 0, count);
            }
            is.close();
            fos.close();
        }else {
            file.mkdirs();
            for (int i = 0; i <asset.length ; i++) {
                InputStream is = context.getResources().getAssets().open(assetPath+"/"+asset[i]);
                FileOutputStream fos = new FileOutputStream(filename+"/"+asset[i]);
                byte[] buffer = new byte[1024];//8129
                int count = 0;

                while ((count = is.read(buffer)) >= 0) {
                    fos.write(buffer, 0, count);
                }
                is.close();
                fos.close();
            }
        }


    }
    /**
     * 复制Raw数据到本地
     */
    public static void copyRawtoLoc(Context context,String filename) throws IOException {
        File file = new File(filename);
        if (file.exists() && file.isFile()) {
            //UnZipTFolder(fileDir, filename);
            return;
        }
        InputStream is = context.getResources().openRawResource(R.raw.db_pest);

        FileOutputStream fos = new FileOutputStream(filename);
        byte[] buffer = new byte[1024];//8129
        int count = 0;

        while ((count = is.read(buffer)) >= 0) {
            fos.write(buffer, 0, count);
        }
        is.close();
        fos.close();
    }
}

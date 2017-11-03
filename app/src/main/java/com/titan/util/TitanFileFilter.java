package com.titan.util;

import java.io.File;
import java.io.FileFilter;

/**
 * Created by whs on 2017/9/22
 */

public class TitanFileFilter {


    /**
     * mp3
     */
    public static class MP3FileFilter implements FileFilter {

        @Override
        public boolean accept(File file) {
            if(file.isDirectory())
                return false;
            else
            {
                String name = file.getName();
                if(name.endsWith(".mp3") || name.endsWith(".mp4"))
                    return true;
                else
                    return false;
            }

        }

    }

    /**
     * 图片
     */
    public static class ImgFileFilter implements FileFilter {

        @Override
        public boolean accept(File file) {
            if(file.isDirectory())
                return false;
            else
            {
                String name = file.getName();
                if(name.endsWith(".jpg") || name.endsWith(".png"))
                    return true;
                else
                    return false;
            }

        }

    }

    /**
     * tpk
     */
    public static class TpkFileFilter implements FileFilter {

        @Override
        public boolean accept(File file) {
            if(file.isDirectory())
                return false;
            else
            {
                String name = file.getName();
                return name.endsWith(".tpk");
            }

        }

    }

    /**
     * image tpk
     */
    public static class ImageTpkFileFilter implements FileFilter {

        @Override
        public boolean accept(File file) {
            if(file.isDirectory())
                return false;
            else
            {
                String name = file.getName();
                return name.endsWith(".tpk")&&name.contains("image");
            }

        }

    }

    /**
     * base tpk
     */
    public static class BaseTpkFileFilter implements FileFilter {

        @Override
        public boolean accept(File file) {
            if(file.isDirectory())
                return false;
            else
            {
                String name = file.getName();
                return name.endsWith(".tpk")&&name.contains("title");
            }

        }

    }

    /**
     * 地理数据库过滤器
     */
    public static class GeodatabaseFileFilter implements FileFilter {
        @Override
        public boolean accept(File file) {


            if(file.isDirectory())
                return false;
            else
            {
                String name = file.getName();
                if(name.endsWith(".geodatabase") || name.endsWith(".otms"))
                    return true;
                else
                    return false;
            }

        }

    }
}

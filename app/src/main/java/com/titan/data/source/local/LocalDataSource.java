package com.titan.data.source.local;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.util.Log;

import com.titan.TitanApplication;
import com.titan.data.greendao.PestDao;
import com.titan.model.Pest;

import java.io.File;
import java.util.List;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by whs on 2017/10/12
 *
 */

public class LocalDataSource implements LDataSource {

    private static LocalDataSource INSTANCE;


    private Context mContext;

    private LocalDataSource(@NonNull Context context) {
        mContext = context;
    }

    public static LocalDataSource getInstance(@NonNull Context context) {
        if (INSTANCE == null) {
            INSTANCE = new LocalDataSource(context);
        }
        return INSTANCE;
    }


    @Override
    public void queryPest(Integer type, final String keyword, final qureyCalllback callback) {
        Observable.create(new Observable.OnSubscribe<List<Pest>>() {
            @Override
            public void call(Subscriber<? super List<Pest>> subscriber) {
                try {

                  /*  QueryBuilder<Pest> qb= DaoManager.getInstance(TitanApplication.dbpath).getNewSession().getPestDao().queryBuilder();
                    qb.whereOr(PestDao.Properties.Cname.like(keyword),PestDao.Properties.Alias.like(keyword));
                    //qb.or(PestDao.Properties.Alias.like(keyword),PestDao.Properties.Alias.like(keyword));
                    List<Pest> pestlist=qb.list();*/




                     List<Pest> pestList=DaoManager.getInstance(TitanApplication.dbpath).getNewSession().getPestDao().queryBuilder()
                             .whereOr(PestDao.Properties.Cname.like("%"+keyword+"%"),PestDao.Properties.Cname.like("%"+keyword+"%")
                                     ,PestDao.Properties.Ename.like("%"+keyword+"%"),PestDao.Properties.Lname.like("%"+keyword+"%"))
                             .list();



                    subscriber.onNext(pestList);

                }catch (Exception e){
                    subscriber.onError(e);
                }
            }}).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Pest>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("TITAN",e.toString());
                        callback.onFailure(e.toString());

                    }

                    @Override
                    public void onNext(List<Pest> pestList) {

                        callback.onSuccess(pestList);
                    }
                });
    }

    @Override
    public void getPestImg(final String keyword, final qureyImgCalllback callback) {
        Observable.from(mContext.getDatabasePath(TitanApplication.IMGS).listFiles())
                .filter(new Func1<File, Boolean>() {
                    @Override
                    public Boolean call(File file) {
                        return file.isFile()&&file.getName().endsWith(".png");
                    }
                })
                .map(new Func1<File, Bitmap>() {
                    @Override
                    public Bitmap call(File file) {
                        if(file.getName().contains(keyword)){
                            return  BitmapFactory.decodeFile(file.getAbsolutePath());
                        }else {
                            return null;
                        }
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Bitmap>() {
                    @Override
                    public void call(Bitmap bitmap) {
                        callback.onSuccess(bitmap);
                    }
                });
        /*Observable.create(new Observable.OnSubscribe<Bitmap>() {
            @Override
            public void call(Subscriber<? super Bitmap> subscriber) {
                try {

                   File[] files=mContext.getDatabasePath(TitanApplication.IMGS).listFiles(new TitanFileFilter.ImgFileFilter());
                   for (File file:files){
                       if(file.getName().contains(keyword)){
                           subscriber.onNext(new Bitmap());
                       }
                   }

                   subscriber.onNext(null);

                }catch (Exception e){
                    subscriber.onError(e);
                }
            }}).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Pest>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("TITAN",e.toString());
                        callback.onFailure(e.toString());

                    }

                    @Override
                    public void onNext(List<Pest> pestList) {
                        callback.onSuccess(pestList);
                    }
                });*/
    }
}

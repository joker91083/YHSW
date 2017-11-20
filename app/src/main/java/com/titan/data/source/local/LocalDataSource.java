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
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
    public void queryPest(final Integer type, final String keyword, final Set<String> whbw , final qureyCalllback callback) {

        Observable.create(new Observable.OnSubscribe<List<Pest>>() {
            @Override
            public void call(Subscriber<? super List<Pest>> subscriber) {
                try {
                    //type 1：有害生物 3：寄主
                    List<Pest> pestList= new ArrayList<>();
                    if(whbw==null||whbw.isEmpty()){
                        if(type==3){
                            pestList=DaoManager.getInstance(TitanApplication.dbpath+"/"+TitanApplication.DBNAME).getNewSession().getPestDao().queryBuilder()
                                    .where(PestDao.Properties.Type.eq(type))
                                    .whereOr(PestDao.Properties.Host.like("%"+keyword+"%"),PestDao.Properties.Cname.like("%"+keyword+"%"),PestDao.Properties.Cname.like("%"+keyword+"%"))
                                    .list();
                        }else {
                            pestList=DaoManager.getInstance(TitanApplication.dbpath+"/"+TitanApplication.DBNAME).getNewSession().getPestDao().queryBuilder()
                                    .where(PestDao.Properties.Type.eq(type))
                                    .whereOr(PestDao.Properties.Cname.like("%"+keyword+"%"),PestDao.Properties.Cname.like("%"+keyword+"%")
                                            ,PestDao.Properties.Ename.like("%"+keyword+"%"),PestDao.Properties.Lname.like("%"+keyword+"%"))
                                    .list();
                        }


                    }else {
                        for(String str:whbw){
                            List<Pest> sublist=new ArrayList<>();
                            if(type==3){
                                 sublist=DaoManager.getInstance(TitanApplication.dbpath).getNewSession().getPestDao().queryBuilder()
                                        .where(PestDao.Properties.Type.eq(type),PestDao.Properties.Host.like("%"+str+"%"))
                                        .whereOr(PestDao.Properties.Cname.like("%"+keyword+"%"),PestDao.Properties.Cname.like("%"+keyword+"%")
                                                ,PestDao.Properties.Ename.like("%"+keyword+"%"),PestDao.Properties.Lname.like("%"+keyword+"%"))
                                        .list();
                            }else {
                                 sublist=DaoManager.getInstance(TitanApplication.dbpath).getNewSession().getPestDao().queryBuilder()
                                        .where(PestDao.Properties.Type.eq(type),PestDao.Properties.Whbw.like("%"+str+"%"))
                                        .whereOr(PestDao.Properties.Cname.like("%"+keyword+"%"),PestDao.Properties.Cname.like("%"+keyword+"%")
                                                ,PestDao.Properties.Ename.like("%"+keyword+"%"),PestDao.Properties.Lname.like("%"+keyword+"%"))
                                        .list();
                            }

                            pestList.addAll(sublist);
                        }
                    }





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
        Observable.from(new File(TitanApplication.imgpath).listFiles())
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

    }
}

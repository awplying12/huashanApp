package util.changhongit.com.cacheutils.Cache_RxText;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;

import java.text.SimpleDateFormat;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmConfiguration;
import io.realm.RealmObject;
import io.realm.RealmResults;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2016/9/7.
 */

public class DiskCache implements ICache {

    private Context context;
    private Realm myRealm;

    public DiskCache(Context context) {
        this.context = context;
        myRealm = Realm.getInstance(context);

        myRealm.addChangeListener(new RealmChangeListener() {
            @Override
            public void onChange() {
                Log.i("obj","onChange");

                if(isEvent){
                    return;
                }
                messageEventAdded();
            }
        });


    }

    @Override
    public <T extends TextBean> Observable<T> get(String type, Class<T> cls) {
        return Observable.create(new Observable.OnSubscribe<T>() {
            @Override
            public void call(Subscriber<? super T> subscriber) {

                RealmBean realmResult = myRealm.where(RealmBean.class).equalTo("Type",type).findFirst();


                if(subscriber.isUnsubscribed() || realmResult == null){
                    subscriber.onNext(null);

                }else {
                    String result = realmResult.getContent();
//                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//                    Log.i("obj","CreateTime : "+format.format(realmResult.getmCreateTime()));
                    T t = new Gson().fromJson(result, cls);
                    subscriber.onNext(t);

                }

                subscriber.onCompleted();
            }
        });
    }

    @Override
    public <T extends TextBean> void put(String type, T t) {
//
        Observable.create(new Observable.OnSubscribe<T>() {
            @Override
            public void call(Subscriber<? super T> subscriber) {

                RealmBean bean = new RealmBean();
                bean.setType(type);
                bean.setContent(t.toJsonString());

                myRealm.beginTransaction();
                RealmBean copybean = myRealm.copyToRealm(bean);

                myRealm.commitTransaction();
                if (!subscriber.isUnsubscribed()) {

                    subscriber.onNext(t);
                    subscriber.onCompleted();
                }
            }
        })
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();
    }

    private boolean isEvent;
    private void messageEventAdded(){
        isEvent = true;
        Realm realm = Realm.getInstance(context);


        RealmResults<RealmBean> realmResults = realm.where(RealmBean.class).findAll();
        Observable.from(realmResults)
                .filter(new Func1<RealmBean, Boolean>() {
                    @Override
                    public Boolean call(RealmBean realmBean) {
//                        realmBean.setEXPIRE_LIMIT(60 * 1000*15);
                      //  return System.currentTimeMillis() - realmBean.getmCreateTime() > realmBean.getEXPIRE_LIMIT();
                        return System.currentTimeMillis()-realmBean.getmCreateTime()>1000*60*15;
                       // return realmBean.getType().equals("Conntry_14");
                    }
                })
                .subscribe(new Subscriber<RealmBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("obj"," e : "+e.toString());
                    }

                    @Override
                    public void onNext(RealmBean realmBean) {
                        realm.beginTransaction();
                        if(realmBean != null){
//
                            Log.i("obj"," RealmBean : "+realmBean.toString());
                            realmResults.remove(realmBean);

                        }

                        realm.commitTransaction();
                    }
                });

//        for(RealmBean bean : realmResults){
//            if(System.currentTimeMillis() - bean.getmCreateTime() > bean.getEXPIRE_LIMIT()){
//                realm.beginTransaction();
//
//                Log.i("obj"," RealmBean : "+bean.toString());
//                //RealmBean mrealmbean = realm.where(RealmBean.class).equalTo("type",bean.getType()).findFirst();
//                realmResults.remove(bean);
//
//            }
//        }
//        realm.commitTransaction();
        Log.i("obj","ok!");

        isEvent = false;

    }
}

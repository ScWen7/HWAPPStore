package com.scwen7.retrofitlibrary.rxhelper;

import android.util.Log;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by 解晓辉 on 2017/7/21.
 * 作用：
 */

public class RxDelayUtil {
    public static  void dalay(long delay,final Consumer<Boolean> consumer){

        Observable.interval(0,delay, TimeUnit.MILLISECONDS)
                .take(delay)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Long aLong) {

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.e("TAG", "onError");
                    }

                    @Override
                    public void onComplete() {
                        try {
                            consumer.accept(true);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

}

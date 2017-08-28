package io.scwen7.hwappstore.common.rxhelper;

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
    public static  void dalay(final Consumer<Boolean> consumer){

        Observable.interval(0,2, TimeUnit.SECONDS)
                .take(2)
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
                        Log.e("TAG", "onComplete");
                        try {
                            consumer.accept(true);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

}

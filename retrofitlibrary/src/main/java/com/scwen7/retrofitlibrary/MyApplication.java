package com.scwen7.retrofitlibrary;

import android.app.Application;

/**
 * Created by 解晓辉  on 2017/11/30 11:46 *
 * QQ  ：811733738
 * 作用:
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        RetrofitClient.init(this);
    }
}

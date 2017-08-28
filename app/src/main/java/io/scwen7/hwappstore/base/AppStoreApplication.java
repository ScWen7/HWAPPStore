package io.scwen7.hwappstore.base;

import android.app.Application;

import io.scwen7.hwappstore.common.http.RetrofitClient;
import io.scwen7.hwappstore.common.utils.CrashHandler;
import io.scwen7.hwappstore.common.utils.UIUtils;

/**
 * Created by 解晓辉 on 2017/8/28.
 * 作用：
 */

public class AppStoreApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        //初始化资源
        UIUtils.init(this);
        RetrofitClient.init(this);
        CrashHandler.getInstance().init(this);
    }
}

package io.scwen7.hwappstore.di.module;

import android.content.Context;

import dagger.Module;
import dagger.Provides;
import io.scwen7.hwappstore.base.AppStoreApplication;
import io.scwen7.hwappstore.di.scope.ContextLife;
import io.scwen7.hwappstore.di.scope.PreApp;

/**
 * Created by 解晓辉  on 2017/8/31 22:06 *
 * QQ  ：811733738
 * 作用:
 */
@Module
public class AppModule {
    private AppStoreApplication mAppStoreApplication;

    public AppModule(AppStoreApplication appStoreApplication) {
        mAppStoreApplication = appStoreApplication;
    }

    @Provides
    @PreApp
    @ContextLife("applicationo")
    public Context provideAppContext() {
        return mAppStoreApplication.getApplicationContext();
    }

}

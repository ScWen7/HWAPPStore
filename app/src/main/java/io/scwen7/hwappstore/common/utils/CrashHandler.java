package io.scwen7.hwappstore.common.utils;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import io.scwen7.hwappstore.base.ActivityManager;
import io.scwen7.hwappstore.base.AppStoreApplication;
import io.scwen7.hwappstore.ui.activity.SplashActivity;

/**
 * Created by 解晓辉  *
 * 作用:  监听软件异常的工具类
 * /**
 * UncaughtException处理类,当程序发生Uncaught异常的时候,有该类来接管程序,并记录发送错误报告.
 * <p>
 * 需要在Application中注册，为了要在程序启动器就监控整个程序
 */
public class CrashHandler implements Thread.UncaughtExceptionHandler {

    public static final String TAG = "CrashHandler";

    //系统默认的UncaughtException处理类
    private Thread.UncaughtExceptionHandler mDefaultHandler;
    //CrashHandler实例
    private static CrashHandler instance;
    //程序的Context对象
    private AppStoreApplication application;
    //用来存储设备信息和异常信息
    private Map<String, String> infos = new HashMap<>();

    //用于格式化日期,作为日志文件名的一部分
    private DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss", Locale.CHINA);

    /**
     * 保证只有一个CrashHandler实例
     */
    private CrashHandler() {
    }

    /**
     * 获取CrashHandler实例 ,单例模式
     */
    public static CrashHandler getInstance() {
        if (instance == null)
            instance = new CrashHandler();
        return instance;
    }

    /**
     * 初始化
     */
    public void init(AppStoreApplication context) {
        application = context;
        //获取系统默认的UncaughtException处理器
        mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
        //设置该CrashHandler为程序的默认处理器
        Thread.setDefaultUncaughtExceptionHandler(this);

//        Tracker myTracker = application.getDefaultTracker();
//        Thread.UncaughtExceptionHandler myHandler = new ExceptionReporter(
//                myTracker,
//                this,
//                context);

// Make myHandler the new default uncaught exception handler.
//        Thread.setDefaultUncaughtExceptionHandler(myHandler);
    }

    /**
     * 当UncaughtException发生时会转入该函数来处理
     */
    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        if (!handleException(ex) && mDefaultHandler != null) {
            //如果用户没有处理则让系统默认的异常处理器来处理
            mDefaultHandler.uncaughtException(thread, ex);
        } else {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Log.e(TAG, "error : ", e);
            }
            Intent intent = new Intent(application.getApplicationContext(), SplashActivity.class);
            PendingIntent restartIntent = PendingIntent.getActivity(
                    application.getApplicationContext(), 0, intent, Intent.FLAG_ACTIVITY_NEW_TASK);
            //退出程序
            AlarmManager mgr = (AlarmManager) application.getSystemService(Context.ALARM_SERVICE);
            mgr.set(AlarmManager.RTC, System.currentTimeMillis() + 1000,
                    restartIntent); // 1秒钟后重启应用
            ActivityManager.finishActivity();
        }
    }

    /**
     * 自定义错误处理,收集错误信息 发送错误报告等操作均在此完成.
     *
     * @param ex
     * @return true:如果处理了该异常信息;否则返回false.
     */
    private boolean handleException(Throwable ex) {
        if (ex == null) {
            return false;
        }
        //收集设备参数信息
//        collectDeviceInfo(application);

        //使用Toast来显示异常信息
        new Thread() {
            @Override
            public void run() {
                Looper.prepare();
                Toast.makeText(application, "啊哦，出现了一点小问题", Toast.LENGTH_SHORT).show();
                Looper.loop();
            }
        }.start();
        //保存日志文件
//        saveCatchInfo2File(ex);
        return true;
    }

}

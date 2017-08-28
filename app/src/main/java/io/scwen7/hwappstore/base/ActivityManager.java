package io.scwen7.hwappstore.base;

import android.app.Activity;

import java.util.ArrayList;

/**
 * Created by 解晓辉 on 2017/7/10.
 * 作用：
 */

public class ActivityManager {
    private static ArrayList<Activity> list = new ArrayList<>();

    /**
     * Activity关闭时，删除Activity列表中的Activity对象
     */
    public static void removeActivity(Activity a) {
        list.remove(a);
    }

    /**
     * 向Activity列表中添加Activity对象
     */
    public static void addActivity(Activity a) {
        list.add(a);
    }


    public static int getActivityCount() {
        return list.size();
    }

    /**
     * 关闭Activity列表中的所有Activity并退出进程
     */
    public static void finishActivity() {
        for (Activity activity : list) {
            if (null != activity) {
                activity.finish();
            }
        }
        //杀死该应用进程
        android.os.Process.killProcess(android.os.Process.myPid());
    }

}

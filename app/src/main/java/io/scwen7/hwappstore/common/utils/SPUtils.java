package io.scwen7.hwappstore.common.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

/**
 * Created by 解晓辉 on 2016/8/13.
 * 作用：
 */
public class SPUtils {

    public static String SP_NAME = "com.pinxiango.store";


    public static boolean getBoolean(Context context, String key) {
        SharedPreferences sp = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        return sp.getBoolean(key, false);
    }

    public static void putBoolean(Context context, String key, boolean b) {

        SharedPreferences sp = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        sp.edit().putBoolean(key, b).apply();

    }

    public static void putString(Context context, String key, String value) {

//        SharedPreferences sp = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
//        sp.edit().putString(key, value).apply();
        ACache.get(context).put(key, value);
    }

    public static void saveToken(Context context, String key, String value) {

//        SharedPreferences sp = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
//        sp.edit().putString(key, value).apply();
        ACache.get(context).put(key, value, ACache.TIME_DAY * 20);
    }

    public static String getString(Context mContext, String key) {
        String result;
//        SharedPreferences sp = mContext.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
//        result = sp.getString(key, "");
        result = ACache.get(mContext).getAsString(key);
        Log.e("TAG", "result:" + result);
        return result == null ? "" : result;
    }

    public static void clearToken(Context context, String key) {
        ACache.get(context).remove(key);
    }
}

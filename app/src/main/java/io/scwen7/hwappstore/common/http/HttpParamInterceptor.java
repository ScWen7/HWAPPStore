package io.scwen7.hwappstore.common.http;


import android.content.Context;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by 解晓辉  on 2017/6/10 09:48 *
 * QQ  ：811733738
 * 作用:
 */

public class HttpParamInterceptor implements Interceptor {


    private Context mContext;

    //url中使用的参数 version
    public String version = "";

    //url中使用的参数
    public String app_source = "";

    public HttpParamInterceptor(Context context) {
        this.mContext = context;
        init(context);

    }

    /**
     * 初始化 网络地址工具类
     *
     * @param context
     */
    public void init(Context context) {
//        PackageManager manager = context.getPackageManager();
//        String appVersionName = "1.3.4";
//        try {
//            PackageInfo info = manager.getPackageInfo(context.getPackageName(), 0);
//
//            appVersionName = info.versionName; // 版本名
//
//            currentVersionCode = info.versionCode; // 版本号
//
//        } catch (PackageManager.NameNotFoundException e) {
//            e.printStackTrace();
//        }
//
//        //拼接 需要的version 字段
//        version = "20." + appVersionName;
//        app_source = "360";

    }

    @Override
    public Response intercept(Chain chain) throws IOException {


        Request request = chain.request();
//        Request.Builder requestBuilder = request.newBuilder();
//        //添加公共的头部
//        requestBuilder.addHeader("User-Agent", "pinxiango");
//
//
//        String method = request.method();
//        if ("GET".equals(method)) {  //GET请求
//        } else if ("POST".equals(method)) {  //POST 请求
//            RequestBody body = request.body();
//
//            String data_json = ""; //传入的Json 字符串
//
//            FormBody.Builder builder = new FormBody.Builder();
//
//
//            Buffer buffer = new Buffer();
//            body.writeTo(buffer);
//            data_json = buffer.readUtf8();
//            builder.add("data", data_json);
//
//            //系统当前的时间戳
//            int dateTime = (int) (new Date().getTime() / 1000);
//            String url = request.url().toString();
//            String[] split = url.split("/");
//            String api_name = split[split.length - 1];
//
//
//            String mde_sign = Contacts.KEY + "-" +
//                    version + "-" +
//                    app_source + "-" +
//                    data_json + "-" +
//                    dateTime + "-"
//                    + api_name;
//            //进行加密
//            String sign = MD5Utils.MD5(mde_sign);
//
//
//            builder.add("version", version);
//            builder.add("app_source", app_source);
//            builder.add("datetime", dateTime + "");
//            builder.add("sign", sign);
//
//
//            FormBody formBody = builder.build();
//
//            request = requestBuilder
//                    .post(formBody)
//                    .build();
//        }

        return chain.proceed(request);
    }

}

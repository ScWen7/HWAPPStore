package com.scwen7.retrofitlibrary;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import com.scwen7.retrofitlibrary.cookie.CookieJarImpl;
import com.scwen7.retrofitlibrary.cookie.store.PersistentCookieStore;
import com.scwen7.retrofitlibrary.fastjsonconverter.FastJsonConvertFactory;
import com.scwen7.retrofitlibrary.rxhelper.RxSchedulerHepler;

import java.io.File;
import java.net.FileNameMap;
import java.net.URLConnection;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 * Created by 解晓辉 on 2017/6/28.
 * 作用:
 * 使用Retrofit 的上传工具类
 */

public class UploadUtil {
    public static Observable<BaseResult<String>> upload(Context context, String json, String filePath) {


        PackageManager manager = context.getPackageManager();
        String appVersionName = "1.3.4";
        try {
            PackageInfo info = manager.getPackageInfo(context.getPackageName(), 0);

            appVersionName = info.versionName; // 版本名


        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        //拼接 需要的version 字段
        String version = "20." + appVersionName;
        String app_source = "360";

        OkHttpClient httpClient = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .cookieJar(new CookieJarImpl(new PersistentCookieStore(context)))
                .build();


        Retrofit retrofit = new Retrofit.Builder().baseUrl(RetrofitClient.BASE_URL)
                .addConverterFactory(FastJsonConvertFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(httpClient)
                .build();

        UploadService uploadService = retrofit.create(UploadService.class);


        // 创建 RequestBody，用于封装 请求RequestBody
        File imageFile = new File(filePath);
        RequestBody requestFile =
                RequestBody.create(guessMimeType(imageFile.getName()), imageFile);


        String data_json = json;

        //系统当前的时间戳
        int dateTime = (int) (new Date().getTime() / 1000);

        String api_name = "uploadImg";


        String mde_sign = "" + "-" +
                version + "-" +
                app_source + "-" +
                data_json + "-" +
                dateTime + "-"
                + api_name;
        //进行加密
//        String sign = MD5Utils.MD5(mde_sign);


        MultipartBody.Builder builder = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)//表单类型 "multipart/form-data"
                .addFormDataPart("version", version)
                .addFormDataPart("app_source", app_source)
                .addFormDataPart("datetime", dateTime + "")
                .addFormDataPart("data", data_json)
//                .addFormDataPart("sign", sign)
                .addFormDataPart("file", imageFile.getName(), requestFile);

        List<MultipartBody.Part> parts = builder.build().parts();

        return uploadService.upload(parts)
                .compose(RxSchedulerHepler.<BaseResult<String>>io_main());

    }

    interface UploadService {
        @Multipart
        @POST("uploadImg")
        Observable<BaseResult<String>> upload(
                @Part List<MultipartBody.Part> partList);
    }

    private static MediaType guessMimeType(String path) {
        FileNameMap fileNameMap = URLConnection.getFileNameMap();
        path = path.replace("#", "");   //解决文件名中含有#号异常的问题
        String contentType = fileNameMap.getContentTypeFor(path);
        if (contentType == null) {
            contentType = "application/octet-stream";
        }
        return MediaType.parse(contentType);
    }

}

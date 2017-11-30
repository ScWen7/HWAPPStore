package com.scwen7.retrofitlibrary;


import io.reactivex.Flowable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

/**
 * Created by 解晓辉 on 2017/6/26.
 * 作用：
 */

public interface ApiService {



    @GET
    @Streaming
    Flowable<ResponseBody> downloadFile(@Url String url);


}

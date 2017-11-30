package com.scwen7.retrofitlibrary.fastjsonconverter;

import android.util.Log;

import com.alibaba.fastjson.JSON;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.charset.Charset;

import okhttp3.ResponseBody;
import retrofit2.Converter;


/**
 * Created by Administrator on 2016/9/12.
 */
public class FastJsonResponseBodyConverter<T> implements Converter<ResponseBody, T> {

    public static final Charset UTF_8 = Charset.forName("UTF-8");

    private Type type;

    public FastJsonResponseBodyConverter(Type type) {
        this.type = type;
    }

    @Override
    public T convert(ResponseBody value) throws IOException {

//        InputStreamReader inputStreamReader;
//        BufferedReader reader;
//
//        inputStreamReader = new InputStreamReader(value.byteStream(), UTF_8);
//        reader = new BufferedReader(inputStreamReader);
//
//        StringBuilder sb = new StringBuilder();
//
//        String line;
//        if ((line = reader.readLine()) != null) {
//            sb.append(line);
//        }
//        inputStreamReader.close();
//        reader.close();
        String json = value.string().toString().trim();
        Log.e("TAG", "json:"+json);
        return JSON.parseObject(json, type);
    }
}

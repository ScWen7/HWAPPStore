package io.scwen7.hwappstore.common.utils;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.load.engine.bitmap_recycle.LruBitmapPool;
import com.bumptech.glide.load.engine.cache.InternalCacheDiskCacheFactory;
import com.bumptech.glide.load.engine.cache.LruResourceCache;
import com.bumptech.glide.module.GlideModule;

/**
 * Created by 解晓辉 on 2017/2/20.
 * 作用：
 */

public class GlideConfiguration implements GlideModule {
    @Override
    public void applyOptions(Context context, GlideBuilder glideBuilder) {
//        glideBuilder.setDecodeFormat(DecodeFormat.PREFER_ARGB_8888);
        int maxMemory = (int) Runtime.getRuntime().maxMemory();//获取系统分配给应用的总内存大小
        int memoryCacheSize = maxMemory / 8;//设置图片内存缓存占用八分之一

        //设置内存缓存大小
        glideBuilder.setMemoryCache(new LruResourceCache(memoryCacheSize));
//        File cacheDir = context.getCacheDir();//指定的是数据的缓存地址
        int diskCacheSize = 1024 * 1024 * 40;//最多可以缓存多少字节的数据
        //设置磁盘缓存大小
        glideBuilder.setDiskCache(new InternalCacheDiskCacheFactory(context, diskCacheSize));

        //设置BitmapPool缓存内存大小
        glideBuilder.setBitmapPool(new LruBitmapPool(memoryCacheSize));

    }

    @Override
    public void registerComponents(Context context, Glide glide) {

    }
}

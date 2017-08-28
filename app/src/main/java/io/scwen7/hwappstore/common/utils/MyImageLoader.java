package io.scwen7.hwappstore.common.utils;

import android.content.Context;
import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;


/**
 * Created by 解晓辉 on 2017/2/15.
 * 作用：图片加载的工具类
 */

public class MyImageLoader {
    /**
     * 显示图片
     *
     * @param context   上下文对象
     * @param path      加载的路径
     * @param imageView 需要显示图片的控件
     * @param errorId   记载错误显示的图片
     * @param placeId   加载中的占位图
     */
    public void displayImage(Context context, String path, ImageView imageView, int errorId, int placeId) {
        Glide.with(context)                             //配置上下文
                .load(path)      //设置图片路径(fix #8,文件名包含%符号 无法识别和显示)
                .error(errorId)           //设置错误图片
                .placeholder(placeId)     //设置占位图片
                .diskCacheStrategy(DiskCacheStrategy.RESULT)//缓存全尺寸
                .into(imageView);

    }

    /**
     * 缓存图片
     *
     * @param context
     * @param path
     * @param imageView
     */
    public void displayImageConer(Context context, String path, ImageView imageView) {
        Glide.with(context)
                .load(path)
                .asBitmap()
                .diskCacheStrategy(DiskCacheStrategy.RESULT)//缓存全尺寸
                .into(imageView);
    }

    /**
     * 缓存图片
     *
     * @param context
     * @param path
     * @param imageView
     */
    public void displayImageConerHolder(Context context, String path, ImageView imageView, int errorId, int placeId) {
        Glide.with(context)
                .load(path)
                .asBitmap()
                .error(errorId)           //设置错误图片
                .placeholder(placeId)     //设置占位图片
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)//缓存全尺寸
                .into(imageView);
    }


//    public void displayImageConerholder(Context context, String path, ImageView imageView, int errorId, int placeId) {
//        Glide.with(context)
//                .load(path).
//                bitmapTransform(new RoundedCornersTransformation(context, radius, 0, RoundedCornersTransformation.CornerType.ALL))
//                .error(errorId)           //设置错误图片
//                .placeholder(placeId)     //设置占位图片
//                .diskCacheStrategy(DiskCacheStrategy.RESULT)//缓存全尺寸
//                .into(imageView);
//    }


//    public void displayImageRadius(Context context, String path, ImageView imageView){
//        int radius = UIUtils.dip2px(5);
//        Glide.with(context)
//                .load(path).
//                bitmapTransform(new RoundedCornersTransformation(context, radius, 0, RoundedCornersTransformation.CornerType.ALL))
//                .diskCacheStrategy(DiskCacheStrategy.RESULT)//缓存全尺寸
//                .into(imageView);
//    }

//    public void displayImageCircle(Context context, String path, ImageView imageView){
//        Glide.with(context)
//                .load(path)
//                .bitmapTransform(new CropCircleTransformation(context))
//                .diskCacheStrategy(DiskCacheStrategy.RESULT)//缓存全尺寸
//                .into(imageView);
//
//    }

    /**
     * 重载的方法 显示图片，但是不显示错误图片和占位图
     *
     * @param context
     * @param url
     * @param imageView
     */
    public void displayImage(Context context, String url, ImageView imageView) {
        Glide.with(context)                             //配置上下文
                .load(url)      //设置图片路径(fix #8,文件名包含%符号 无法识别和显示)
                .diskCacheStrategy(DiskCacheStrategy.RESULT)//缓存全尺寸
                .centerCrop()
                .into(imageView);
    }

    public void displayIamgeNoScale(Context context, String url, ImageView imageView) {
        Glide.with(context)                             //配置上下文
                .load(url)      //设置图片路径(fix #8,文件名包含%符号 无法识别和显示)
                .diskCacheStrategy(DiskCacheStrategy.RESULT)//缓存全尺寸
                .into(imageView);
    }

    public void displayBigImage(Context context, String url, ImageView imageView) {
        Glide.with(context)                             //配置上下文
                .load(url)      //设置图片路径(fix #8,文件名包含%符号 无法识别和显示)
                .diskCacheStrategy(DiskCacheStrategy.RESULT)//缓存裁剪结果
                .centerCrop()
//                .placeholder(R.drawable.big_place)
//                .error(R.drawable.big_place)
                .into(imageView);
    }

    /**
     * @param context
     * @param url
     * @param imageView
     * @param width     设置显示的宽
     * @param height    设置显示的高
     */
    public void displayImageOverride(Context context, String url, ImageView imageView, int width, int height) {
        Glide.with(context)                             //配置上下文
                .load(url)      //设置图片路径(fix #8,文件名包含%符号 无法识别和显示)
                .diskCacheStrategy(DiskCacheStrategy.RESULT)//缓存全尺寸
                .override(width, height)
                .into(imageView);
    }

    //单例模式
    private static MyImageLoader instance;
    private static int radius ;

    public static MyImageLoader getInstance() {
        if (instance == null) {
            instance = new MyImageLoader();
            radius = UIUtils.dip2px(10);
        }
        return instance;
    }

    private MyImageLoader() {

    }

    /**
     * 判断path 是否是 网络资源
     *
     * @param path 路径
     * @return
     */
    public boolean isNetUri(String path) {
        boolean isNet = false;
        if (!TextUtils.isEmpty(path)) {
            String url = path.toLowerCase();
            if (url.startsWith("http") || url.startsWith("mms") || url.startsWith("rtsp")) {
                isNet = true;

            }
        }
        return isNet;
    }
}

package io.scwen7.hwappstore.weight.baseadapter.listgridcommon;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import io.scwen7.hwappstore.common.utils.MyImageLoader;


/**
 * Created by 解晓辉  on 2016/11/26 15:12 *
 * QQ  ：811733738
 * 作用:打造通用的ViewHolder
 *
 */

public class CommonViewHolder {
    private SparseArray<View> mViews;
    private View mConvertView;
    private Context mContext;

    private CommonViewHolder(Context context, ViewGroup parent, int layoutId, int position) {
        this.mViews = new SparseArray<>();
        mConvertView = LayoutInflater.from(context).inflate(layoutId, parent, false);
        mConvertView.setTag(this);
        this.mContext = context;
    }

    /**
     * 获取一个通用的ViewHolder
     *
     * @param context
     * @param convertView
     * @param parent
     * @param layoutId
     * @param position
     * @return
     */
    public static CommonViewHolder get(Context context, View convertView, ViewGroup parent, int layoutId, int position) {
        if (convertView == null) {
            return new CommonViewHolder(context, parent, layoutId, position);
        }
        return (CommonViewHolder) convertView.getTag();
    }

    /**
     * 通过View的id获取视图对象
     *
     * @param viewId
     * @param <T>
     * @return
     */
    public <T extends View> T getView(int viewId) {
        //当获取不到视图时，再去加载，同时添加
        View view = mViews.get(viewId);
        if (view == null) {
            view = mConvertView.findViewById(viewId);
            //很重要，一定要添加到集合中
            mViews.put(viewId,view);
        }
        return (T)view;
    }

    public View getConvertView() {
        return mConvertView;
    }
    //--------------提供一些通用视图的操作-------------------------------

    /**
     * 给TextView设置 文本
     * @param viewId  视图的id
     * @param content   需要设置的文本的内容
     * @return
     */
    public CommonViewHolder setText(int viewId,String content){
        TextView textView = getView(viewId);
        textView.setText(content);
        return this; //返回该类对象，可以链式调用
    }

    /**
     * 给TextView设置 文本
     * @param viewId  视图的id
     * @param content   需要设置的文本的内容
     * @return
     */
    public CommonViewHolder setText(int viewId,String content,int color){
        TextView textView = getView(viewId);
        textView.setText(content);
        textView.setTextColor(color);
        return this; //返回该类对象，可以链式调用
    }
    /**
     * 为IamgeView设置ImageResourceid
     *
     * @param viewId  视图的id
     * @param resourceId
     * @return
     */
    public CommonViewHolder setImageResource(int viewId ,int resourceId){
        ImageView imageView = getView(viewId);
        imageView.setImageResource(resourceId);
        return this;
    }
    /**
     * 为ImageView设置Bitmap图片
     *
     * @param viewId
     * @param bm
     * @return
     */
    public CommonViewHolder setImageBitmap(int viewId, Bitmap bm)
    {
        ImageView view = getView(viewId);
        view.setImageBitmap(bm);
        return this;
    }

    /**
     * 为ImageView设置网络url 或者 文件path 图片
     * @param viewId   View的id
     * @param path     url或者 文件path
     * @param errorId   错误图片id
     * @param placeId   占位图片id
     * @return
     */
    public CommonViewHolder setImageBUrlOrFile(int viewId,String path,int errorId,int placeId){
        ImageView imageView = getView(viewId);
        MyImageLoader.getInstance().displayImage(mContext,path,imageView,errorId,placeId);
        return this;
    }

    public CommonViewHolder setImageNoPlace(int viewId,String path){
        ImageView imageView = getView(viewId);
        MyImageLoader.getInstance().displayImage(mContext,path,imageView);
        return this;
    }

    /**
     * 加载图片并重新调整大小
     * @param viewId
     * @param path
     * @param width  宽
     * @param height   高
     * @return
     */
    public CommonViewHolder setImageOverride(int viewId,String path,int width,int height){
        ImageView imageView = getView(viewId);
        MyImageLoader.getInstance().displayImageOverride(mContext,path,imageView,width,height);
        return this;
    }

    /**
     * 配合 SelectableRoundedImageView  使用展示圆角图片
     * @param viewId
     * @param path
     * @return
     */
    public CommonViewHolder setImageCorner(int viewId,String path){
        ImageView imageView = getView(viewId);
        MyImageLoader.getInstance().displayImageConer(mContext,path,imageView);
        return this;
    }

//    public CommonViewHolder setImageRadius(int viewId,String path){
//        ImageView imageView = getView(viewId);
//        MyImageLoader.getInstance().displayImageRadius(mContext,path,imageView);
//        return this;
//    }



    public  CommonViewHolder setImageCornerPlace(int viewId,String path,int errorId, int placeId){
        ImageView imageView = getView(viewId);
        MyImageLoader.getInstance().displayImageConerHolder(mContext,path,imageView,errorId,placeId);
        return this;
    }

    public CommonViewHolder setVisiable(int viewId,int  visiable){
        View view =  getView(viewId);
        view.setVisibility(visiable);
        return this;
    }

    /**
     * 关于事件的
     */
    public CommonViewHolder setOnClickListener(int viewId,
                                         View.OnClickListener listener)
    {
        View view = getView(viewId);
        view.setOnClickListener(listener);
        return this;

    }
}

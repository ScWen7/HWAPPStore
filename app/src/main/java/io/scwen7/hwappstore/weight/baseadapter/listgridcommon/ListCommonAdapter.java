package io.scwen7.hwappstore.weight.baseadapter.listgridcommon;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Created by 解晓辉  on 2016/11/26 15:24 *
 * QQ  ：811733738
 * 作用: 打造通用的Adapter
 */

public abstract class ListCommonAdapter<T> extends BaseAdapter {

    protected Context mContext;
    private List<T> mDatas;
    private int mItemViewId;
    private LayoutInflater inflater;

    public ListCommonAdapter(Context context, List<T> datas, int itemViewId) {
        this.mContext = context;
        this.mDatas = datas;
        this.mItemViewId = itemViewId;
    }


    public void setDatas(List<T> datas) {
        this.mDatas = datas;
        this.notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mDatas == null ? 0 : mDatas.size();
    }

    @Override
    public T getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CommonViewHolder commonViewHolder = getViewHoler(position, convertView, parent);
        convert(commonViewHolder, getItem(position), position);
        return commonViewHolder.getConvertView();
    }

    /**
     * 子类重写次方法进行数据的绑定
     *
     * @param commonViewHolder 获取到的ViewHolder
     * @param item             数据bean
     */
    protected abstract void convert(CommonViewHolder commonViewHolder, T item, int position);

    /**
     * 获取ViewHolder
     *
     * @param position
     * @param convertView
     * @param parent
     * @return
     */
    private CommonViewHolder getViewHoler(int position, View convertView, ViewGroup parent) {
        return CommonViewHolder.get(mContext, convertView, parent, mItemViewId, position);
    }
}

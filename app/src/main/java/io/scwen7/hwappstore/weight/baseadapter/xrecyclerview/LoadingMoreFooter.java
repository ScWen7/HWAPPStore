package io.scwen7.hwappstore.weight.baseadapter.xrecyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import io.scwen7.hwappstore.R;
import io.scwen7.hwappstore.common.utils.UIUtils;


public class LoadingMoreFooter extends FrameLayout {

    //    SimpleViewSwitcher progressCon;
    public final static int STATE_LOADING = 0;
    public final static int STATE_COMPLETE = 1;
    public final static int STATE_NOMORE = 2;
    private TextView mText;
    private String loadingHint;
    private String noMoreHint;
    private String loadingDoneHint;

    private Context mContext;
    private ProgressBar mProgressBar;

    public LoadingMoreFooter(Context context) {
        super(context);
        this.mContext = context;
        initView();
    }

    /**
     * @param context
     * @param attrs
     */
    public LoadingMoreFooter(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        initView();
    }

    public void setLoadingHint(String hint) {
        loadingHint = hint;
    }

    public void setNoMoreHint(String hint) {
        noMoreHint = hint;
    }

    public void setLoadingDoneHint(String hint) {
        loadingDoneHint = hint;
    }

    public void initView() {
        setPadding(0, UIUtils.dip2px(10), 0, UIUtils.dip2px(10));

        RecyclerView.LayoutParams layoutParams = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        setLayoutParams(layoutParams);

        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        View loadingView = layoutInflater.inflate(R.layout.loading_footer, this, true);
        mProgressBar = (ProgressBar) loadingView.findViewById(R.id.progress_loading);
        mText = (TextView) loadingView.findViewById(R.id.tv_hint);
        loadingHint = (String) getContext().getText(R.string.listview_loading);
        noMoreHint = (String) getContext().getText(R.string.nomore_loading);
        loadingDoneHint = (String) getContext().getText(R.string.loading_done);
    }

    public void setProgressStyle(int style) {
    }

    public void setState(int state) {
        switch (state) {
            case STATE_LOADING:
                mProgressBar.setVisibility(View.VISIBLE);
                mText.setText(loadingHint);
                this.setVisibility(View.VISIBLE);
                break;
            case STATE_COMPLETE:
                mText.setText(loadingDoneHint);
                this.setVisibility(View.GONE);
                break;
            case STATE_NOMORE:
                mText.setText(noMoreHint);
                mProgressBar.setVisibility(View.GONE);
                this.setVisibility(View.VISIBLE);
                break;
        }
    }
}

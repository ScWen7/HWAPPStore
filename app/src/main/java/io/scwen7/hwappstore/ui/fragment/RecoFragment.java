package io.scwen7.hwappstore.ui.fragment;

import android.view.View;
import android.widget.FrameLayout;

import io.scwen7.hwappstore.R;
import io.scwen7.hwappstore.base.BaseFragment;
import io.scwen7.hwappstore.weight.statuslayout.StatusLayoutManager;

/**
 * Created by 解晓辉 on 2017/8/28.
 * 作用：
 */

public class RecoFragment extends BaseFragment {

    private StatusLayoutManager mStatusLayoutManager;

    @Override
    protected int getLayoutId() {
        return R.layout.empty_content;
    }


    @Override
    public void initStatusLayout(View rootView) {
        FrameLayout emptyContent = (FrameLayout) rootView.findViewById(R.id.empty_content);

        mStatusLayoutManager = new StatusLayoutManager.Builder(mActivity)
                .loadingView(R.layout.page_loading)
                .contentView(R.layout.fragment_reco)
                .errorView(R.layout.page_error)
                .emptyDataView(R.layout.page_empty)
                .build();

        emptyContent.addView(mStatusLayoutManager.getRootLayout());

    }

    @Override
    protected void initData() {
        mStatusLayoutManager.showLoading();

    }
}

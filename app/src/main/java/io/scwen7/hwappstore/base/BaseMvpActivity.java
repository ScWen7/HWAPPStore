package io.scwen7.hwappstore.base;

import android.os.Bundle;

import io.scwen7.hwappstore.presenter.BasePresenter;


/**
 * Created by 解晓辉  on 2017/6/10 14:34 *
 * QQ  ：811733738
 * 作用: 公共的Mvp Activity 继承与BaseActivity
 * <p>
 * getLayoutId()   -> initView()  -> createPresenter() ->initData()
 * <p>
 * 生命周期  onDestory-> 中包含  presenter 的 detachView 和 presenter的置空
 */

public abstract class BaseMvpActivity<P extends BasePresenter> extends BaseActivity {

    protected P mPresenter;


    @Override
    protected void initData(Bundle savedInstanceState) {
        mPresenter = createPresenter();
        initMvpData(savedInstanceState);
    }

    protected abstract void initMvpData(Bundle savedInstanceState);


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachView();
            mPresenter = null;
        }
    }

    /**
     * 创建 Presenter
     *
     * @return
     */
    public abstract P createPresenter();
}

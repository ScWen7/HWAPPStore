package io.scwen7.hwappstore.base;


import io.scwen7.hwappstore.presenter.BasePresenter;

/**
 * Created by 解晓辉  on 2017/6/10 14:52 *
 * QQ  ：811733738
 * 作用:   封装完成之后方法的调用顺序为:
 * getLayoutId()   -> initView()  -> createPresenter() -> intiData()
 * <p>
 * 生命周期  onDestoryView -> 中包含  presenter 的 detachView 和 presenter的置空
 */

public abstract class BaseMvpFragment<P extends BasePresenter> extends BaseFragment {


    protected P mPresenter;


    @Override
    protected void initData() {
        mPresenter = createPresenter();
        initMvpData();
    }


    protected abstract void initMvpData();

    @Override
    public void onDestroyView() {
        super.onDestroyView();
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

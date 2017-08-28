package io.scwen7.hwappstore.base;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by 解晓辉 on 2017/2/16.
 * 作用：公共的Fragment的基类
 * 完成一些公共的功能
 */

public abstract class BaseFragment extends Fragment {

    private static final String STATE_SAVE_IS_HIDDEN = "STATE_SAVE_IS_HIDDEN";


    protected Activity mActivity;

    protected AppStoreApplication mApplication;

    protected View mRootView;

    protected Unbinder mUnbinder;


    protected CompositeDisposable disposables;


    /**
     * Fragment 对应的视图
     */
    private ViewGroup rootView;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = inflater.inflate(getLayoutId(), container, false);
        mActivity = getActivity();
        initStatusLayout(mRootView);
        mUnbinder = ButterKnife.bind(this, mRootView);
        return mRootView;
    }


    /**
     * 子类如果需要多状态布局，需要重写此方法来配置多状态布局
     * 子类需要执行两部操作
     * Step1 初始化StatusLayout 配置多状态布局
     * Step2 添加到 ContentView的相应位置中
     *
     * @param rootView
     */
    public void initStatusLayout(View rootView) {

    }


    @Override
    public void onDestroyView() {
        if (disposables != null) {
            disposables.dispose();
            disposables = null;
        }
        if (mUnbinder != null) {
            mUnbinder.unbind();
            mUnbinder = null;
        }
        super.onDestroyView();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        mApplication = (AppStoreApplication) mActivity.getApplication();
        initData();
    }


    /**
     * 预留出 初始化视图的方法
     */
    protected void initView() {
    }

    ;

    /**
     * 子类需要提供布局ID
     *
     * @return activity对应的  布局id
     */
    protected abstract int getLayoutId();


    public <T extends Activity> T getParentActivity() {
        return (T) mActivity;
    }


    public void addRx(Disposable disposable) {
        if (disposables == null) {
            disposables = new CompositeDisposable();
        }
        disposables.add(disposable);
    }


    public void removeRx(Disposable disposable) {
        if (disposables == null) {
            return;
        }
        disposables.remove(disposable);

    }


    /**
     * 初始化Fragment时
     *
     * @param savedInstanceState
     */
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState != null) {
            boolean isSupportHidden = savedInstanceState.getBoolean(STATE_SAVE_IS_HIDDEN);

            FragmentTransaction ft = getFragmentManager().beginTransaction();
            if (isSupportHidden) {
                ft.hide(this);
            } else {
                ft.show(this);
            }
            ft.commit();
        }

    }


    /**
     * 子类通过该方法来完成数据的初始化
     */
    protected abstract void initData();


    /**
     * 跳转Activity
     *
     * @param clazz
     */
    public void startActivity(Class clazz) {
        Intent intent = new Intent(mActivity, clazz);
        mActivity.startActivity(intent);
    }

    public ViewGroup getRootView() {
        return rootView;
    }


    public void hideInput(View view) {
        InputMethodManager imm = (InputMethodManager) mApplication.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putBoolean(STATE_SAVE_IS_HIDDEN, isHidden());
    }
}

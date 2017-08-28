package io.scwen7.hwappstore.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by 解晓辉 on 2017/2/16.
 * 作用： 公共的Activity  的基类
 */

public abstract class BaseActivity extends AppCompatActivity {


    private Unbinder mUnbinder;


    protected AppStoreApplication mApplication;


    protected CompositeDisposable disposables;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置视图
        setContentView(getLayoutId());

        initStatusLayout();
        //视图的绑定
        mUnbinder = ButterKnife.bind(this);
        initView();
        mApplication = (AppStoreApplication) getApplication();
        ActivityManager.addActivity(this);
        initData(savedInstanceState);
    }


    public void hideInput(View view) {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }


    /**
     * 子类需要重写该方法来提供视图
     *
     * @return
     */
    protected abstract int getLayoutId();

    /**
     * 子类需要重写该方法进行数据的初始化
     *
     * @param savedInstanceState
     */
    protected abstract void initData(Bundle savedInstanceState);


    /**
     * 子类如果需要多状态布局，需要重写此方法来配置多状态布局
     * 子类需要执行两部操作
     * Step1 初始化StatusLayout 配置多状态布局
     * Step2 添加到 ContentView的相应位置中
     */
    public void initStatusLayout() {

    }


    /**
     * 初始化操作
     */
    protected void initView() {
    }

    ;


    public void startActivity(Class<? extends Activity> clazz) {
        startActivity(new Intent(this, clazz));
    }

    @Override
    protected void onDestroy() {
        ActivityManager.removeActivity(this);
        if (disposables != null) {
            disposables.dispose();
            disposables = null;
        }

        if (mUnbinder != null) {
            mUnbinder.unbind();
            mUnbinder = null;
        }
        super.onDestroy();
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

}

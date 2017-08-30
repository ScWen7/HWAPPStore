package io.scwen7.hwappstore.ui.activity;

import android.os.Bundle;

import butterknife.OnClick;
import io.scwen7.hwappstore.R;
import io.scwen7.hwappstore.base.BaseActivity;
import io.scwen7.hwappstore.common.utils.SPUtils;

public class SplashActivity extends BaseActivity {


    @Override
    protected int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

        boolean isSecond = SPUtils.getBoolean(this, "isSecond");

        if (isSecond) {
            startActivity(MainActivity.class);
            finish();
        }

    }

    @OnClick(R.id.enter_button)
    public void onClick() {
        //进入引导页
        SPUtils.putBoolean(this, "isSecond", true);
        startActivity(GuideActivity.class);
        finish();
    }
}

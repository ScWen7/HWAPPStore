package io.scwen7.hwappstore.ui.activity;

import android.os.Bundle;
import android.view.View;

import io.scwen7.hwappstore.R;
import io.scwen7.hwappstore.base.BaseActivity;
import io.scwen7.hwappstore.common.utils.SPUtils;

public class SplashActivity extends BaseActivity implements View.OnClickListener{


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

        findViewById(R.id.enter_button).setOnClickListener(this);

    }


    @Override
    public void onClick(View view) {
        //进入引导页
        SPUtils.putBoolean(this, "isSecond", true);
        startActivity(GuideActivity.class);
        finish();
    }
}

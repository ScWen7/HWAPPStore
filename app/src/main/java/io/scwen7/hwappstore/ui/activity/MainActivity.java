package io.scwen7.hwappstore.ui.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import io.scwen7.hwappstore.R;
import io.scwen7.hwappstore.base.BaseActivity;
import io.scwen7.hwappstore.ui.adapter.MainPagerAdapter;
import io.scwen7.hwappstore.ui.fragment.CatrgoryFragment;
import io.scwen7.hwappstore.ui.fragment.ManageFragment;
import io.scwen7.hwappstore.ui.fragment.RankFragment;
import io.scwen7.hwappstore.ui.fragment.RecoFragment;
import io.scwen7.hwappstore.ui.fragment.UserFragment;

public class MainActivity extends BaseActivity {

    @BindView(R.id.viewpager_main)
    ViewPager mViewpagerMain;
    @BindView(R.id.tab_main)
    TabLayout mTabMain;

    private List<Fragment> mFragments;

    private String[] titles = {"推荐", "分类", "排行", "管理", "我的"};

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

        initFragments();

        MainPagerAdapter mainPagerAdapter = new MainPagerAdapter(getSupportFragmentManager(), mFragments, titles);
        mViewpagerMain.setAdapter(mainPagerAdapter);
        mTabMain.setupWithViewPager(mViewpagerMain);

    }

    private void initFragments() {
        mFragments = new ArrayList<>();
        mFragments.add(new RecoFragment());
        mFragments.add(new CatrgoryFragment());
        mFragments.add(new RankFragment());
        mFragments.add(new ManageFragment());
        mFragments.add(new UserFragment());
    }

}



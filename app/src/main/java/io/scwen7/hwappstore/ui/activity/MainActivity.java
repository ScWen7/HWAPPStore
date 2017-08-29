package io.scwen7.hwappstore.ui.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import io.scwen7.hwappstore.R;
import io.scwen7.hwappstore.base.BaseActivity;
import io.scwen7.hwappstore.common.utils.UIUtils;
import io.scwen7.hwappstore.ui.adapter.MainPagerAdapter;
import io.scwen7.hwappstore.ui.fragment.CatrgoryFragment;
import io.scwen7.hwappstore.ui.fragment.ManageFragment;
import io.scwen7.hwappstore.ui.fragment.RankFragment;
import io.scwen7.hwappstore.ui.fragment.RecoFragment;
import io.scwen7.hwappstore.ui.fragment.UserFragment;
import io.scwen7.hwappstore.weight.StatusBarUtil;

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

        StatusBarUtil.setTranslucentForImageView(this, 0, mTabMain);


        initFragments();

        MainPagerAdapter mainPagerAdapter = new MainPagerAdapter(getSupportFragmentManager(), mFragments, titles);
        mViewpagerMain.setAdapter(mainPagerAdapter);
        mTabMain.setupWithViewPager(mViewpagerMain);


        mViewpagerMain.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == 0) {
                    mTabMain.setBackgroundColor(Color.TRANSPARENT);
                    mTabMain.setSelectedTabIndicatorColor(Color.WHITE);
                    mTabMain.setTabTextColors(Color.WHITE, Color.WHITE);
                } else {
                    mTabMain.setBackgroundColor(Color.WHITE);
                    mTabMain.setSelectedTabIndicatorColor(UIUtils.getColor(R.color.colorPrimaryDark));
                    mTabMain.setTabTextColors(UIUtils.getColor(R.color.textgray), UIUtils.getColor(R.color.colorPrimaryDark));
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

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



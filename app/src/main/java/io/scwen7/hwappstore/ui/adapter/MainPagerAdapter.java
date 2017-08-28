package io.scwen7.hwappstore.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * Created by 解晓辉 on 2017/8/28.
 * 作用：
 */

public class MainPagerAdapter extends FragmentStatePagerAdapter {
    private List<Fragment> mFragments;

    private String[] titles;

    public MainPagerAdapter(FragmentManager fm, List<Fragment> fragments, String[] titles) {
        super(fm);
        this.mFragments = fragments;
        this.titles = titles;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}

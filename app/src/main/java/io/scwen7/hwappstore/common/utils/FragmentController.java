package io.scwen7.hwappstore.common.utils;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;


/**
 * 主界面Fragment控制器
 */
public class FragmentController {

    private int containerId;
    private FragmentManager fm;
    private List<Fragment> fragments;

    private Fragment preFragment;
    private ViewGroup mViewGroup;
   


    public  FragmentController(FragmentActivity activity, int containerId, List<Fragment> fragments, ViewGroup viewGroup) {
        this.containerId = containerId;
        fm = activity.getSupportFragmentManager();
        this.fragments = fragments;
        mViewGroup = viewGroup;
        addFragments();
        setListsner();
    }

    private void addFragments() {
        FragmentTransaction ft = fm.beginTransaction();
        for (int i = 0; i < fragments.size(); i++) {
            Fragment fragment = fragments.get(i);
            if(fragment.isAdded()) {
                continue;
            }
            ft.add(containerId, fragments.get(i), "" + i);
            ft.hide(fragment);
        }
        ft.commit();
    }

    private View lastSelectedIcon;
    private View lastSelectedText;
    private void setListsner() {
        for (int i = 0; i < mViewGroup.getChildCount(); i++) {

            View childAt = mViewGroup.getChildAt(i);
            childAt.setTag(i);
            childAt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (lastSelectedIcon != null) lastSelectedIcon.setSelected(false);
                    if (lastSelectedText != null) lastSelectedText.setSelected(false);

                    RelativeLayout rl = (RelativeLayout) v;
                    ImageView icon = (ImageView) rl.getChildAt(0);
                    TextView text = (TextView) rl.getChildAt(1);
                    Integer position = (Integer) v.getTag();
                    showFragment(position);
                    setSelectIcon(icon, text);
                }
            });
        }
    }

    private void setSelectIcon(ImageView iv, TextView tv) {
        iv.setSelected(true);
        tv.setSelected(true);
        lastSelectedIcon = iv;
        lastSelectedText = tv;
    }


    public void setCurrentTab(int position){
        showFragment(position);
        View childAt = mViewGroup.getChildAt(position);
        RelativeLayout rl = (RelativeLayout) childAt;
        ImageView icon = (ImageView) rl.getChildAt(0);
        TextView text = (TextView) rl.getChildAt(1);
        setSelectIcon(icon,text);
    }

    public void showFragment( int position) {
        FragmentTransaction ft = fm.beginTransaction();
        Fragment toFragment = swiFragment(position);
        if(preFragment!= toFragment) {
            if(preFragment!=null) {
                ft.hide(preFragment);
            }
            if(toFragment!=null) {
                ft.show(toFragment);
            }
            ft.commit();
        }
        preFragment = toFragment;
    }

    private Fragment swiFragment(int position) {
        if (fragments != null && fragments.size() > 0) {
            return fragments.get(position);
        }
        return null;
    }


}
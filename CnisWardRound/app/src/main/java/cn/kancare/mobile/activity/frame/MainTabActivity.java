package cn.kancare.mobile.activity.frame;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;

import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;

import java.util.ArrayList;

import cn.kancare.mobile.R;
import cn.kancare.mobile.common.constant.LogTag;
import os.zxs.force.core.App;
import os.zxs.force.core.bridge.CallBackListener;
import os.zxs.force.core.util.ViewFindUtils;
import os.zxs.force.core.view.activity.BaseActivity;

public class MainTabActivity extends BaseActivity{
    private Context mContext = this;
    private ArrayList<Fragment> mFragments = new ArrayList<Fragment>();
    private String[] mTitles = {"患者列表", "数据同步", "设置"};

    private int[] mIconUnselectIds = {
            R.drawable.patient_list_unselected, R.drawable.sync_unselected,
            R.drawable.setting_unselected};
    private int[] mIconSelectIds = {
            R.drawable.patient_list_selected, R.drawable.sync_selected,
            R.drawable.setting_selected};
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<CustomTabEntity>();
    private View mDecorView;
    private ViewPager mViewPager;
    private CommonTabLayout mTabLayout_1;

    protected String getLogTag() {
        return LogTag.CNIS_LOG;
    }

    protected int getLayoutId() {
        return R.layout.frame_main_tab;
    }

    protected void receiveIntent(Intent intent) throws Exception {

    }

    protected void initializeBo() throws Exception {

    }

    protected void setView() throws Exception {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.frame_main_tab);

        mFragments.add(new PatientListFragment());
        mFragments.add(new SyncFragment());
        mFragments.add(new SettingFragment());


        for (int i = 0; i < mTitles.length; i++) {
            mTabEntities.add(new os.zxs.force.tablayoutsamples.entity.TabEntity(mTitles[i], mIconSelectIds[i], mIconUnselectIds[i]));
        }

        mDecorView = getWindow().getDecorView();
        mViewPager = ViewFindUtils.find(mDecorView, os.zxs.force.R.id.vp_2);
        mViewPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));
        /** with nothing */
        mTabLayout_1 = ViewFindUtils.find(mDecorView, os.zxs.force.R.id.tl_1);
        /** with ViewPager */

        mTabLayout_1.setTabData(mTabEntities);
        tl_2();

    }

    private class MyPagerAdapter extends FragmentPagerAdapter {
        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitles[position];
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }
    }

    private void tl_2() {
        mTabLayout_1.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                mViewPager.setCurrentItem(position);
                execCallBack(position);
            }

            @Override
            public void onTabReselect(int position) {
            }
        });

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mTabLayout_1.setCurrentTab(position);
                execCallBack(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        mViewPager.setCurrentItem(0);
    }

    //点击患者列表选项卡时，触发回调
    private void execCallBack(int position){
        if(position == 0){
            Fragment fragment = mFragments.get(position);
            CallBackListener callBackListener = (CallBackListener) fragment;
            callBackListener.doCallBack();
        }
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK
                && event.getAction() == KeyEvent.ACTION_DOWN
                && event.getRepeatCount() == 0) {

            App.toExit(this);

            return true;
        }

        return super.dispatchKeyEvent(event);
    }
}

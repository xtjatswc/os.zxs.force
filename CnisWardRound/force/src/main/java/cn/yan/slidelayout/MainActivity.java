package cn.yan.slidelayout;

import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.widget.ListView;

import os.zxs.force.R;

public class MainActivity extends FragmentActivity {
    private ListView mListView;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mListView = (ListView) this.findViewById(R.id.listview_id);
        DemoListAdapter adapter = new DemoListAdapter();
        mListView.setAdapter(adapter);

        mViewPager = (ViewPager) this.findViewById(R.id.viewpager);
        DemoPageAdapter pageAdapter = new DemoPageAdapter(this);
        mViewPager.setAdapter(pageAdapter);
    }
}

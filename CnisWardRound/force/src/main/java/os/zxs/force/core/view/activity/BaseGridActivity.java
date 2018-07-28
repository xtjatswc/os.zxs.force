package os.zxs.force.core.view.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;

import os.zxs.force.core.view.Loading;
import os.zxs.force.core.view.base.GridListAdapter;
import os.zxs.force.core.view.base.IGridList;
import os.zxs.force.core.view.base.PaginationAdapter;

public abstract class BaseGridActivity<Bean> extends BaseActivity implements IGridList<Bean>{
	protected GridView gridView;

	protected PaginationAdapter<Bean> adapter;
	protected GridListAdapter<Bean> gridListAdapter;
	public Activity getTheActivity() {
		return this;
	}

	public void handleException(Exception e) {
		super.doException(e);
	}

	// 选择行的时候是否改变颜色
	public Boolean isSelectedChangeColor() {
		return adapter.isSelectedChangeColor();
	}

	// 得到选中行的颜色
	public int getSelectedColor() {
		return adapter.getSelectedColor();// 金色
	}

	// 得到未选中行的颜色
	public int getUnSelectedColor() {
		return adapter.getUnSelectedColor();// 透明
	}
	
	public AbsListView getAbsListView() {
		return gridView;
	}

	@Override
	public void onCreate(Bundle paramBundle) {
		super.onCreate(paramBundle);

		gridView = (GridView) findViewById(getListId());
		initData();
        gridListAdapter = new GridListAdapter<Bean>(this);
		gridListAdapter.initFinish();
	}

    public PaginationAdapter getPaginationAdapter() {
        return adapter;
    }

	public void onListItemClick(Bean data) {
		adapter.setCurrentItem(data);
	}

	private void initData(){
		adapter = new PaginationAdapter<Bean>(this);
		gridView.setAdapter(adapter);
	}
}

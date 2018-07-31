package os.zxs.force.core.view.fragment;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;

import os.zxs.force.core.view.Loading;
import os.zxs.force.core.view.base.GridListAdapter;
import os.zxs.force.core.view.base.IGridList;
import os.zxs.force.core.view.base.PaginationAdapter;

public abstract class BaseListFragment<Bean> extends BaseFragment implements
		IGridList<Bean> {

	protected ListView listView;
	protected PaginationAdapter<Bean> adapter;
	protected GridListAdapter<Bean> gridListAdapter;

	public Activity getTheActivity() {
		return getActivity();
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
		return listView;
	}

	public void handleException(Exception e) {
		super.doException(e);
	}

	public PaginationAdapter getPaginationAdapter() {
		return adapter;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View layoutView = super.onCreateView(inflater, container,
				savedInstanceState);

		listView = (ListView) layoutView.findViewById(getListId());
		initData();
		gridListAdapter = new GridListAdapter<Bean>(this);
		gridListAdapter.initFinish();

		return layoutView;
	}

	public void onListItemClick(Bean data) {
		adapter.setCurrentItem(data);
	}

	private void initData(){
		adapter = new PaginationAdapter<Bean>(this);
		listView.setAdapter(adapter);
	}

	public Boolean isShowLoading(){return true;}

	public Boolean isTopRefresh(){return false;}

}

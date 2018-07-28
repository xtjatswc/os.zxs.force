package os.zxs.force.core.view.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
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

public abstract class BaseListActivity<Bean> extends BaseActivity implements
		IGridList<Bean> {

	protected ListView listView;
	protected PaginationAdapter<Bean> adapter;
    protected GridListAdapter<Bean> gridListAdapter;

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

	protected void setOnListItemSubClick(final View item, final View widget, final int position,
										 final View subView)throws Exception {
		adapter.setCurrentItem(adapter.getItem(position));
		subView.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				try {
					onListItemSubClick(item, widget,
							position, subView.getId());
				} catch (Exception e) {
					doException(e);
				}
			}
		});
	}
	public PaginationAdapter getPaginationAdapter() {
		return adapter;
	}

	public Activity getTheActivity() {
		return this;
	}
	public AbsListView getAbsListView() {
		return listView;
	}

	public void handleException(Exception e) {
		super.doException(e);
	}

	@Override
	public void onCreate(Bundle paramBundle) {
		super.onCreate(paramBundle);

		listView = (ListView) findViewById(getListId());
		initData();
		gridListAdapter = new GridListAdapter<Bean>(this);
		listView.setOnScrollListener(gridListAdapter);
        gridListAdapter.refreshList();

		// 条目点击事件
		listView.setOnItemClickListener(new ItemClickListener());
	}

	public void onListItemClick(Bean data) {
		adapter.setCurrentItem(data);
	}

	// 获取点击事件
	private final class ItemClickListener implements OnItemClickListener {

		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			//final ListView listView = (ListView) parent;
			final Bean data = (Bean) parent.getItemAtPosition(position);

			onListItemClick(data);

			if (isSelectedChangeColor()) {
				adapter.notifyDataSetInvalidated();
			}

			return;
		}
	}

	private void initData(){
		adapter = new PaginationAdapter(this);
		listView.setAdapter(adapter);
	}
}

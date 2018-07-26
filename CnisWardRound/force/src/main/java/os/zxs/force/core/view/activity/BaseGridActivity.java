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
import os.zxs.force.core.view.base.IGridList;
import os.zxs.force.core.view.base.PaginationAdapter;

public abstract class BaseGridActivity<Bean> extends BaseActivity   implements
		AbsListView.OnScrollListener , IGridList<Bean>{
	protected GridView gridView;
	protected int visibleLastIndex = 0; // 最后的可视项索引

	protected PaginationAdapter<Bean> adapter;

	protected abstract int getPageSize();

	//offset 不是页数，是跳过的记录行数
	protected abstract List<Bean> getMoreData(int pageSize, int offset) throws Exception;

	protected abstract int getGridId();


	public Activity getTheActivity() {
		return this;
	}

	public void doException(Exception e) {
		doException(e);
	}

	// 选择行的时候是否改变颜色
	public Boolean isSelectedChangeColor() {
		return false;
	}

	// 得到选中行的颜色
	public int getSelectedColor() {
		return 0xFFED9516;// 金色
	}

	// 得到未选中行的颜色
	public int getUnSelectedColor() {
		return 0x80ffffff;// 透明
	}

	// 行元素点击事件
	protected abstract void onGridItemSubClick(View item, View widget, int position,
											   int which) throws Exception;

	protected void setOnGridItemSubClick(final View item, final View widget, final int position,
										 final View subView)throws Exception {
		adapter.setCurrentItem(adapter.getItem(position));
		subView.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				try {
					onGridItemSubClick(item, widget,
							position, subView.getId());
				} catch (Exception e) {
					doException(e);
				}
			}
		});
	}

	@Override
	public void onCreate(Bundle paramBundle) {
		super.onCreate(paramBundle);

		gridView = (GridView) findViewById(getGridId());
		initData();
		refreshList();
		gridView.setOnScrollListener(this);

		// 条目点击事件
		gridView.setOnItemClickListener(new ItemClickListener());
	}

	protected void onListItemClick(Bean data) {
		adapter.setCurrentItem(data);
	}

	// 获取点击事件
	private final class ItemClickListener implements OnItemClickListener {

		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
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
		gridView.setAdapter(adapter);
	}

	protected void refreshList() {
		Loading.turn(this);

		try {
			List<Bean> list = getMoreData(getPageSize(), 0);
			adapter.setItems(list);
		} catch (Exception e) {
			doException(e);
		}
		adapter.notifyDataSetChanged();
		gridView.setSelection(0);//直接返回顶部，不带滑动效果
		Loading.turnoff();

	}
	
	protected void removeAndRefresh() {
		adapter.removeItem(adapter.getCurrentItem());
		adapter.notifyDataSetChanged();
	}

	public void onScrollStateChanged(AbsListView view, int scrollState) {
		int itemsLastIndex = adapter.getCount() - 1; // 数据集最后一项的索引
		int lastIndex = itemsLastIndex;
		if (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE
				&& visibleLastIndex == lastIndex) {
			Loading.turn(this);
			// 如果是自动加载,可以在这里放置异步加载数据的代码
			int count = adapter.getCount();
			List<Bean> list = null;
			try {
				list = getMoreData(getPageSize(), count);
			} catch (Exception e) {
				doException(e);
			}
			if (list != null) {
				for (Bean model : list) {
					adapter.addItem(model);
				}
				adapter.notifyDataSetChanged();
			}
			Loading.turnoff();
		}
	}

	public void onScroll(AbsListView view, int firstVisibleItem,
						 int visibleItemCount, int totalItemCount) {
		visibleLastIndex = firstVisibleItem + visibleItemCount - 1;
	}

}

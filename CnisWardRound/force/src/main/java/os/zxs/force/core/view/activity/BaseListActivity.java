package os.zxs.force.core.view.activity;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;

public abstract class BaseListActivity<Bean> extends BaseActivity implements
		OnScrollListener {

	protected ListView listView;
	protected int visibleLastIndex = 0; // 最后的可视项索引
	protected PaginationAdapter adapter;

	protected abstract List<Bean> getInitializeData() throws Exception;

	protected abstract List<Bean> getMoreData(int listCount) throws Exception;

	protected abstract int getListId();

	protected abstract int getListItemLayoutId();

	protected abstract void setListItemView(int position, View view, Bean data,
			ViewGroup parent);

	// 选择行的时候是否改变颜色
	protected Boolean isSelectedChangeColor() {
		return false;
	}

	// 得到选中行的颜色
	protected int getSelectedColor() {
		return 0xFFED9516;// 金色
	}

	// 得到未选中行的颜色
	protected int getUnSelectedColor() {
		return 0x80ffffff;// 透明
	}

	// 行元素点击事件
	protected void onListItemSubClick(View item, View widget, int position,
			int which) {
		adapter.setCurrentItem(adapter.getItem(position));
	}

	protected abstract void setViewHolder(View view);

	@Override
	public void onCreate(Bundle paramBundle) {
		super.onCreate(paramBundle);

		listView = (ListView) findViewById(getListId());
		refreshList();
		listView.setOnScrollListener(this);

		// 条目点击事件
		listView.setOnItemClickListener(new ItemClickListener());
	}

	protected void onListItemClick(Bean data) {
		adapter.setCurrentItem(data);
	}

	// 获取点击事件
	private final class ItemClickListener implements OnItemClickListener {

		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			final ListView listView = (ListView) parent;
			final Bean data = (Bean) listView.getItemAtPosition(position);

			onListItemClick(data);

			if (isSelectedChangeColor()) {
				adapter.notifyDataSetInvalidated();
			}

			return;
		}
	}

	protected void refreshList() {
		List<Bean> list = null;
		try {
			list = getInitializeData();
		} catch (Exception e) {
			doException(e);
		}
		if (list == null || list.size() == 0) {
			list = new ArrayList<Bean>();
		}
		adapter = new PaginationAdapter(list);
		if (list != null && adapter != null) {
			listView.setAdapter(adapter);
		}
	}

	protected void removeAndRefresh() {
		adapter.removeItem(adapter.getCurrentItem());
		adapter.notifyDataSetChanged();
	}

	public void onScrollStateChanged(AbsListView view, int scrollState) {
		int itemsLastIndex = adapter.getCount() - 1; // 数据集最后一项的索引
		int lastIndex = itemsLastIndex;
		if (scrollState == OnScrollListener.SCROLL_STATE_IDLE
				&& visibleLastIndex == lastIndex) {
			// 如果是自动加载,可以在这里放置异步加载数据的代码
			int count = adapter.getCount();
			List<Bean> list = null;
			try {
				list = getMoreData(count);
			} catch (Exception e) {
				doException(e);
			}
			if (list != null) {
				for (Bean model : list) {
					adapter.addItem(model);
				}
				adapter.notifyDataSetChanged();
			}
		}
	}

	public void onScroll(AbsListView view, int firstVisibleItem,
			int visibleItemCount, int totalItemCount) {
		visibleLastIndex = firstVisibleItem + visibleItemCount - 1;
	}

	protected class PaginationAdapter extends BaseAdapter {

		List<Bean> items;

		Bean currentItem;

		public void setCurrentItem(Bean item) {
			currentItem = item;
		}

		public Bean getCurrentItem() {
			return currentItem;
		}

		public PaginationAdapter(List<Bean> items) {
			this.items = items;
		}

		public int getCount() {
			return items.size();
		}

		public Bean getItem(int position) {
			return items.get(position);
		}

		public long getItemId(int position) {
			return position;
		}

		public View getView(int position, View view, ViewGroup parent) {
			if (view == null) {
				view = getLayoutInflater().inflate(getListItemLayoutId(), null);
				setViewHolder(view);
			}

			Bean data = items.get(position);

			setListItemView(position, view, data, parent);

			if (isSelectedChangeColor()) {
				if (getCurrentItem() == data) {
					view.setBackgroundColor(getSelectedColor());
				} else {
					view.setBackgroundColor(getUnSelectedColor());
				}
			}

			return view;
		}

		/**
		 * 添加数据列表项
		 * 
		 * @param item
		 */
		public void addItem(Bean item) {
			items.add(item);
		}

		/**
		 * 移除数据列表项
		 * 
		 * @param item
		 */
		public void removeItem(Bean item) {
			items.remove(item);
		}

		/**
		 * 获取所有数据列表项
		 * 
		 * @param item
		 */
		public List<Bean> getItems() {
			return items;
		}
	}
}

package os.zxs.force.core.view.fragment;

import java.util.ArrayList;
import java.util.List;

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

public abstract class BaseListFragment<Bean> extends BaseFragment implements
		OnScrollListener {

	protected ListView listView;
	protected int visibleLastIndex = 0; // 最后的可视项索引
	protected PaginationAdapter adapter;

	protected abstract int getPageSize();

	//offset 不是页数，是跳过的记录行数
	protected abstract List<Bean> getMoreData(int pageSize, int offset) throws Exception;

	protected abstract int getListId();

	protected abstract int getListItemLayoutId();

	protected abstract void setListItemView(int position, View view, Bean data,
			ViewGroup parent)  throws Exception;

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
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View layoutView = super.onCreateView(inflater, container,
				savedInstanceState);

		listView = (ListView) layoutView.findViewById(getListId());
		initData();
		refreshList();
		listView.setOnScrollListener(this);

		// 条目点击事件
		listView.setOnItemClickListener(new ItemClickListener());

		return layoutView;
	}

	protected void onListItemClick(Bean data) {
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
		adapter = new PaginationAdapter();
		listView.setAdapter(adapter);
	}

	protected void refreshList() {
		Loading.turn(getContext());

		try {
			List<Bean> list = getMoreData(getPageSize(), 0);
			adapter.setItems(list);
		} catch (Exception e) {
			doException(e);
		}
		adapter.notifyDataSetChanged();
		listView.setSelection(0);//直接返回顶部，不带滑动效果
		Loading.turnoff();
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
			Loading.turn(this.getActivity());
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

	protected class PaginationAdapter extends BaseAdapter {

		List<Bean> items = new ArrayList<Bean>();

		Bean currentItem;

		public void setCurrentItem(Bean item) {
			currentItem = item;
		}

		public Bean getCurrentItem() {
			return currentItem;
		}

		public PaginationAdapter() {

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
				view = getActivity().getLayoutInflater().inflate(
						getListItemLayoutId(), null);
				setViewHolder(view);
			}

			Bean data = items.get(position);

			try {
				setListItemView(position, view, data, parent);
			} catch (Exception e) {
				doException(e);
			}

			if (isSelectedChangeColor()) {
				if (getCurrentItem() == data) {
					view.setBackgroundColor(getSelectedColor());
				} else {
					view.setBackgroundColor(getUnSelectedColor());
				}
			}

			return view;
		}

		public void addItem(Bean item) {
			items.add(item);
		}

		public void removeItem(Bean item) {
			items.remove(item);
		}

		public List<Bean> getItems() {
			return items;
		}
		
		public  void setItems(List<Bean> items){
			this.items = items;
		}
	}
}

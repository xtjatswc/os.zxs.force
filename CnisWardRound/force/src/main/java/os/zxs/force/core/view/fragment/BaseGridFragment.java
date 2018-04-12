package os.zxs.force.core.view.fragment;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;

public abstract class BaseGridFragment<Bean> extends BaseFragment {
	protected GridView gridView;

	protected MyAdapter adapter;

	protected abstract List<Bean> getInitializeData() throws Exception;

	protected abstract int getGridId();

	protected abstract int getGridItemLayoutId();

	protected abstract void setGridItemView(int position, View view, Bean data,
			ViewGroup parent) throws Exception;

	// 行元素点击事件
	protected void onGridItemSubClick(View item, View widget, int position,
			int which) {
		adapter.setCurrentItem(adapter.getItem(position));
	}

	protected abstract void setViewHolder(View view);

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View layoutView = super.onCreateView(inflater, container,
				savedInstanceState);

		gridView = (GridView) layoutView.findViewById(getGridId());
		refreshList();

		// 条目点击事件
		gridView.setOnItemClickListener(new ItemClickListener());

		return layoutView;
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
		adapter = new MyAdapter(list);
		if (list != null && adapter != null) {
			gridView.setAdapter(adapter);
		}
	}

	protected void removeAndRefresh() {
		adapter.removeItem(adapter.getCurrentItem());
		adapter.notifyDataSetChanged();
	}

	/**
	 * 
	 * @author Administrator
	 * 
	 */
	public class MyAdapter extends BaseAdapter {

		private List<Bean> items;

		Bean currentItem;

		public void setCurrentItem(Bean item) {
			currentItem = item;
		}

		public Bean getCurrentItem() {
			return currentItem;
		}

		public MyAdapter(List<Bean> list) {
			this.items = list;
		}

		public List<Bean> getItems() {
			return items;
		}

		/**
		 * 数据总数
		 */
		public int getCount() {

			return items.size();
		}

		/**
		 * 获取当前数据
		 */
		public Bean getItem(int position) {

			return items.get(position);
		}

		public long getItemId(int position) {

			return position;
		}

		public View getView(int position, View view, ViewGroup parent) {

			if (view == null) {
				view = getActivity().getLayoutInflater().inflate(
						getGridItemLayoutId(), null);
				setViewHolder(view);
			}

			Bean data = items.get(position);

			try {
				setGridItemView(position, view, data, parent);
			} catch (Exception e) {
				doException(e);
			}

			return view;
		}

		/**
		 * 移除数据列表项
		 * 
		 * @param item
		 */
		public void removeItem(Bean item) {
			items.remove(item);
		}
	}
}

package os.zxs.force.core.view.fragment;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
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

public abstract class BaseGridFragment<Bean> extends BaseFragment  implements IGridList<Bean> {
	protected GridView gridView;

	protected PaginationAdapter<Bean> adapter;

	public Activity getTheActivity() {
		return getActivity();
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
	
	protected void setOnGridItemSubClick(final View item, final View widget, final int position,
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

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View layoutView = super.onCreateView(inflater, container,
				savedInstanceState);

		gridView = (GridView) layoutView.findViewById(getListId());
		initData();
		refreshList();
		GridListAdapter<Bean> gridListAdapter = new GridListAdapter<Bean>(this);
		gridView.setOnScrollListener(gridListAdapter);

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
			if (isSelectedChangeColor()) {
				adapter.notifyDataSetInvalidated();
			}
			return;
		}
	}

	private void initData(){
		adapter = new PaginationAdapter<Bean>(this);
		gridView.setAdapter(adapter);
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
		gridView.setSelection(0);//直接返回顶部，不带滑动效果
		Loading.turnoff();
	}
	public AbsListView getAbsListView() {
		return gridView;
	}
}

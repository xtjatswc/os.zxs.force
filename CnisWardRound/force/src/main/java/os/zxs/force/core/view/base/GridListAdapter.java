package os.zxs.force.core.view.base;

import android.widget.AbsListView;

import java.util.List;

import os.zxs.force.core.view.Loading;

public class GridListAdapter<Bean> implements
        AbsListView.OnScrollListener{
    IGridList iGridList;
    protected int visibleLastIndex = 0; // 最后的可视项索引

    public GridListAdapter(IGridList iGridList){
        this.iGridList = iGridList;
    }

    public void onScrollStateChanged(AbsListView view, int scrollState) {
        int itemsLastIndex = iGridList.getPaginationAdapter().getCount() - 1; // 数据集最后一项的索引
        int lastIndex = itemsLastIndex;
        if (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE
                && visibleLastIndex == lastIndex) {
            Loading.turn(iGridList.getTheActivity());
            // 如果是自动加载,可以在这里放置异步加载数据的代码
            int count = iGridList.getPaginationAdapter().getCount();
            List<Bean> list = null;
            try {
                list = iGridList.getMoreData(iGridList.getPageSize(), count);
            } catch (Exception e) {
                iGridList.handleException(e);
            }
            if (list != null) {
                for (Bean model : list) {
                    iGridList.getPaginationAdapter().addItem(model);
                }
                iGridList.getPaginationAdapter().notifyDataSetChanged();
            }
            Loading.turnoff();
        }
    }

    public void onScroll(AbsListView view, int firstVisibleItem,
                         int visibleItemCount, int totalItemCount) {
        visibleLastIndex = firstVisibleItem + visibleItemCount - 1;
    }

    public void refreshList() {
        Loading.turn(iGridList.getTheActivity());

        try {
            List<Bean> list = iGridList.getMoreData(iGridList.getPageSize(), 0);
            iGridList.getPaginationAdapter().setItems(list);
        } catch (Exception e) {
            iGridList.handleException(e);
        }
        iGridList.getPaginationAdapter().notifyDataSetChanged();
        iGridList.getAbsListView().setSelection(0);//直接返回顶部，不带滑动效果
        Loading.turnoff();
    }

    public void removeAndRefresh() {
        iGridList.getPaginationAdapter().removeItem(iGridList.getPaginationAdapter().getCurrentItem());
        iGridList.getPaginationAdapter().notifyDataSetChanged();
    }

}

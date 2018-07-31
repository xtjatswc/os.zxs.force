package os.zxs.force.core.view.base;

import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;

import java.util.List;

import os.zxs.force.core.view.Loading;

public class GridListAdapter<Bean> implements
        AbsListView.OnScrollListener{
    IGridList<Bean> iGridList;
    private int lastVisibleItemPosition = 0;// 标记上次滑动位置，初始化默认为0
	private int visibleLastIndex = 0; // 最后的可视项索引
    private boolean scrollFlag = false;// 标记是否滑动
    private boolean isUP = true;

    public GridListAdapter(IGridList iGridList){
        this.iGridList = iGridList;
    }

    public void onScrollStateChanged(AbsListView view, int scrollState) {
        //判断状态
        switch (scrollState) {
            // 当不滚动时
            case AbsListView.OnScrollListener.SCROLL_STATE_IDLE:// 是当屏幕停止滚动时
                scrollFlag = false;

                // 判断滚动到顶部
                if (isUP && isFirstAllDisplay()) {
                    refreshList();
                }

                // 判断滚动到底部 、position是从0开始算起的
                if (!isUP && visibleLastIndex == (iGridList.getPaginationAdapter()
                        .getCount() - 1)) {

                    if(iGridList.isShowLoading())
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

                    if(iGridList.isShowLoading())
                        Loading.turnoff();

                }

                break;
            case AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL:// 滚动时
                scrollFlag = true;
                break;
            case AbsListView.OnScrollListener.SCROLL_STATE_FLING:
                // 当用户由于之前划动屏幕并抬起手指，屏幕产生惯性滑动时，即滚动时
                scrollFlag = true;
                break;
        }

    }

    public void onScroll(AbsListView view, int firstVisibleItem,
                         int visibleItemCount, int totalItemCount) {
        //当滑动时
        if (scrollFlag) {
            if (firstVisibleItem < lastVisibleItemPosition) {
                // 上滑
                isUP = true;
            } else if (firstVisibleItem > lastVisibleItemPosition) {
                // 下滑
                isUP = false;
            } else {
                return;
            }
            lastVisibleItemPosition = firstVisibleItem;//更新位置
 			visibleLastIndex = firstVisibleItem + visibleItemCount - 1;
        }

    }

    private Boolean isFirstAllDisplay(){

        if (iGridList.getAbsListView().getChildCount() > 0 &&
                iGridList.getAbsListView().getFirstVisiblePosition() == 0 &&
                iGridList.getAbsListView().getChildAt(0).getTop() >= iGridList.getAbsListView().getPaddingTop()) {
            return true;
        }
        return  false;
    }

    public void refreshList() {

        if(iGridList.isShowLoading())
            Loading.turn(iGridList.getTheActivity());

        try {
            List<Bean> list = iGridList.getMoreData(iGridList.getPageSize(), 0);
            iGridList.getPaginationAdapter().setItems(list);
        } catch (Exception e) {
            iGridList.handleException(e);
        }
        iGridList.getPaginationAdapter().notifyDataSetChanged();
        iGridList.getAbsListView().setSelection(0);//直接返回顶部，不带滑动效果

        if(iGridList.isShowLoading())
            Loading.turnoff();
    }

    public void removeAndRefresh() {
        iGridList.getPaginationAdapter().removeItem(iGridList.getPaginationAdapter().getCurrentItem());
        iGridList.getPaginationAdapter().notifyDataSetChanged();
    }

    // 获取点击事件
    public class ItemClickListener implements AdapterView.OnItemClickListener {

        public void onItemClick(AdapterView<?> parent, View view, int position,
                                long id) {
            final Bean data = (Bean) parent.getItemAtPosition(position);

            iGridList.onListItemClick(data);
            if (iGridList.isSelectedChangeColor()) {
                iGridList.getPaginationAdapter().notifyDataSetInvalidated();
            }
            return;
        }
    }

    public void initFinish(){
        iGridList.getAbsListView().setOnScrollListener(this);
        refreshList();

        // 条目点击事件
        iGridList.getAbsListView().setOnItemClickListener(new GridListAdapter.ItemClickListener());

    }

    public void setOnListItemSubClick(final View item, final ViewGroup parent, final int position,
                                      final View subView, final Bean data)throws Exception {
        iGridList.getPaginationAdapter().setCurrentItem(iGridList.getPaginationAdapter().getItem(position));
        subView.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                try {
//                  Bean data = (Bean)iGridList.getPaginationAdapter()
//                            .getItem(position);

                    iGridList.onListItemSubClick(item, parent,
                            position, subView.getId(), data);
                } catch (Exception e) {
                    iGridList.handleException(e);
                }
            }
        });
    }

}

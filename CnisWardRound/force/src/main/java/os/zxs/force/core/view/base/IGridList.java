package os.zxs.force.core.view.base;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;

import java.util.List;

public interface IGridList<Bean> {

    Activity getTheActivity();

    void setListItemView(int position, View view, Bean data,
                                            ViewGroup parent)  throws Exception;

    int getListItemLayoutId();

    void setViewHolder(View view);

    void handleException(Exception e);
    Boolean isSelectedChangeColor();

    int getSelectedColor();

    int getUnSelectedColor();

    int getListId();

    int getPageSize();

    //offset 不是页数，是跳过的记录行数
    List<Bean> getMoreData(int pageSize, int offset) throws Exception;

    // 行元素点击事件
    void onListItemSubClick(View item, ViewGroup parent, int position,
                                               int which, Bean data) throws Exception;

    PaginationAdapter getPaginationAdapter();

    AbsListView getAbsListView();

    void onListItemClick(Bean data);

    Boolean isShowLoading();

    Boolean isTopRefresh();
}

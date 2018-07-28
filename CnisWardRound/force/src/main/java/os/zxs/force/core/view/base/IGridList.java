package os.zxs.force.core.view.base;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public interface IGridList<Bean> {
    Activity getTheActivity();

    void setListItemView(int position, View view, Bean data,
                                            ViewGroup parent)  throws Exception;

    int getListItemLayoutId();

    void setViewHolder(View view);

    void doException(Exception e);

    Boolean isSelectedChangeColor();

    int getSelectedColor();

    int getUnSelectedColor();

    int getListId();

    int getPageSize();

    //offset 不是页数，是跳过的记录行数
    List<Bean> getMoreData(int pageSize, int offset) throws Exception;

    // 行元素点击事件
    void onListItemSubClick(View item, View widget, int position,
                                               int which) throws Exception;

    PaginationAdapter getPaginationAdapter();

}

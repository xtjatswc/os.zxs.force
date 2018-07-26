package os.zxs.force.core.view.base;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;

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
}

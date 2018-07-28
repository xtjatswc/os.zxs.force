package os.zxs.force.core.view.base;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

public class PaginationAdapter<Bean> extends BaseAdapter {
    List<Bean> items = new ArrayList<Bean>();

    Bean currentItem;
    IGridList<Bean> iGridList;

    // 选择行的时候是否改变颜色
    public Boolean isSelectedChangeColor(){ return false;}

    // 得到选中行的颜色
    public int getSelectedColor(){ return 0xFFED9516;}// 金色

    // 得到未选中行的颜色
    public int getUnSelectedColor (){ return 0x80ffffff;}// 透明

    public void setCurrentItem(Bean item) {
        currentItem = item;
    }

    public Bean getCurrentItem() {
        return currentItem;
    }

    public PaginationAdapter(IGridList iGridList) {
        this.iGridList = iGridList;
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
            view = iGridList.getTheActivity().getLayoutInflater().inflate(
                    iGridList.getListItemLayoutId(), null);
            iGridList.setViewHolder(view);
        }

        Bean data = items.get(position);

        try {
            iGridList.setListItemView(position, view, data, parent);
        } catch (Exception e) {
            iGridList.handleException(e);
        }

        if (iGridList.isSelectedChangeColor()) {
            if (getCurrentItem() == data) {
                view.setBackgroundColor(iGridList.getSelectedColor());
            } else {
                view.setBackgroundColor(iGridList.getUnSelectedColor());
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
        if(items == null)
            items = new ArrayList<Bean>();
        this.items = items;
    }
}

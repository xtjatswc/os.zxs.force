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
            iGridList.doException(e);
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
        this.items = items;
    }
}

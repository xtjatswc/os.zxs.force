package os.zxs.force.core.util;

import android.util.SparseArray;
import android.view.View;

@SuppressWarnings({ "unchecked" })
public class ViewFindUtils
{

	public static <T extends View> T hold(View view, int id)
	{
		return hold(view, id, null);
	}

	/**
	 * ViewHolder简洁写法,避免适配器中重复定义ViewHolder,减少代码量 用法:
	 * 
	 * <pre>
	 * if (convertView == null)
	 * {
	 * 	convertView = View.inflate(context, R.layout.ad_demo, null);
	 * }
	 * TextView tv_demo = ViewHolderUtils.hold(convertView, R.id.tv_demo);
	 * ImageView iv_demo = ViewHolderUtils.hold(convertView, R.id.iv_demo);
	 * </pre>
	 */
	public static <T extends View> T hold(View view, int id, HoldCallBack holdCallBack)
	{
		SparseArray<View> viewHolder = (SparseArray<View>) view.getTag();

		if (viewHolder == null)
		{
			viewHolder = new SparseArray<View>();
			view.setTag(viewHolder);
		}

		View childView = viewHolder.get(id);

		if (childView == null)
		{
			childView = view.findViewById(id);
			viewHolder.put(id, childView);
			if(holdCallBack != null){
				holdCallBack.doInit(childView);
			}
		}

		return (T) childView;
	}

	public interface HoldCallBack{
		void doInit(View view);
	}

	/**
	 * 替代findviewById方法
	 */
	public static <T extends View> T find(View view, int id)
	{
		return (T) view.findViewById(id);
	}
}

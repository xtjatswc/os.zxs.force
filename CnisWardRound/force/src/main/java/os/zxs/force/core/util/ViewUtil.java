package os.zxs.force.core.util;

import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;

public class ViewUtil {

	// 得到灰色分隔线
	public static ImageView getDividerView(final Activity activity) {
		ImageView iv = new ImageView(activity);
		iv.setBackgroundColor(0x7f05005e);
		LayoutParams layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT,
				1);
		iv.setLayoutParams(layoutParams);
		iv.setBackgroundColor(Color.rgb(220, 220, 220));
		return iv;
	}

	public static View getBlankView(final Activity activity, int height) {
		View view = new View(activity);
		LayoutParams layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT,
				height);
		view.setLayoutParams(layoutParams);
		return view;
	}
}

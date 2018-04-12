package os.zxs.force.core.util;

import android.content.res.ColorStateList;
import android.content.res.Resources;

public class ColorUtil {

	public static ColorStateList getColor(int colorRes) {
		Resources resource = (Resources) ContextUtil.getInstance()
				.getBaseContext().getResources();
		ColorStateList csl = (ColorStateList) resource
				.getColorStateList(colorRes);
		return csl;
	}
}

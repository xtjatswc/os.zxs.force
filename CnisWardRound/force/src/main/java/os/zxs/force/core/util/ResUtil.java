package os.zxs.force.core.util;

import java.lang.reflect.Field;

import os.zxs.force.common.Global;

public class ResUtil {

	private Class<?> drawableCls;

	public ResUtil() throws ClassNotFoundException {
		drawableCls = Class.forName(Global.packageName + ".R$drawable");
	}

	private static ResUtil resUtil;

	public static ResUtil getInstanse() throws ClassNotFoundException {
		if (resUtil == null) {
			resUtil = new ResUtil();
		}
		return resUtil;
	}

	public int getDrawableId(String resName) throws Exception {
		Field field = drawableCls.getField(resName);
		return field.getInt(field);
	}
}

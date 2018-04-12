package os.zxs.force.common;

import android.app.Activity;

import os.zxs.force.core.App;
import os.zxs.force.core.constant.AppConstant;

public class Global{

	public static Activity currentActivity;

	public static String packageName = App
			.getAppInfo(AppConstant.GET_PACKAGE_NAME);
	public static String rootPackageName = "os.zxs.force";

	// 设备编号
	public static String MachineNo = "";
}

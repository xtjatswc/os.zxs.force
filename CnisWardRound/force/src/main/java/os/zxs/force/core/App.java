package os.zxs.force.core;

import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import os.zxs.force.core.constant.AppConstant;
import os.zxs.force.core.util.ContextUtil;
import os.zxs.force.core.util.PopUtil;

public class App {

	// 是否是调试模式
	public static Boolean iS_DEBUG = false;

	/**
	 * 　　* 获取版本号 　　* @return 当前应用的版本号 　　
	 */
	public static String getAppInfo(String tag) {
		Context context = ContextUtil.getInstance();
		PackageManager manager = context.getPackageManager();
		PackageInfo info = null;
		try {
			info = manager.getPackageInfo(context.getPackageName(), 0);
		} catch (NameNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String appInfo = "";
		if (tag == AppConstant.GET_VERSION) {
			appInfo = info.versionName;
		} else if (tag == AppConstant.GET_PACKAGE_NAME) {
			appInfo = info.packageName;
		}
		return appInfo;
	}

	public static void toExit(Context context) {
		PopUtil.AlertDialog(context, "提示", "确定退出系统？", "确定",
				new DialogInterface.OnClickListener() {

					public void onClick(DialogInterface dialog, int whichButton) {

						// finish();//
						android.os.Process.killProcess(android.os.Process
								.myPid()); // 获取PID
						System.exit(0); // 常规java、c#的标准退出法，返回值为0代表正常退出
					}

				});
	}
}

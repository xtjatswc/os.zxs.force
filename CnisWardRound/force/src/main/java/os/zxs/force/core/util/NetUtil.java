package os.zxs.force.core.util;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;

public class NetUtil {
	public static String getLocalIpAddress() {
		String ip = "0.0.0.0";
		try {

			// 获取wifi服务
			WifiManager wifiManager = (WifiManager) ContextUtil.getInstance().getApplicationContext()
					.getSystemService(Context.WIFI_SERVICE);
			// 判断wifi是否开启
			if (!wifiManager.isWifiEnabled()) {
				wifiManager.setWifiEnabled(true);
			}
			WifiInfo wifiInfo = wifiManager.getConnectionInfo();
			int ipAddress = wifiInfo.getIpAddress();
			ip = intToIp(ipAddress);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return ip;
	}

	private static String intToIp(int i) {

		return (i & 0xFF) + "." + ((i >> 8) & 0xFF) + "." + ((i >> 16) & 0xFF)
				+ "." + (i >> 24 & 0xFF);
	}
}

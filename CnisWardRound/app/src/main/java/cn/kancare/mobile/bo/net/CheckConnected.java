package cn.kancare.mobile.bo.net;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.ImageView;
import cn.kancare.mobile.R;
import cn.kancare.mobile.common.Global;
import cn.kancare.mobile.common.constant.LogTag;
import os.zxs.force.core.util.Convert;
import os.zxs.force.core.util.HttpUtil;

import com.loopj.android.http.HttpGet;

public class CheckConnected {
	ImageView imageView = null;
	private int TIME = 10000; // 毫秒

	public CheckConnected(ImageView imageView) {
		this.imageView = imageView;
	}

	// handler类接收数据
	Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			if (msg.what == 1) {
				imageView.setImageResource(R.drawable.wifi_connected);
			} else if (msg.what == 2) {
				imageView.setImageResource(R.drawable.wifi_disconnected);
			}
		};
	};

	// 线程类
	class ThreadShow implements Runnable {

		public void run() {
			Message msg = null;
			String requestIp = Global.WEB_REQUEST_IP;
			String url = "http://" + requestIp
					+ "/webapi/Handler.ashx?opt=testwebconn";
			HttpGet httpGet = new HttpGet(url);

			String result = "";

			while (true) {
				try {
					if (requestIp != Global.WEB_REQUEST_IP) {
						requestIp = Global.WEB_REQUEST_IP;
						url = "http://" + requestIp
								+ "/webapi/Handler.ashx?opt=testwebconn";
						httpGet = new HttpGet(url);
					}

					msg = new Message();
					// 测试网络
					HttpResponse httpResponse = new DefaultHttpClient()
							.execute(httpGet);
					result = "";
					if (httpResponse.getStatusLine().getStatusCode() == 200) {
						result = EntityUtils.toString(httpResponse.getEntity());
					}
					if (result.equals("ok")) {
						msg.what = 1;
					} else {
						msg.what = 2;
					}

				} catch (Exception e) {
					msg.what = 2;
					Log.e(LogTag.CHECK_WIFI, e.getMessage());
				} finally {
					handler.sendMessage(msg);
					try {
						Thread.sleep(TIME);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
	}

	// 启动定时器
	public void Start() {
		new Thread(new ThreadShow()).start();
	}

	/**
	 * 测试与cnis后台是否连通
	 * 
	 * @return
	 */
	public static Boolean TestCnisWebConn() {
		String url = "http://" + Global.WEB_REQUEST_IP
				+ "/webapi/Handler.ashx?opt=testconn";
		try {
			String str = HttpUtil.doHttpGet(url);
			return HttpUtil.doHttpGet(url).equals("ok");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
		}

		return false;
	}
	
	/**
	 * 获取服务端版本
	 * 
	 * @return
	 */
	public static int getServerVersion() {
		String url = "http://" + Global.WEB_REQUEST_IP
				+ "/webapi/Handler.ashx?opt=getversion";
		try {
			String str = HttpUtil.doHttpGet(url);
			return Convert.cash2Int(HttpUtil.doHttpGet(url));

		} catch (IOException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
		}

		return -1;
	}
}

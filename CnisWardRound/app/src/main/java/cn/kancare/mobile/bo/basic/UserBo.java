package cn.kancare.mobile.bo.basic;

import java.sql.SQLException;
import java.util.List;

import org.apache.http.Header;

import android.app.Activity;
import android.util.Log;
import cn.kancare.mobile.bean.basic.User;
import cn.kancare.mobile.core.base.BaseBo;
import cn.kancare.mobile.dao.basic.UserDao;

import com.alibaba.fastjson.JSON;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

public class UserBo extends BaseBo<UserDao> {

	public UserBo(Activity context) throws Exception {
		super(context);
	}

	public void doDownloadJson(String json) throws Exception {
		if (json == "")
			return;

		List<User> models = JSON.parseArray(json, User.class);

		for (User model : models) {
			try {
				dao.create(model);
			} catch (Exception e) {
				try {
					dao.update(model);
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
		}

	}

	public String InsertUsersFromWeb() {

		AsyncHttpClient client = new AsyncHttpClient();
		client.get("http://172.16.3.32:8089/webapi/user.ashx",
				new AsyncHttpResponseHandler() {
					@Override
					public void onSuccess(int statusCode, Header[] headers,
							byte[] responseBody) {
						try {
							List<User> users = JSON.parseArray(new String(
									responseBody), User.class);

							for (User u : users) {
								dao.create(u);
							}
						} catch (Exception e) {
							e.printStackTrace();
						}
					}

					@Override
					public void onFailure(int statusCode, Header[] headers,
							byte[] responseBody, Throwable error) {
						// TODO Auto-generated method stub
						Log.i("cnis", new String(responseBody));
					}

				});

		return "";
	}

	@Override
	protected void setDao() throws Exception {
		dao = new UserDao(context);
	}

	@Override
	public void doUploadResult(String json) throws Exception {
		// TODO Auto-generated method stub

	}

}

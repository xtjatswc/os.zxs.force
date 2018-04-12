package cn.kancare.mobile.activity.frame;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import cn.kancare.mobile.R;
import cn.kancare.mobile.common.constant.LogTag;
import os.zxs.force.core.util.AssetUtil;
import os.zxs.force.core.view.activity.BaseActivity;

public class VersionActivity extends BaseActivity {

	TextView TextViewVersion;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		try {
			String version = AssetUtil.getContent("template/version.txt");
			TextViewVersion.setText(version);
		} catch (Exception e) {
			doException(e);
		}

	}

	@Override
	protected String getLogTag() {
		return LogTag.VERSION;
	}

	@Override
	protected int getLayoutId() {
		return R.layout.frame_version;
	}

	@Override
	protected void receiveIntent(Intent intent) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	protected void initializeBo() throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	protected void setView() throws Exception {
		TextViewVersion = (TextView) findViewById(R.id.TextViewVersion);
	}

}

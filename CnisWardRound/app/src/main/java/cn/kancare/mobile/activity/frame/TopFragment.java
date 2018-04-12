package cn.kancare.mobile.activity.frame;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import cn.kancare.mobile.R;
import cn.kancare.mobile.activity.CnisLogListActivity;
import cn.kancare.mobile.bo.net.CheckConnected;
import cn.kancare.mobile.common.Global;
import cn.kancare.mobile.common.constant.LogTag;
import os.zxs.force.core.App;
import os.zxs.force.core.bridge.CallBackListener;
import os.zxs.force.core.event.Multihit;
import os.zxs.force.core.view.fragment.BaseFragment;

public class TopFragment extends BaseFragment {

	CheckConnected connected;
	Multihit multihit;

	ImageView wifiImageView;
	ImageView logoutImageView;
	TextView txtCurrentUser;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View topLayout = super.onCreateView(inflater, container,
				savedInstanceState);

		// 开启检查网络连接线程
		connected = new CheckConnected(wifiImageView);
		connected.Start();

		// 恐怖三连击，调出日志列表
		multihit = new Multihit(3, new CallBackListener() {

			public void doCallBack() {
				Intent i = new Intent(TopFragment.this.getActivity(),
						CnisLogListActivity.class);
				startActivity(i);
			}
		});

		txtCurrentUser.setText(Global.loginUser.getUserName());

		wifiImageView.setOnClickListener(new onClickListener());
		logoutImageView.setOnClickListener(new onClickListener());

		return topLayout;
	}

	class onClickListener implements View.OnClickListener {

		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.wifi_image:
				multihit.listen();
				break;
			case R.id.logout_image:
				App.toExit(TopFragment.this.getActivity());
				break;
			default:
				break;
			}
		}

	}

	@Override
	protected String getLogTag() {
		return LogTag.FRAME_TOP;
	}

	@Override
	protected void initializeBo() throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	protected int getLayoutId() {
		return R.layout.frame_top;
	}

	@Override
	protected void setView(View layout) throws Exception {
		wifiImageView = (ImageView) layout.findViewById(R.id.wifi_image);
		logoutImageView = (ImageView) layout.findViewById(R.id.logout_image);
		txtCurrentUser = (TextView) layout.findViewById(R.id.txtCurrentUser);
	}
}

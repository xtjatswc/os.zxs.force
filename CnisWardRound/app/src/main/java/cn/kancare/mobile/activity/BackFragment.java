package cn.kancare.mobile.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import cn.kancare.mobile.R;
import cn.kancare.mobile.common.Global;
import cn.kancare.mobile.common.constant.LogTag;
import os.zxs.force.core.view.fragment.BaseFragment;
import os.zxs.force.core.view.fragment.OnBackListener;

public class BackFragment extends os.zxs.force.activity.BackFragment {
	OnBackListener onBackListener;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View layout = super.onCreateView(inflater, container,
				savedInstanceState);

		try {
			if (Global.currentPatient == null) {
				textViewTitle.setText("");
			} else {
				textViewTitle.setText("患者："
						+ Global.currentPatient.getPatientName());
			}
		} catch (Exception e) {
			doException(e);
		}

		return layout;
	}

	@Override
	protected String getLogTag() {
		return LogTag.BACK;
	}

	@Override
	protected void initializeBo() throws Exception {
		// TODO Auto-generated method stub

	}
}
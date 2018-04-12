package cn.kancare.mobile.activity.mealrecord.fragment;

import java.util.List;

import android.app.ActionBar.LayoutParams;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import cn.kancare.mobile.R;
import cn.kancare.mobile.bean.basic.SysCode;
import cn.kancare.mobile.bo.basic.SysCodeBo;
import cn.kancare.mobile.common.constant.LogTag;
import cn.kancare.mobile.common.constant.SysCodeType;
import cn.kancare.mobile.common.mealrecord.MealRecordListener.MealRecordInfoListener;
import cn.kancare.mobile.common.mealrecord.MealRecordListener.MealRecordTmealListener;
import os.zxs.force.core.util.ColorUtil;
import os.zxs.force.core.view.fragment.BaseFragment;

public class MealRecordTmealFragment extends BaseFragment implements
		MealRecordTmealListener {

	MealRecordInfoListener mealRecordInfoListener;

	String tmeal;
	SysCodeBo sysCodeBo;

	LinearLayout LinearLayoutButtons;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View layout = super.onCreateView(inflater, container,
				savedInstanceState);

		try {

			List<SysCode> lstCodes = sysCodeBo.getDao()
					.query(SysCodeType.TMEAL);
			LinearLayoutButtons.removeAllViews();
			for (SysCode sysCode : lstCodes) {
				Button btn = new Button(getActivity());
				btn.setText(sysCode.getSysCodeName());
				btn.setId(sysCode.getSystemCode_DBKEY());
				btn.setTextColor(ColorUtil.getColor(R.color.white));
				btn.setTextSize(18);
				LayoutParams layoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
				btn.setLayoutParams(layoutParams);
				btn.setBackgroundResource(R.drawable.gray);

				btn.setOnClickListener(new onClickListener());
				btn.setTag(sysCode);
				LinearLayoutButtons.addView(btn);
			}

			if (LinearLayoutButtons.getChildCount() > 0) {
				LinearLayoutButtons.getChildAt(0).setBackgroundResource(
						R.drawable.blue);
				tmeal = lstCodes.get(0).getSysCode();
			}

		} catch (Exception e) {
			doException(e);
		}

		return layout;
	}

	class onClickListener implements OnClickListener {

		public void onClick(View v) {

			// 先保存
			mealRecordInfoListener.saveMealRecord();

			int count = LinearLayoutButtons.getChildCount();
			for (int i = 0; i < count; i++) {
				View view = LinearLayoutButtons.getChildAt(i);
				if (view instanceof Button) {
					LinearLayoutButtons.getChildAt(i).setBackgroundResource(
							R.drawable.gray);
				}
			}
			v.setBackgroundResource(R.drawable.blue);
			SysCode sysCode = (SysCode) v.getTag();
			tmeal = sysCode.getSysCode();
			mealRecordInfoListener.loadMealRecord();
		}

	}

	@Override
	protected String getLogTag() {
		return LogTag.CURD_MEALRECORD;
	}

	@Override
	protected void initializeBo() throws Exception {
		sysCodeBo = new SysCodeBo(getActivity());

	}

	@Override
	protected int getLayoutId() {
		return R.layout.mealrecord_fragment_tmeal;
	}

	@Override
	protected void setView(View layout) throws Exception {
		LinearLayoutButtons = (LinearLayout) layout
				.findViewById(R.id.LinearLayoutButtons);

		mealRecordInfoListener = (MealRecordInfoListener) getActivity();

	}

	public String getTmeal() {
		return tmeal;
	}
}
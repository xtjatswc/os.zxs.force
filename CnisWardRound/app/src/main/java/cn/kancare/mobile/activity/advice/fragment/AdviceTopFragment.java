package cn.kancare.mobile.activity.advice.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import cn.kancare.mobile.R;
import cn.kancare.mobile.bean.advice.NutrientAdviceSummary;
import cn.kancare.mobile.bo.advice.NutrientAdviceSummaryBo;
import cn.kancare.mobile.common.advice.AdviceListener.AdviceTopListener;
import cn.kancare.mobile.common.constant.LogTag;
import os.zxs.force.core.view.DatePickerView;
import os.zxs.force.core.view.fragment.BaseFragment;

public class AdviceTopFragment extends BaseFragment implements
		AdviceTopListener {

	NutrientAdviceSummaryBo nutrientAdviceSummaryBo;

	DatePickerView textBeginDate;
	DatePickerView textEndDate;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View layout = super.onCreateView(inflater, container,
				savedInstanceState);

		try {

		} catch (Exception e) {
			doException(e);
		}

		return layout;
	}

	@Override
	protected String getLogTag() {
		return LogTag.CURD_ADVICE;
	}

	@Override
	protected void initializeBo() throws Exception {
		nutrientAdviceSummaryBo = new NutrientAdviceSummaryBo(getActivity());

	}

	@Override
	protected int getLayoutId() {
		return R.layout.advice_top;
	}

	@Override
	protected void setView(View layout) throws Exception {
		textBeginDate = (DatePickerView) layout
				.findViewById(R.id.textBeginDate);
		textEndDate = (DatePickerView) layout.findViewById(R.id.textEndDate);
	}

	public String[] getAdviceDate() {
		return new String[] { textBeginDate.getText().toString(),
				textEndDate.getText().toString() };
	}

	public void setAdviceDate(String NutrientAdviceSummary_DBKey) throws Exception {
		NutrientAdviceSummary nutrientAdviceSummary = nutrientAdviceSummaryBo
				.getDao().queryForId(NutrientAdviceSummary_DBKey);
		textBeginDate.setText(nutrientAdviceSummary.getAdviceBeginDate());
		textEndDate.setText(nutrientAdviceSummary.getAdviceEndDate());

	}

}

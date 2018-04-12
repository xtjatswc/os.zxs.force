package cn.kancare.mobile.activity.advice;

import android.content.Intent;
import android.os.Bundle;
import cn.kancare.mobile.R;
import cn.kancare.mobile.bean.basic.ChinaFoodComposition;
import cn.kancare.mobile.bo.advice.NutrientAdviceSummaryBo;
import cn.kancare.mobile.common.advice.AdviceListener.AdviceInfoListener;
import cn.kancare.mobile.common.advice.AdviceListener.AdviceInputListener;
import cn.kancare.mobile.common.advice.AdviceListener.AdviceTopListener;
import cn.kancare.mobile.common.advice.AdviceListener.NutrientProductListener;
import cn.kancare.mobile.common.constant.LogTag;
import os.zxs.force.core.view.activity.BaseActivity;

public class AdviceInfoActivity extends BaseActivity implements
		AdviceInfoListener {

	String NutrientAdviceSummary_DBKey;
	NutrientAdviceSummaryBo nutrientAdviceSummaryBo;

	NutrientProductListener nutrientProductListener;
	AdviceInputListener adviceInputListener;
	AdviceTopListener adviceTopListener;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		try {
			if (NutrientAdviceSummary_DBKey != null) {
				nutrientProductListener
						.refreshProductStatus(NutrientAdviceSummary_DBKey);
				adviceInputListener
						.loadNutrientAdviceSummary(NutrientAdviceSummary_DBKey);
				adviceTopListener.setAdviceDate(NutrientAdviceSummary_DBKey);
			}
		} catch (Exception e) {
			doException(e);
		}
	}

	@Override
	protected String getLogTag() {
		return LogTag.CURD_ADVICE;
	}

	@Override
	protected int getLayoutId() {
		return R.layout.advice_info;
	}

	@Override
	protected void receiveIntent(Intent intent) throws Exception {

		NutrientAdviceSummary_DBKey = intent
				.getStringExtra("NutrientAdviceSummary_DBKey");

	}

	@Override
	protected void initializeBo() throws Exception {
		nutrientAdviceSummaryBo = new NutrientAdviceSummaryBo(this);

	}

	@Override
	protected void setView() throws Exception {
		nutrientProductListener = (NutrientProductListener) getFragmentManager()
				.findFragmentById(R.id.fragment_product);
		adviceInputListener = (AdviceInputListener) getFragmentManager()
				.findFragmentById(R.id.fragment_advice_input);
		adviceTopListener = (AdviceTopListener) getFragmentManager()
				.findFragmentById(R.id.fragment_advice_top);

	}

	public void ProductChange(ChinaFoodComposition chinaFoodComposition) {
		adviceInputListener.ProductChange(chinaFoodComposition);
	}

	public String[] getAdviceDate() {
		return adviceTopListener.getAdviceDate();
	}

	public void refreshProductStatus(String NutrientAdviceSummary_DBKey)
			throws Exception {
		nutrientProductListener
				.refreshProductStatus(NutrientAdviceSummary_DBKey);

	}

}

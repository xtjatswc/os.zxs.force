package cn.kancare.mobile.activity.advice.fragment;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.app.ActionBar.LayoutParams;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import cn.kancare.mobile.R;
import cn.kancare.mobile.bean.advice.NutrientAdvice;
import cn.kancare.mobile.bean.advice.NutrientAdviceDetail;
import cn.kancare.mobile.bean.advice.NutrientAdviceSummary;
import cn.kancare.mobile.bean.basic.ChinaFoodComposition;
import cn.kancare.mobile.bean.basic.SysCode;
import cn.kancare.mobile.bo.advice.NutrientAdviceBo;
import cn.kancare.mobile.bo.advice.NutrientAdviceDetailBo;
import cn.kancare.mobile.bo.advice.NutrientAdviceSummaryBo;
import cn.kancare.mobile.bo.basic.SysCodeBo;
import cn.kancare.mobile.common.advice.AdviceListener.AdviceInfoListener;
import cn.kancare.mobile.common.advice.AdviceListener.AdviceInputListener;
import cn.kancare.mobile.common.constant.LogTag;
import cn.kancare.mobile.common.constant.SysCodeType;
import os.zxs.force.core.util.Convert;
import os.zxs.force.core.util.DateHelper;
import os.zxs.force.core.util.PopUtil;
import os.zxs.force.core.view.FlowRadioGroup;
import os.zxs.force.core.view.fragment.BaseFragment;

public class AdviceInputFragment extends BaseFragment implements
		AdviceInputListener {

	public AdviceInfoListener adviceInfoListener;

	public NutrientAdviceSummaryBo nutrientAdviceSummaryBo;
	public NutrientAdviceBo nutrientAdviceBo;
	public NutrientAdviceDetailBo nutrientAdviceDetailBo;
	public SysCodeBo sysCodeBo;

	public NutrientAdviceSummary nutrientAdviceSummary;
	public NutrientAdvice nutrientAdvice;
	public ChinaFoodComposition chinaFoodComposition;

	public FlowRadioGroup FlowRadioGroupENTime;
	public LinearLayout LinearLayoutDoTime;
	public LinearLayout LinearLayoutAdviceInput;
	public FlowRadioGroup FlowRadioGroupPreparationMode;
	public FlowRadioGroup FlowRadioGroupDirections;
	public EditText EditTextEnName;
	public EditText EditTextSpec;
	public EditText EditTextNum;
	public EditText EditTextRemark;
	public Button btnSave;
	public Button btnSave2;
	public Button ButtonDelete;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View layout = super.onCreateView(inflater, container,
				savedInstanceState);

		try {
			loadAdviceInputForm();

			btnSave.setOnClickListener(new ClickListener());
			btnSave2.setOnClickListener(new ClickListener());
			ButtonDelete.setOnClickListener(new ClickListener());

		} catch (Exception e) {
			doException(e);
		}

		return layout;
	}

	// 获取服用方式
	public SysCode getENTime() {
		SysCode sysCode = null;
		for (int i = 0; i < FlowRadioGroupENTime.getChildCount(); i++) {
			if (FlowRadioGroupENTime.getChildAt(i) instanceof RadioButton) {
				RadioButton radioButton = (RadioButton) FlowRadioGroupENTime
						.getChildAt(i);
				if (radioButton.isChecked()) {
					sysCode = (SysCode) radioButton.getTag();
					break;
				}
			}
		}
		return sysCode;
	}

	// 设置服用方式
	public void setENTime(String sysCodeString) {
		SysCode sysCode;
		for (int i = 0; i < FlowRadioGroupENTime.getChildCount(); i++) {
			if (FlowRadioGroupENTime.getChildAt(i) instanceof RadioButton) {
				RadioButton radioButton = (RadioButton) FlowRadioGroupENTime
						.getChildAt(i);
				sysCode = (SysCode) radioButton.getTag();
				if (sysCode.getSysCode().equals(sysCodeString)) {
					radioButton.setChecked(true);
				}
			}
		}
	}

	// 获取制剂方式
	public SysCode getPreparationMode() {
		SysCode sysCode = null;
		for (int i = 0; i < FlowRadioGroupPreparationMode.getChildCount(); i++) {
			if (FlowRadioGroupPreparationMode.getChildAt(i) instanceof RadioButton) {
				RadioButton radioButton = (RadioButton) FlowRadioGroupPreparationMode
						.getChildAt(i);
				if (radioButton.isChecked()) {
					sysCode = (SysCode) radioButton.getTag();
					break;
				}
			}
		}
		return sysCode;
	}

	// 设置制剂方式
	public void setPreparationMode(String sysCodeString) {
		SysCode sysCode = null;
		for (int i = 0; i < FlowRadioGroupPreparationMode.getChildCount(); i++) {
			if (FlowRadioGroupPreparationMode.getChildAt(i) instanceof RadioButton) {
				RadioButton radioButton = (RadioButton) FlowRadioGroupPreparationMode
						.getChildAt(i);
				sysCode = (SysCode) radioButton.getTag();
				if (sysCode.getSysCode().equals(sysCodeString)) {
					radioButton.setChecked(true);
					break;
				}
			}
		}
	}

	// 获取途径
	public SysCode getDirections() {
		SysCode sysCode = null;
		for (int i = 0; i < FlowRadioGroupDirections.getChildCount(); i++) {
			if (FlowRadioGroupDirections.getChildAt(i) instanceof RadioButton) {
				RadioButton radioButton = (RadioButton) FlowRadioGroupDirections
						.getChildAt(i);
				if (radioButton.isChecked()) {
					sysCode = (SysCode) radioButton.getTag();
					break;
				}
			}
		}
		return sysCode;
	}

	// 设置途径
	public void setDirections(String sysCodeString) {
		SysCode sysCode = null;
		for (int i = 0; i < FlowRadioGroupDirections.getChildCount(); i++) {
			if (FlowRadioGroupDirections.getChildAt(i) instanceof RadioButton) {
				RadioButton radioButton = (RadioButton) FlowRadioGroupDirections
						.getChildAt(i);
				sysCode = (SysCode) radioButton.getTag();
				if (sysCode.getSysCode().equals(sysCodeString)) {
					radioButton.setChecked(true);
					break;
				}
			}
		}
	}

	// 获取时间段
	public List<String> getDoTime() {
		List<String> listDoTime = new ArrayList();
		for (int i = 0; i < LinearLayoutDoTime.getChildCount(); i++) {
			if (LinearLayoutDoTime.getChildAt(i) instanceof CheckBox) {
				CheckBox checkBox = (CheckBox) LinearLayoutDoTime.getChildAt(i);
				if (checkBox.isChecked()) {
					listDoTime.add(checkBox.getText().toString());
				}
			}
		}
		return listDoTime;
	}

	// 设置时间段
	public void setDoTime(String TakeOrder) {
		for (int i = 0; i < LinearLayoutDoTime.getChildCount(); i++) {
			if (LinearLayoutDoTime.getChildAt(i) instanceof CheckBox) {
				CheckBox checkBox = (CheckBox) LinearLayoutDoTime.getChildAt(i);
				if (TakeOrder.indexOf(checkBox.getText().toString()) >= 0) {
					checkBox.setChecked(true);
				} else {
					checkBox.setChecked(false);
				}
			}
		}
	}

	@Override
	protected void setValidation() {
		super.setValidation();
		mAwesomeValidation.addValidation(EditTextSpec, "^.{1,12}$", "规格为必填项！");
		mAwesomeValidation.addValidation(EditTextNum, "^.{1,12}$", "数量为必填项！");
	}

	class ClickListener implements OnClickListener {

		public void onClick(View v) {
			try {
				switch (v.getId()) {
				case R.id.ButtonDelete:
					NutrientAdviceDetail nutrientAdviceDetail = nutrientAdviceDetailBo
							.getDao().queryAdviceDetail(
									nutrientAdvice.getNutrientAdvice_DBKey(),
									chinaFoodComposition
											.getRecipeAndProduct_DBKey());

					nutrientAdviceDetailBo.getDao().deleteById(
							nutrientAdviceDetail
									.getNutrientAdviceDetail_DBKEY());

					ButtonDelete.setVisibility(View.GONE);

					// 刷新状态
					adviceInfoListener
							.refreshProductStatus(nutrientAdviceSummary
									.getNutrientAdviceSummary_DBKey());
					break;
				case R.id.btnSave:
				case R.id.btnSave2:
					if (!mAwesomeValidation.validate()) {
						return;
					}

					// 校验服用方式与时间段
					SysCode enSysCode = getENTime();
					int en = Convert.cash2Int(enSysCode.getSysCodeShortName());
					List<String> listDoTime = getDoTime();
					if (en != listDoTime.size()) {
						PopUtil.show(getActivity(), "保存失败，服用方式与时间段不符！");
						return;
					}

					// 校验医嘱开始日期与结束日期
					String[] arrStrings = adviceInfoListener.getAdviceDate();
					Date beginDate = DateHelper.getInstance().getDate(
							arrStrings[0],
							DateHelper.getInstance().date_Formater_2);
					Date endDate = DateHelper.getInstance().getDate(
							arrStrings[1],
							DateHelper.getInstance().date_Formater_2);
					if (beginDate.getTime() > endDate.getTime()) {
						PopUtil.show(getActivity(), "医嘱的开始日期必须小于或等于结束日期！");
						return;
					}

					nutrientAdviceSummaryBo
							.saveAdvice(AdviceInputFragment.this);
					PopUtil.show(AdviceInputFragment.this.getActivity(),
							"保存成功！");

					ButtonDelete.setVisibility(View.VISIBLE);
					// 刷新状态
					adviceInfoListener
							.refreshProductStatus(nutrientAdviceSummary
									.getNutrientAdviceSummary_DBKey());
					break;
				default:
					if (v instanceof RadioButton) {
						RadioButton radioButton = (RadioButton) v;
						SysCode sysCode = (SysCode) radioButton.getTag();

						if (sysCode == null
								|| !sysCode.getSystemCodeTypeName().equals(
										SysCodeType.ENTIME)) {
							return;
						}

						for (int i = 0; i < LinearLayoutDoTime.getChildCount(); i++) {
							if (LinearLayoutDoTime.getChildAt(i) instanceof CheckBox) {
								CheckBox chk = (CheckBox) LinearLayoutDoTime
										.getChildAt(i);
								chk.setChecked(false);
								if (sysCode.getEditType().indexOf(
										chk.getText().toString()) >= 0) {
									chk.setChecked(true);
								}
							}
						}
					}
					break;
				}

			} catch (Exception e) {
				doException(e);
			}
		}

	}

	private void reloadAdviceInputFormValue() throws Exception {

		EditTextNum.setText("1");
		EditTextRemark.setText("无");
		ButtonDelete.setVisibility(View.GONE);

		if (nutrientAdvice == null) {
			return;
		}

		NutrientAdviceDetail nutrientAdviceDetail = nutrientAdviceDetailBo
				.getDao().queryAdviceDetail(
						nutrientAdvice.getNutrientAdvice_DBKey(),
						chinaFoodComposition.getRecipeAndProduct_DBKey());

		if (nutrientAdviceDetail == null)
			return;

		ButtonDelete.setVisibility(View.VISIBLE);

		// 设置服用方式
		setENTime(nutrientAdviceDetail.getAdviceDoTimeSegmental());
		EditTextSpec.setText(nutrientAdviceDetail.getSpecification());
		EditTextNum.setText(nutrientAdviceDetail.getAdviceAmount() + "");

		// 设置时间段
		setDoTime(nutrientAdviceDetail.getTakeOrder());

		// 设置制剂方式
		setPreparationMode(nutrientAdviceDetail.getPreparationMode());
		EditTextRemark.setText(nutrientAdviceDetail
				.getNutrientAdviceDetailRemark());
	}

	private void loadAdviceInputForm() throws Exception {
		List<SysCode> listSysCodes = null;

		EditTextNum.setText("1");

		// 时间段
		listSysCodes = sysCodeBo.getDao().query(SysCodeType.DOTIME);
		for (SysCode sysCode : listSysCodes) {
			CheckBox checkBox = new CheckBox(getActivity());
			checkBox.setText(sysCode.getSysCodeName());
			LinearLayoutDoTime.addView(checkBox);
		}

		// 服用方式
		listSysCodes = sysCodeBo.getDao().query(SysCodeType.ENTIME);
		for (SysCode sysCode : listSysCodes) {
			RadioButton radioButton = new RadioButton(getActivity());
			LayoutParams layoutParams = new LayoutParams(
					LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
			radioButton.setLayoutParams(layoutParams);
			radioButton.setText(sysCode.getSysCodeName());
			FlowRadioGroupENTime.addView(radioButton);
			radioButton.setTag(sysCode);
			radioButton.setOnClickListener(new ClickListener());
			// 默认选中 qd 每日一次
			if (sysCode.getSysCode().equals("9")) {
				radioButton.setChecked(true);
				radioButton.performClick();
			}
		}

		// 制剂方式
		listSysCodes = sysCodeBo.getDao().query(SysCodeType.PREPARATIONMODE);
		for (SysCode sysCode : listSysCodes) {
			RadioButton radioButton = new RadioButton(getActivity());
			LayoutParams layoutParams = new LayoutParams(
					LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
			radioButton.setLayoutParams(layoutParams);
			radioButton.setText(sysCode.getSysCodeName());
			FlowRadioGroupPreparationMode.addView(radioButton);
			radioButton.setTag(sysCode);
			if (sysCode.getSysCodeName().equals("粉剂")) {
				radioButton.setChecked(true);
			}
		}

		// 途径
		listSysCodes = sysCodeBo.getDao().query(SysCodeType.DIRECTIONS);
		for (SysCode sysCode : listSysCodes) {
			RadioButton radioButton = new RadioButton(getActivity());
			LayoutParams layoutParams = new LayoutParams(
					LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
			radioButton.setLayoutParams(layoutParams);
			radioButton.setText(sysCode.getSysCodeName());
			FlowRadioGroupDirections.addView(radioButton);
			radioButton.setTag(sysCode);
			if (sysCode.getSysCodeName().equals("粉剂")) {
				radioButton.setChecked(true);
			}
		}
	}

	@Override
	protected String getLogTag() {
		return LogTag.CURD_ADVICE;
	}

	@Override
	protected void initializeBo() throws Exception {
		nutrientAdviceSummaryBo = new NutrientAdviceSummaryBo(
				this.getActivity());
		sysCodeBo = new SysCodeBo(getActivity());
		nutrientAdviceBo = new NutrientAdviceBo(getActivity());
		nutrientAdviceDetailBo = new NutrientAdviceDetailBo(getActivity());
	}

	@Override
	protected int getLayoutId() {
		return R.layout.advice_input;
	}

	@Override
	protected void setView(View layout) throws Exception {
		FlowRadioGroupENTime = (FlowRadioGroup) layout
				.findViewById(R.id.FlowRadioGroupENTime);
		LinearLayoutDoTime = (LinearLayout) layout
				.findViewById(R.id.LinearLayoutDoTime);
		LinearLayoutAdviceInput = (LinearLayout) layout
				.findViewById(R.id.LinearLayoutAdviceInput);
		FlowRadioGroupPreparationMode = (FlowRadioGroup) layout
				.findViewById(R.id.FlowRadioGroupPreparationMode);
		EditTextEnName = (EditText) layout.findViewById(R.id.EditTextEnName);
		EditTextSpec = (EditText) layout.findViewById(R.id.EditTextSpec);
		EditTextNum = (EditText) layout.findViewById(R.id.EditTextNum);
		EditTextRemark = (EditText) layout.findViewById(R.id.EditTextRemark);
		btnSave = (Button) layout.findViewById(R.id.btnSave);
		btnSave2 = (Button) layout.findViewById(R.id.btnSave2);
		ButtonDelete = (Button) layout.findViewById(R.id.ButtonDelete);
		adviceInfoListener = (AdviceInfoListener) getActivity();
	}

	public void ProductChange(ChinaFoodComposition chinaFoodComposition) {
		try {
			this.chinaFoodComposition = chinaFoodComposition;
			LinearLayoutAdviceInput.setVisibility(View.VISIBLE);
			EditTextEnName.setText(chinaFoodComposition.getFoodName());
			if (chinaFoodComposition.getWrapperType() == 1) {
				EditTextSpec.setEnabled(false);
				EditTextSpec.setText(chinaFoodComposition
						.getNutrientProductSpecification() + "");
			} else {
				EditTextSpec.setEnabled(true);
				EditTextSpec.setText("");
			}

			reloadAdviceInputFormValue();
		} catch (Exception e) {
			doException(e);
		}
	}

	public void loadNutrientAdviceSummary(String NutrientAdviceSummary_DBKey) {
		try {
			nutrientAdviceSummary = nutrientAdviceSummaryBo.getDao()
					.queryForId(NutrientAdviceSummary_DBKey);
			nutrientAdvice = nutrientAdviceBo
					.getDao()
					.queryByNutrientAdviceSummary_DBKey(
							NutrientAdviceSummary_DBKey).get(0);
		} catch (SQLException e) {
			doException(e);
		}

	}

}

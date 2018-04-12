package cn.kancare.mobile.activity.frame;

import java.sql.SQLException;
import java.util.Date;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TableRow;
import android.widget.TextView;
import cn.kancare.mobile.R;
import cn.kancare.mobile.bo.basic.SettingBo;
import cn.kancare.mobile.bo.patient.PatientHospitalizeBasicInfoBo;
import cn.kancare.mobile.common.Global;
import cn.kancare.mobile.common.constant.LogTag;
import cn.kancare.mobile.common.constant.SettingCode;
import cn.kancare.mobile.common.db.DatabaseHelper;
import os.zxs.force.core.util.PopUtil;
import os.zxs.force.core.util.RawDBUtil;
import os.zxs.force.core.view.DatePickerView;
import os.zxs.force.core.view.fragment.BaseFragment;

public class SettingFragment extends BaseFragment {
	SettingBo settingBo;
	PatientHospitalizeBasicInfoBo patientHospitalizeBasicInfoBo;
	RawDBUtil rawDBUtil;

	Button btnVersion;
	EditText editTextServerIP;
	Button btnSaveSetting;
	TextView textViewServerIP;
	RadioButton RadioButtonSingleMode;
	RadioButton RadioButtonInnerNetwork;
	RadioButton RadioButtonDepartment;
	RadioButton RadioButtonBedNumber;
	RadioButton RadioButtonInHospitalDate;
	RadioButton RadioButtonDoctor;
	RadioButton RadioButtonAsc;
	RadioButton RadioButtonDesc;
	RadioButton RadioButtonHide;
	RadioButton RadioButtonDisplay;
	TableRow TableRowMode;
	Button btnDataBack;
	Button btnDataRestore;
	Button btnClear;
	DatePickerView DatePickerViewClearHistoryData;
	EditText EditTextMachineNo;
	TableRow TableRowServerIP;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View settingLayout = super.onCreateView(inflater, container,
				savedInstanceState);

		try {
			rawDBUtil = new RawDBUtil(getActivity(), Global.rootPackageName,
					"cnis" + Global.version,
					new String[] { DatabaseHelper.DATABASE_NAME });

			loadSetting();
			btnVersion.setText("版本号：" + Global.version);
			btnVersion.setOnClickListener(new onClickListener());
			btnSaveSetting.setOnClickListener(new onClickListener());
			btnDataBack.setOnClickListener(new onClickListener());
			btnDataRestore.setOnClickListener(new onClickListener());
			btnClear.setOnClickListener(new onClickListener());
		} catch (Exception e) {
			doException(e);
		}

		return settingLayout;
	}

	private void loadSetting() throws Exception {
		if (Global.loginUser.getUser_DBKey() == 0) {
			TableRowServerIP.setVisibility(View.VISIBLE);
			btnSaveSetting.setVisibility(View.VISIBLE);
			TableRowMode.setVisibility(View.VISIBLE);
			btnDataRestore.setVisibility(View.VISIBLE);
		}

		String appMode = settingBo.getSetting(SettingCode.APP_MODE);
		if (appMode.equals(SettingCode.APP_MODE_SINGLE)) {
			RadioButtonSingleMode.setChecked(true);
		} else {
			RadioButtonInnerNetwork.setChecked(true);
		}

		editTextServerIP.setText(settingBo.getSetting(SettingCode.SERVER_IP));
		EditTextMachineNo.setText(settingBo.getSetting(SettingCode.MACHINE_NO));

		String orderByString = settingBo
				.getSetting(SettingCode.PATIENT_LIST_ORDER_BY);
		if (orderByString.equals(SettingCode.ORDER_BY_DEPARTMENT)) {
			RadioButtonDepartment.setChecked(true);
		} else if (orderByString.equals(SettingCode.ORDER_BY_BEDNUMBER)) {
			RadioButtonBedNumber.setChecked(true);
		} else if (orderByString.equals(SettingCode.ORDER_BY_INHOSPITALDATE)) {
			RadioButtonInHospitalDate.setChecked(true);
		} else if (orderByString.equals(SettingCode.ORDER_BY_DOCTOR)) {
			RadioButtonDoctor.setChecked(true);
		}

		String ascDesc = settingBo
				.getSetting(SettingCode.PATIENT_LIST_ORDER_BY_ASC_DESC);
		if (ascDesc.equals(SettingCode.ORDER_BY_ASC)) {
			RadioButtonAsc.setChecked(true);
		} else if (ascDesc.equals(SettingCode.ORDER_BY_DESC)) {
			RadioButtonDesc.setChecked(true);
		}

		String hideHistoryPatient = settingBo
				.getSetting(SettingCode.HISTORY_PATIENT);
		if (hideHistoryPatient.equals(SettingCode.HISTORY_PATIENT_DISPLAY)) {
			RadioButtonDisplay.setChecked(true);
		} else if (hideHistoryPatient.equals(SettingCode.HISTORY_PATIENT_HIDE)) {
			RadioButtonHide.setChecked(true);
		}

		Date d = new Date();
		d.setDate(d.getDate() - 180);
		DatePickerViewClearHistoryData.setDate(d);

		RadioButtonDepartment
				.setOnCheckedChangeListener(new onCheckedChangeListener());
		RadioButtonBedNumber
				.setOnCheckedChangeListener(new onCheckedChangeListener());
		RadioButtonInHospitalDate
				.setOnCheckedChangeListener(new onCheckedChangeListener());
		RadioButtonDoctor
				.setOnCheckedChangeListener(new onCheckedChangeListener());

		RadioButtonAsc
				.setOnCheckedChangeListener(new onCheckedChangeListener2());
		RadioButtonDesc
				.setOnCheckedChangeListener(new onCheckedChangeListener2());

		RadioButtonDisplay
				.setOnCheckedChangeListener(new onCheckedChangeListener3());
		RadioButtonHide
				.setOnCheckedChangeListener(new onCheckedChangeListener3());

		RadioButtonInnerNetwork
				.setOnCheckedChangeListener(new onCheckedChangeListener4());
		RadioButtonSingleMode
				.setOnCheckedChangeListener(new onCheckedChangeListener4());
	}

	public class onCheckedChangeListener implements OnCheckedChangeListener {

		public void onCheckedChanged(CompoundButton buttonView,
				boolean isChecked) {
			try {
				saveOrderBy();
			} catch (Exception e) {
				doException(e);
			}
		}

	}

	public class onCheckedChangeListener2 implements OnCheckedChangeListener {

		public void onCheckedChanged(CompoundButton buttonView,
				boolean isChecked) {
			try {
				saveAscDesc();
			} catch (Exception e) {
				doException(e);
			}
		}
	}

	public class onCheckedChangeListener3 implements OnCheckedChangeListener {

		public void onCheckedChanged(CompoundButton buttonView,
				boolean isChecked) {
			try {
				saveHideHistoryPatient();
			} catch (Exception e) {
				doException(e);
			}
		}
	}

	public class onCheckedChangeListener4 implements OnCheckedChangeListener {

		public void onCheckedChanged(CompoundButton buttonView,
				boolean isChecked) {
			try {
				saveAppMode();
			} catch (Exception e) {
				doException(e);
			}
		}
	}

	class onClickListener implements View.OnClickListener {
		public void onClick(View v) {
			try {

				switch (v.getId()) {
				case R.id.btnSaveSetting:

					saveAppMode();

					String serverIP = editTextServerIP.getText().toString();
					settingBo.saveSetting(SettingCode.SERVER_IP, serverIP);
					Global.WEB_REQUEST_IP = serverIP;

					saveOrderBy();
					saveAscDesc();

					PopUtil.show(getActivity(), "保存成功！");
					break;
				case R.id.btnDataBack:
					rawDBUtil.backupDB();

					break;
				case R.id.btnDataRestore:
					rawDBUtil.restoreDB();
					break;
				case R.id.btnVersion:
					Intent i = new Intent(getActivity(), VersionActivity.class);
					startActivity(i);
					break;
				case R.id.btnClear:
					ClearHistoryData();
					break;
				default:
					break;
				}
			} catch (Exception e) {
				doException(e);
			}
		}

	}

	private void ClearHistoryData() {
		PopUtil.AlertDialog(this.getActivity(), "请谨慎操作！", "确定清空"
				+ DatePickerViewClearHistoryData.getText().toString()
				+ "前入院的患者信息？", "清空", new DialogInterface.OnClickListener() {

			public void onClick(DialogInterface dialog, int which) {
				try {
					patientHospitalizeBasicInfoBo.getDao()
							.clearHistoryPatient(
									DatePickerViewClearHistoryData.getText()
											.toString());
				} catch (SQLException e) {
					doException(e);
				}
			}

		});
	}

	@Override
	protected String getLogTag() {
		return LogTag.SETTING;
	}

	public void saveAppMode() throws Exception {
		if (RadioButtonSingleMode.isChecked()) {
			settingBo.saveSetting(SettingCode.APP_MODE,
					SettingCode.APP_MODE_SINGLE);
			Global.AppMode = SettingCode.APP_MODE_SINGLE;
		} else {
			settingBo.saveSetting(SettingCode.APP_MODE,
					SettingCode.APP_MODE_INNER);
			Global.AppMode = SettingCode.APP_MODE_INNER;
		}
	}

	public void saveOrderBy() throws Exception {
		if (RadioButtonDepartment.isChecked()) {
			settingBo.saveSetting(SettingCode.PATIENT_LIST_ORDER_BY,
					SettingCode.ORDER_BY_DEPARTMENT);
			Global.PatientListOrderBy = SettingCode.ORDER_BY_DEPARTMENT;
		} else if (RadioButtonBedNumber.isChecked()) {
			settingBo.saveSetting(SettingCode.PATIENT_LIST_ORDER_BY,
					SettingCode.ORDER_BY_BEDNUMBER);
			Global.PatientListOrderBy = SettingCode.ORDER_BY_BEDNUMBER;
		} else if (RadioButtonInHospitalDate.isChecked()) {
			settingBo.saveSetting(SettingCode.PATIENT_LIST_ORDER_BY,
					SettingCode.ORDER_BY_INHOSPITALDATE);
			Global.PatientListOrderBy = SettingCode.ORDER_BY_INHOSPITALDATE;
		} else if (RadioButtonDoctor.isChecked()) {
			settingBo.saveSetting(SettingCode.PATIENT_LIST_ORDER_BY,
					SettingCode.ORDER_BY_DOCTOR);
			Global.PatientListOrderBy = SettingCode.ORDER_BY_DOCTOR;
		}
	}

	public void saveAscDesc() throws Exception {
		if (RadioButtonAsc.isChecked()) {
			settingBo.saveSetting(SettingCode.PATIENT_LIST_ORDER_BY_ASC_DESC,
					SettingCode.ORDER_BY_ASC);
			Global.PatientListOrderByAscDesc = SettingCode.ORDER_BY_ASC;
		} else if (RadioButtonDesc.isChecked()) {
			settingBo.saveSetting(SettingCode.PATIENT_LIST_ORDER_BY_ASC_DESC,
					SettingCode.ORDER_BY_DESC);
			Global.PatientListOrderByAscDesc = SettingCode.ORDER_BY_DESC;
		}
	}

	public void saveHideHistoryPatient() throws Exception {
		if (RadioButtonDisplay.isChecked()) {
			settingBo.saveSetting(SettingCode.HISTORY_PATIENT,
					SettingCode.HISTORY_PATIENT_DISPLAY);
			Global.IsHideHistoryPatient = SettingCode.HISTORY_PATIENT_DISPLAY;
		} else {
			settingBo.saveSetting(SettingCode.HISTORY_PATIENT,
					SettingCode.HISTORY_PATIENT_HIDE);
			Global.IsHideHistoryPatient = SettingCode.HISTORY_PATIENT_HIDE;
		}
	}

	@Override
	protected void initializeBo() throws Exception {
		settingBo = new SettingBo(getActivity());
		patientHospitalizeBasicInfoBo = new PatientHospitalizeBasicInfoBo(
				getActivity());
	}

	@Override
	protected int getLayoutId() {
		return R.layout.frame_setting;
	}

	@Override
	protected void setView(View layout) throws Exception {
		textViewServerIP = (TextView) layout
				.findViewById(R.id.textViewServerIP);
		editTextServerIP = (EditText) layout
				.findViewById(R.id.editTextServerIP);
		btnSaveSetting = (Button) layout.findViewById(R.id.btnSaveSetting);
		btnVersion = (Button) layout.findViewById(R.id.btnVersion);
		RadioButtonBedNumber = (RadioButton) layout
				.findViewById(R.id.RadioButtonBedNumber);
		RadioButtonInHospitalDate = (RadioButton) layout
				.findViewById(R.id.RadioButtonInHospitalDate);
		RadioButtonSingleMode = (RadioButton) layout
				.findViewById(R.id.RadioButtonSingleMode);
		RadioButtonInnerNetwork = (RadioButton) layout
				.findViewById(R.id.RadioButtonInnerNetwork);
		RadioButtonDepartment = (RadioButton) layout
				.findViewById(R.id.RadioButtonDepartment);
		RadioButtonDoctor = (RadioButton) layout
				.findViewById(R.id.RadioButtonDoctor);
		RadioButtonAsc = (RadioButton) layout.findViewById(R.id.RadioButtonAsc);
		RadioButtonHide = (RadioButton) layout
				.findViewById(R.id.RadioButtonHide);
		RadioButtonDisplay = (RadioButton) layout
				.findViewById(R.id.RadioButtonDisplay);
		RadioButtonDesc = (RadioButton) layout
				.findViewById(R.id.RadioButtonDesc);
		TableRowMode = (TableRow) layout.findViewById(R.id.TableRowMode);
		btnDataBack = (Button) layout.findViewById(R.id.btnDataBack);
		btnDataRestore = (Button) layout.findViewById(R.id.btnDataRestore);
		btnClear = (Button) layout.findViewById(R.id.btnClear);
		DatePickerViewClearHistoryData = (DatePickerView) layout
				.findViewById(R.id.DatePickerViewClearHistoryData);
		EditTextMachineNo = (EditText) layout
				.findViewById(R.id.EditTextMachineNo);
		TableRowServerIP = (TableRow) layout
				.findViewById(R.id.TableRowServerIP);

	}

}

package cn.kancare.mobile.activity.patient;

import java.sql.SQLException;
import java.util.List;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import cn.kancare.mobile.R;
import cn.kancare.mobile.activity.advice.AdviceListActivity;
import cn.kancare.mobile.activity.bodyanalysis.BodyAnalysisListActivity;
import cn.kancare.mobile.activity.courserecord.CourseRecordListActivity;
import cn.kancare.mobile.activity.laboratoryindex.LaboratoryIndexListActivity;
import cn.kancare.mobile.activity.mealrecord.MealRecordActivity;
import cn.kancare.mobile.activity.questionnaire.QuestionnaireListActivity;
import cn.kancare.mobile.bean.BodyAnalysisReport;
import cn.kancare.mobile.bean.advice.NutrientAdviceSummary;
import cn.kancare.mobile.bean.basic.SysCode;
import cn.kancare.mobile.bean.laboratoryindex.LaboratoryIndex;
import cn.kancare.mobile.bean.patient.PatientHospitalizeBasicInfo;
import cn.kancare.mobile.bo.BodyAnalysisReportBo;
import cn.kancare.mobile.bo.advice.NutrientAdviceSummaryBo;
import cn.kancare.mobile.bo.basic.SysCodeBo;
import cn.kancare.mobile.bo.laboratoryindex.LaboratoryIndexBo;
import cn.kancare.mobile.bo.patient.PatientHospitalizeBasicInfoBo;
import cn.kancare.mobile.common.Global;
import cn.kancare.mobile.common.constant.LogTag;
import cn.kancare.mobile.common.constant.RequestCode;
import os.zxs.force.common.constant.SyncConstant.OperateFlag;
import os.zxs.force.core.baseanimation.BadgeView;
import os.zxs.force.core.util.Convert;
import os.zxs.force.core.util.DateHelper;
import os.zxs.force.core.util.PopUtil;
import os.zxs.force.core.view.activity.BaseActivity;

public class PatientViewActivity extends BaseActivity {
	PatientHospitalizeBasicInfoBo hospitalInfoBo;
	BodyAnalysisReportBo bodyAnalysisReportBo;
	LaboratoryIndexBo laboratoryIndexBo;
	SysCodeBo sysCodeBo;
	NutrientAdviceSummaryBo nutrientAdviceSummaryBo;
	String PatientHospitalize_DBKey;

	ImageView ImageViewSex;
	TextView textViewPatientName;
	TextView textViewDepartment;
	TextView textViewBedCode;
	TextView textViewClinicistName;
	TextView textViewInHospitalDate;
	TextView textViewAge;
	TextView textViewDiseaseName;
	TextView textViewPatientNo;
	TextView textViewHospitalizationNumber;
	TextView TextView_Height;
	TextView TextView_Weight;
	Switch switchStatus;
	TextView textViewStaging;
	Button btnToQuestionnaire;
	Button btnToCourseRecord;
	Button btnToMealRecord;
	Button btnToNutrientAdvice;
	Button btnBodyAnalysisReport;
	Button btnLaboratoryIndex;
	ImageButton ImageButton_Edit;
	ImageButton ImageButton_Delete;
	TextView textViewChiefComplaint;
	TextView textViewMedicalHistory;

	BadgeView badge7;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		try {

			loadPatientInfo();

			btnToQuestionnaire.setOnClickListener(new onClickListener());
			btnToCourseRecord.setOnClickListener(new onClickListener());
			btnToMealRecord.setOnClickListener(new onClickListener());
			btnToNutrientAdvice.setOnClickListener(new onClickListener());
			btnBodyAnalysisReport.setOnClickListener(new onClickListener());
			btnLaboratoryIndex.setOnClickListener(new onClickListener());
			ImageButton_Edit.setOnClickListener(new onClickListener());
			ImageButton_Delete.setOnClickListener(new onClickListener());

			// 人体成分分析气泡
			List<BodyAnalysisReport> lstAnalysisReports = bodyAnalysisReportBo
					.getDao().query(100, 0, PatientHospitalize_DBKey);
			BadgeView badge3 = new BadgeView(this, btnBodyAnalysisReport);
			badge3.setText(lstAnalysisReports.size() + "");
			badge3.setBadgePosition(BadgeView.POSITION_TOP_LEFT);
			badge3.toggle(true);

			// 实验室检查气泡
			List<LaboratoryIndex> lstLaboratoryIndex = laboratoryIndexBo
					.getDao().query(100, 0, PatientHospitalize_DBKey);
			badge7 = new BadgeView(this, btnLaboratoryIndex);
			badge7.setText(lstLaboratoryIndex.size() + "");
			badge7.setBadgePosition(BadgeView.POSITION_TOP_LEFT);
			badge7.toggle(true);

			BadgeView badge1 = new BadgeView(this, btnToMealRecord);
			badge1.setText("New");
			badge1.setBadgePosition(BadgeView.POSITION_TOP_RIGHT);
			badge1.toggle(true);

			// 制定医嘱
			List<NutrientAdviceSummary> listnuNutrientAdviceSummaries = nutrientAdviceSummaryBo
					.getDao()
					.query(2000, 0,
							Global.currentPatient.getPatientHospitalize_DBKey());
			BadgeView badge2 = new BadgeView(this, btnToNutrientAdvice);
			badge2.setText(listnuNutrientAdviceSummaries.size() + "");
			// badge2.setTextColor(0xFF0000FF);// Color.BLUE;
			// badge2.setBadgeBackgroundColor(0xFFFFFF00);// Color.YELLOW;
			badge2.setBadgePosition(BadgeView.POSITION_TOP_LEFT);
			badge2.toggle(true);

			// 在平板上新增的患者，并且还没有同步到pc上的，是可以删除的
			if (Global.currentPatient.getOperateFlag() != OperateFlag.NEED_ADD_TO_SERVER) {
				ImageButton_Delete.setVisibility(View.GONE);
			}

		} catch (Exception e) {
			doException(e);
		}

	}

	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();

		// 刷新实验室检查气泡
		List<LaboratoryIndex> lstLaboratoryIndex = null;
		try {
			lstLaboratoryIndex = laboratoryIndexBo.getDao().query(100, 0,
					PatientHospitalize_DBKey);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		badge7.setText(lstLaboratoryIndex.size() + "");

		try {
			loadPatientInfo();
		} catch (Exception e) {
			doException(e);
		}
	}

	class onClickListener implements View.OnClickListener {

		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.ImageButton_Edit:
				Intent i7 = new Intent(PatientViewActivity.this,
						PatientInfoActivity.class);
				Bundle bundle3 = new Bundle();
				try {
					bundle3.putInt("OperateType", RequestCode.EDIT_PATIENT_INFO);
				} catch (Exception e) {
					doException(e);
				}
				i7.putExtras(bundle3);

				startActivityForResult(i7, RequestCode.EDIT_PATIENT_INFO);
				break;
			case R.id.ImageButton_Delete:
				PopUtil.AlertDialog(PatientViewActivity.this, "请谨慎操作",
						"确定删除该患者及其信息吗？", "确定",
						new DialogInterface.OnClickListener() {

							public void onClick(DialogInterface dialog,
									int which) {
								try {
									hospitalInfoBo
											.DeletePatientInfo(PatientHospitalize_DBKey);
									PatientViewActivity.this
											.setResult(RequestCode.DELETE_PATIENT_INFO);
									PatientViewActivity.this.finish();
								} catch (Exception e) {
									doException(e);
								}
							}
						});

				break;
			case R.id.btnToQuestionnaire:
				// 筛查
				Intent i = new Intent(PatientViewActivity.this,
						QuestionnaireListActivity.class);
				// i.setClassName("cn.limc.androidcharts",
				// "cn.limc.androidcharts.AndroidChartsActivity");
				Bundle bundle = new Bundle();
				try {
					bundle.putInt("OperateType", RequestCode.LIST_QUESTIONNAIRE);
					bundle.putString("PatientHospitalize_DBKey",
							PatientHospitalize_DBKey);
				} catch (Exception e) {
					doException(e);
				}
				i.putExtras(bundle);

				startActivityForResult(i, RequestCode.LIST_QUESTIONNAIRE);
				break;
			case R.id.btnToCourseRecord:
				// 查房记录
				Intent i2 = new Intent(PatientViewActivity.this,
						CourseRecordListActivity.class);
				Bundle bundle2 = new Bundle();
				try {
					bundle2.putInt("OperateType",
							RequestCode.LIST_COURSE_RECORD);
					bundle2.putString("PatientHospitalize_DBKey",
							PatientHospitalize_DBKey);
				} catch (Exception e) {
					doException(e);
				}
				i2.putExtras(bundle2);

				startActivityForResult(i2, RequestCode.LIST_COURSE_RECORD);
				break;
			case R.id.btnBodyAnalysis:
				// 人体成分
				try {
					List<BodyAnalysisReport> lstAnalysisReports = bodyAnalysisReportBo
							.getDao().query(100, 0, PatientHospitalize_DBKey);
					if (lstAnalysisReports.size() == 0) {
						PopUtil.show(PatientViewActivity.this, "未查询到 『"
								+ Global.currentPatient.getPatientName()
								+ "』 的人体成分报告！");
						return;
					}
				} catch (Exception e) {
					doException(e);
				}

				Intent i3 = new Intent(PatientViewActivity.this,
						BodyAnalysisListActivity.class);
				startActivity(i3);
				break;
			case R.id.btnLaboratoryIndex:
				// 实验室检查
				Intent i4 = new Intent(PatientViewActivity.this,
						LaboratoryIndexListActivity.class);
				startActivity(i4);
				break;
			case R.id.btnToMealRecord:
				// 膳食调查
				Intent i5 = new Intent(PatientViewActivity.this,
						MealRecordActivity.class);
				startActivity(i5);
				break;
			case R.id.btnToNutrientAdvice:
				// 制定医嘱
				Intent i6 = new Intent(PatientViewActivity.this,
						AdviceListActivity.class);
				startActivity(i6);
				break;
			default:
				PopUtil.show(PatientViewActivity.this, "功能未实现！");
				break;
			}
		}
	}

	private void loadPatientInfo() throws Exception {
		final PatientHospitalizeBasicInfo hospitalInfo = hospitalInfoBo.getDao()
				.queryForId(PatientHospitalize_DBKey);

		textViewPatientName.setText(hospitalInfo.getPatientName());
		// 科室
		textViewDepartment.setText(hospitalInfo.getDepartmentName());
		textViewBedCode.setText("    床号：" + hospitalInfo.getBedCode());
		textViewClinicistName.setText("    临床医生："
				+ hospitalInfo.getClinicistName());

		String d = DateHelper.getInstance().getDataString_2(
				hospitalInfo.getInHospitalData());
		textViewInHospitalDate.setText("    " + d + " 入院");
		textViewAge.setText(Convert.RoundString2(hospitalInfo.getAge(), 2)
				+ " 岁");
		textViewDiseaseName.setText("疾病（诊断）：" + hospitalInfo.getDiseaseName()
				+ " " + hospitalInfo.getClinicalDiagnosis());
		textViewHospitalizationNumber.setText("    住院号："
				+ hospitalInfo.getHospitalizationNumber());
		textViewPatientNo.setText("    病案号：" + hospitalInfo.getPatientNo());

		if (hospitalInfo.getGender().equals("M")) {
			ImageViewSex.setImageResource(R.drawable.male);
		} else {
			ImageViewSex.setImageResource(R.drawable.female);
		}

		// 营养治疗状态
		if (hospitalInfo.getTherapyStatusName().equals("出院")) {
			switchStatus.setChecked(false);
		} else {
			switchStatus.setChecked(true);
		}
		switchStatus.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				PatientHospitalizeBasicInfo patientInfo = Global.currentPatient;
				if(switchStatus.isChecked()){
					patientInfo.setTherapyStatus("0");
					patientInfo.setTherapyStatusName("待筛查");
				}else{
					patientInfo.setTherapyStatus("9");
					patientInfo.setTherapyStatusName("出院");
				}
				try {
					hospitalInfoBo.getDao().update(patientInfo);
				} catch (Exception e) {
					doException(e);
				}

			}
		});

		if (hospitalInfo.getHeight() == 0) {
			TextView_Height.setText("身高：_ cm");
		} else {
			TextView_Height
					.setText("身高："
							+ Convert.RoundString2(hospitalInfo.getHeight(), 2)
							+ " cm");
		}

		if (hospitalInfo.getWeight() == 0) {
			TextView_Weight.setText("    体重：_ kg");
		} else {
			TextView_Weight
					.setText("    体重："
							+ Convert.RoundString2(hospitalInfo.getWeight(), 2)
							+ " kg");
		}

		if (hospitalInfo.getStaging() == null
				|| hospitalInfo.getStaging().equals("")) {
			textViewStaging.setText("肿瘤分期：无");
		} else {
			SysCode sysCode = sysCodeBo.getDao().queryBySysCode("Staging",
					hospitalInfo.getStaging());
			if (sysCode != null) {
				textViewStaging.setText("肿瘤分期：" + sysCode.getSysCodeName());
			}
		}

		if (!hospitalInfo.getChiefComplaint().equals("")) {
			textViewChiefComplaint.setText("主诉："
					+ hospitalInfo.getChiefComplaint());
		}

		if (!hospitalInfo.getMedicalHistory().equals("")) {
			textViewMedicalHistory.setText("现病史："
					+ hospitalInfo.getMedicalHistory());
		}

		// 桓兴从his导入的主诉和现病史没法分开，所以统一显示到主诉字段
		if (Global.BUILD_FLAG.equals("HX")) {
			textViewMedicalHistory.setVisibility(View.GONE);
		} else {
			textViewMedicalHistory.setVisibility(View.VISIBLE);
		}
	}

	@Override
	protected String getLogTag() {
		return LogTag.CURD_PATIENT_INFO;

	}

	@Override
	protected int getLayoutId() {
		return R.layout.patient_view;

	}

	@Override
	protected void receiveIntent(Intent intent) throws Exception {
		PatientHospitalize_DBKey = intent
				.getStringExtra("PatientHospitalize_DBKey");

	}

	@Override
	protected void initializeBo() throws Exception {
		hospitalInfoBo = new PatientHospitalizeBasicInfoBo(this);
		bodyAnalysisReportBo = new BodyAnalysisReportBo(this);
		laboratoryIndexBo = new LaboratoryIndexBo(this);
		sysCodeBo = new SysCodeBo(this);
		nutrientAdviceSummaryBo = new NutrientAdviceSummaryBo(this);
	}

	@Override
	protected void setView() throws Exception {
		ImageViewSex = (ImageView) findViewById(R.id.ImageViewSex);
		textViewPatientName = (TextView) findViewById(R.id.textViewPatientName);
		textViewDepartment = (TextView) findViewById(R.id.textViewDepartment);
		textViewBedCode = (TextView) findViewById(R.id.textViewBedCode);
		textViewClinicistName = (TextView) findViewById(R.id.textViewClinicistName);
		textViewInHospitalDate = (TextView) findViewById(R.id.textViewInHospitalDate);
		textViewAge = (TextView) findViewById(R.id.textViewAge);
		textViewDiseaseName = (TextView) findViewById(R.id.textViewDiseaseName);
		textViewPatientNo = (TextView) findViewById(R.id.textViewPatientNo);
		textViewHospitalizationNumber = (TextView) findViewById(R.id.hospitalizationNumber);
		switchStatus = (Switch) findViewById(R.id.switchStatus);
		btnToQuestionnaire = (Button) findViewById(R.id.btnToQuestionnaire);
		btnToCourseRecord = (Button) findViewById(R.id.btnToCourseRecord);
		btnToMealRecord = (Button) findViewById(R.id.btnToMealRecord);
		btnToNutrientAdvice = (Button) findViewById(R.id.btnToNutrientAdvice);
		btnBodyAnalysisReport = (Button) findViewById(R.id.btnBodyAnalysis);
		btnLaboratoryIndex = (Button) findViewById(R.id.btnLaboratoryIndex);
		ImageButton_Edit = (ImageButton) findViewById(R.id.ImageButton_Edit);
		ImageButton_Delete = (ImageButton) findViewById(R.id.ImageButton_Delete);
		TextView_Height = (TextView) findViewById(R.id.TextView_Height);
		TextView_Weight = (TextView) findViewById(R.id.TextView_Weight);
		textViewStaging = (TextView) findViewById(R.id.textViewStaging);
		textViewChiefComplaint = (TextView) findViewById(R.id.textViewChiefComplaint);
		textViewMedicalHistory = (TextView) findViewById(R.id.textViewMedicalHistory);

	}

}

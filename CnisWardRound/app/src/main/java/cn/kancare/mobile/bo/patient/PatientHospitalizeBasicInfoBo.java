package cn.kancare.mobile.bo.patient;

import java.sql.SQLException;
import java.util.List;

import android.app.Activity;
import android.content.DialogInterface;
import cn.kancare.mobile.R;
import cn.kancare.mobile.activity.frame.PatientListFragment;
import cn.kancare.mobile.activity.frame.PatientListFragment.ViewHolder;
import cn.kancare.mobile.activity.patient.PatientInfoActivity;
import cn.kancare.mobile.bean.basic.User;
import cn.kancare.mobile.bean.laboratoryindex.LaboratoryIndex;
import cn.kancare.mobile.bean.laboratoryindex.SearchPageConfig;
import cn.kancare.mobile.bean.laboratoryindex.TestResult;
import cn.kancare.mobile.bean.patient.PatientHospitalizeBasicInfo;
import cn.kancare.mobile.bo.basic.UserBo;
import cn.kancare.mobile.common.Global;
import os.zxs.force.common.constant.SyncConstant.OperateFlag;
import cn.kancare.mobile.core.base.BaseBo;
import cn.kancare.mobile.core.sync.DBKeyEntity;
import os.zxs.force.core.util.ColorUtil;
import os.zxs.force.core.util.Convert;
import os.zxs.force.core.util.PinyinUtil;
import os.zxs.force.core.util.PinyinUtil.HanyuPinyinCaseType;
import os.zxs.force.core.util.PopUtil;
import os.zxs.force.core.util.StringUtil;
import os.zxs.force.core.util.spinner.SpinnerOption;
import cn.kancare.mobile.dao.patient.PatientHospitalizeBasicInfoDao;

import com.alibaba.fastjson.JSON;

public class PatientHospitalizeBasicInfoBo extends
		BaseBo<PatientHospitalizeBasicInfoDao> {

	public PatientHospitalizeBasicInfoBo(Activity context) throws Exception {
		super(context);
	}

	public void updatePatientBasicInfo(PatientInfoActivity activity)
			throws Exception {

		PatientHospitalizeBasicInfo patient = Global.currentPatient;
		if (patient.getOperateFlag() != OperateFlag.NEED_ADD_TO_SERVER) {
			patient.setOperateFlag(OperateFlag.NEED_EDIT_TO_SERVER);
		}
		patient.setAge(activity.EditText_Age.getText().toString());
		patient.setWeight(Convert.cash2Double(activity.EditText_Weight
				.getText().toString()));
		patient.setHeight(Convert.cash2Double(activity.EditText_Height
				.getText().toString()));
		if (activity.RadioButton0.isChecked()) {
			patient.setGender("F");
		} else {
			patient.setGender("M");
		}
		patient.setInHospitalData(activity.DatePickerView_HospitalDate
				.getDate());
		SpinnerOption spinnerOption = (SpinnerOption) activity.Spinner_Department
				.getSelectedItem();
		patient.setDepartment_DBKey(Convert.cash2Int(spinnerOption.getValue()));
		patient.setDepartmentName(spinnerOption.getText());
		patient.setBedCode(activity.EditText_BedCode.getText().toString());
		String[] arr = StringUtil.subStrNum(patient.getBedCode());
		patient.setBedCodePrefix(arr[0]);
		patient.setBedCodeSuffix(Convert.cash2Int(arr[1]));

		patient.setPatientNo(activity.EditText_PatientNo.getText().toString());
		patient.setHospitalizationNumber(activity.EditText_ZYH.getText()
				.toString());
		SpinnerOption spinnerOption2 = (SpinnerOption) activity.Spinner_Staging
				.getSelectedItem();
		patient.setStaging(spinnerOption2.getValue());
		patient.setClinicalDiagnosis(activity.EditText_ClinicalDiagnosis
				.getText().toString());
		getDao().update(patient);
	}

	public void createPatientBasicInfo(PatientInfoActivity activity)
			throws Exception {

		PatientHospitalizeBasicInfo patient = new PatientHospitalizeBasicInfo();
		patient.setPatientHospitalize_DBKey(StringUtil.getUniqueDBKey());
		patient.setPATIENT_DBKEY(patient.getPatientHospitalize_DBKey());
		// patient.setTherapyStatus("0"); //默认待筛查状态
		patient.setPatientNo(activity.EditText_PatientNo.getText().toString());
		patient.setHospitalizationNumber(activity.EditText_ZYH.getText()
				.toString());
		patient.setOperateFlag(OperateFlag.NEED_ADD_TO_SERVER);
		patient.setPatientName(activity.EditText_PatientName.getText()
				.toString());
		patient.setPatientNameFirstLetter(PinyinUtil.getPinYin(
				patient.getPatientName(), HanyuPinyinCaseType.LOWERCASE, true));
		patient.setAge(activity.EditText_Age.getText().toString());
		patient.setWeight(Convert.cash2Double(activity.EditText_Weight
				.getText().toString()));
		patient.setHeight(Convert.cash2Double(activity.EditText_Height
				.getText().toString()));
		if (activity.RadioButton0.isChecked()) {
			patient.setGender("F");
		} else {
			patient.setGender("M");
		}
		patient.setInHospitalData(activity.DatePickerView_HospitalDate
				.getDate());
		SpinnerOption spinnerOption = (SpinnerOption) activity.Spinner_Department
				.getSelectedItem();
		patient.setDepartment_DBKey(Convert.cash2Int(spinnerOption.getValue()));
		patient.setDepartmentName(spinnerOption.getText());
		patient.setBedCode(activity.EditText_BedCode.getText().toString());
		String[] arr = StringUtil.subStrNum(patient.getBedCode());
		patient.setBedCodePrefix(arr[0]);
		patient.setBedCodeSuffix(Convert.cash2Int(arr[1]));

		SpinnerOption spinnerOption2 = (SpinnerOption) activity.Spinner_Staging
				.getSelectedItem();
		if (spinnerOption2 != null) {
			patient.setStaging(spinnerOption2.getValue());
		}
		patient.setClinicalDiagnosis(activity.EditText_ClinicalDiagnosis
				.getText().toString());
		getDao().create(patient);
	}

	public void doDownloadJson(String json) throws Exception {
		if (json == "")
			return;

		List<PatientHospitalizeBasicInfo> models = JSON.parseArray(json,
				PatientHospitalizeBasicInfo.class);

		for (PatientHospitalizeBasicInfo model : models) {
			String[] arr = StringUtil.subStrNum(model.getBedCode());
			model.setBedCodePrefix(arr[0]);
			model.setBedCodeSuffix(Convert.cash2Int(arr[1]));

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

	@Override
	protected void setDao() throws SQLException {
		dao = new PatientHospitalizeBasicInfoDao(context);
	}

	@Override
	public void doUploadResult(String result) throws Exception {
		if (!result.equals("")) {
			/*
			 * MealRecordBo mealRecordBo = new MealRecordBo(context);
			 * PatientQuestionnaireBo patientQuestionnaireBo = new
			 * PatientQuestionnaireBo( context); CourseRecordBo courseRecordBo =
			 * new CourseRecordBo(context); LaboratoryIndexBo laboratoryIndexBo
			 * = new LaboratoryIndexBo(context);
			 */
			List<DBKeyEntity> lstSyncResults = JSON.parseArray(result,
					DBKeyEntity.class);
			for (DBKeyEntity dbKeyEntity : lstSyncResults) {

				// 新增和编辑现在走一套逻辑，改标志就行
				PatientHospitalizeBasicInfo patientInfo = dao
						.queryForId(dbKeyEntity.getOldDBKey());
				patientInfo.setOperateFlag(OperateFlag.NONE);
				dao.update(patientInfo);
				/*
				 * if (dbKeyEntity.getOperateFlag() ==
				 * OperateFlag.NEED_EDIT_TO_SERVER) {
				 * 
				 * 
				 * } else if (dbKeyEntity.getOperateFlag() ==
				 * OperateFlag.NEED_ADD_TO_SERVER) { // 患者
				 * getDao().deleteById(dbKeyEntity.getOldDBKey());
				 * 
				 * // 膳调 mealRecordBo.getDao().UpdatePatientHospitalize_DBKey(
				 * dbKeyEntity.getNewDBKey(), dbKeyEntity.getOldDBKey());
				 * 
				 * // 问卷 patientQuestionnaireBo.getDao()
				 * .UpdatePatientHospitalize_DBKey( dbKeyEntity.getNewDBKey(),
				 * dbKeyEntity.getOldDBKey());
				 * 
				 * // 查房记录
				 * courseRecordBo.getDao().UpdatePatientHospitalize_DBKey(
				 * dbKeyEntity.getNewDBKey(), dbKeyEntity.getOldDBKey());
				 * 
				 * // 实验室检查
				 * laboratoryIndexBo.getDao().UpdatePatientHospitalize_DBKey(
				 * dbKeyEntity.getNewDBKey(), dbKeyEntity.getOldDBKey());
				 * 
				 * }
				 */
			}
		}
	}

	public void setPatientDietician2(final Activity activity,
			final ViewHolder holder,
			final PatientHospitalizeBasicInfo patientInfo, final UserBo userBo)
			throws Exception {
		User user = userBo.getDao().queryForId(
				patientInfo.getNutrientDoctor_DBKey());
		PopUtil.AlertDialog(activity, "患者『" + patientInfo.getPatientName()
				+ "』", "主管营养师是否做如下更换：\r\n『" + user.getUserName() + "』 -> 『"
				+ Global.loginUser.getUserName() + "』", "是",
				new DialogInterface.OnClickListener() {

					public void onClick(DialogInterface dialog, int which) {
						try {
							setPatientDietician(activity, holder, patientInfo,
									userBo);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}

				}, "否", new DialogInterface.OnClickListener() {

					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
					}

				});
	}

	// 设置患者的主管营养师
	public void setPatientDietician(Activity activity, final ViewHolder holder,
			final PatientHospitalizeBasicInfo patientInfo, UserBo userBo)
			throws Exception {
		patientInfo.setNutrientDoctor_DBKey(Global.loginUser.getUser_DBKey());
		holder.tvPatientNo.setTextColor(ColorUtil.getColor(R.color.red));// red
		User user = userBo.getDao().queryForId(
				patientInfo.getNutrientDoctor_DBKey());

		if (user != null) {
			holder.badgeDietician.setText(user.getUserName());
		}
		holder.badgeDietician.show();
	}

	// 根据检验数据判断是否有风险
	public Boolean checkIsRisk(PatientListFragment fragment,
			String PatientHospitalize_DBKey) throws Exception {

		List<SearchPageConfig> lstSearchPageConfigs = fragment.searchPageConfigBo
				.getDao().queryForAll();
		List<LaboratoryIndex> lstLaboratoryIndexs = fragment.laboratoryIndexBo
				.getDao().query(PatientHospitalize_DBKey);
		for (LaboratoryIndex laboratoryIndex : lstLaboratoryIndexs) {
			List<TestResult> lstResults = fragment.testResultBo.getDao().query(
					laboratoryIndex.getLaboratoryIndex_DBKey());
			for (TestResult testResult : lstResults) {
				for (SearchPageConfig searchPageConfig : lstSearchPageConfigs) {
					try {

						if (testResult.getTestItemDetail_DBKey() == searchPageConfig
								.getLinkDBKEY()
								&& !testResult.getTestItemValue().equals("")) {
							double itemValue = Convert.cash2Double(testResult
									.getTestItemValue());
							double lower = Convert.cash2Double(searchPageConfig
									.getLowerLimit());
							double upper = Convert.cash2Double(searchPageConfig
									.getUpperLimit());

							if (itemValue < lower || itemValue > upper) {
								return true;
							}
						}

					} catch (Exception e) {
						// TODO: handle exception
					}

				}
			}
		}

		return false;

	}

	// 删除患者
	public void DeletePatientInfo(String PatientHospitalize_DBKey)
			throws SQLException {
		dao.deleteById(PatientHospitalize_DBKey);
		dao.clearHistoryPatient();
	}
}

package cn.kancare.mobile.bo;

import java.sql.SQLException;
import java.util.List;

import android.app.Activity;
import android.widget.ImageView;
import cn.kancare.mobile.R;
import cn.kancare.mobile.activity.courserecord.CourseRecordInfoActivity;
import cn.kancare.mobile.activity.frame.PatientListFragment.ViewHolder;
import cn.kancare.mobile.bean.CourseRecord;
import cn.kancare.mobile.bean.patient.PatientHospitalizeBasicInfo;
import cn.kancare.mobile.bo.basic.SysCodeBo;
import cn.kancare.mobile.common.Global;
import os.zxs.force.common.constant.SyncConstant.OperateFlag;
import cn.kancare.mobile.common.constant.SysCodeType;
import cn.kancare.mobile.core.base.BaseBo;
import os.zxs.force.core.baseanimation.BadgeView;
import cn.kancare.mobile.core.sync.DBKeyEntity;
import os.zxs.force.core.util.Convert;
import os.zxs.force.core.util.DateHelper;
import os.zxs.force.core.util.StringUtil;
import cn.kancare.mobile.core.util.spinner.SpinnerUtil;
import cn.kancare.mobile.dao.CourseRecordDao;

import com.alibaba.fastjson.JSON;

public class CourseRecordBo extends BaseBo<CourseRecordDao> {

	public CourseRecordBo(Activity context) throws Exception {
		super(context);
	}

	@Override
	protected void setDao() throws SQLException {
		dao = new CourseRecordDao(context);
	}

	// 同步
	public void doDownloadJson(String json) throws Exception {
		if (json == "")
			return;

		List<CourseRecord> models = JSON.parseArray(json, CourseRecord.class);

		for (CourseRecord model : models) {
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

	public void loadCourseRecordInfo(CourseRecordInfoActivity activity,
			CourseRecord course) throws Exception {

		SysCodeBo sysBo = new SysCodeBo(context);
		// 恶心
		SpinnerUtil.setSpinnerValue(context, sysBo, activity.Nausea,
				SysCodeType.NAUSEA, course.getNausea());
		// 腹泻
		SpinnerUtil.setSpinnerValue(context, sysBo, activity.Diarrhea,
				SysCodeType.DIARRHEA, course.getDiarrhea());
		// 便秘
		SpinnerUtil.setSpinnerValue(context, sysBo, activity.Constipation,
				SysCodeType.CONSTIPATION, course.getConstipation());
		// 呕吐
		SpinnerUtil.setSpinnerValue(context, sysBo, activity.Vomit,
				SysCodeType.VOMIT, course.getVomit());
		// 腹胀
		SpinnerUtil.setSpinnerValue(context, sysBo,
				activity.AbdominalDistension, SysCodeType.DISTENTION,
				course.getAbdominalDistension());
		// 腹痛
		SpinnerUtil.setSpinnerValue(context, sysBo, activity.AbdominalPain,
				SysCodeType.DISTENTION, course.getAbdominalPain());
		
		activity.editNauseaRemark.setText(course.getNauseaRemark());
		activity.editDiarrheaRemark.setText(course.getDiarrheaRemark());
		activity.editConstipationRemark.setText(course.getConstipationRemark());
		activity.editVomitRemark.setText(course.getVomitRemark());
		activity.editAbdominalDistensionRemark.setText(course.getAbdominalDistensionRemark());
		activity.editAbdominalPainRemark.setText(course.getAbdominalPainRemark());

		// 体温
		activity.Temperature.setText(course.getTemperature() + "");
		activity.seekBarTemperature
				.setProgress((int) ((course.getTemperature() - 35) * 10));

		// 呼吸
		if (course.getBreathe() != 0)
			activity.Breathe
					.setText(Convert.cash2Int(course.getBreathe()) + "");

		// 皮肤
		SpinnerUtil.setSpinnerValue(context, sysBo, activity.Skin,
				SysCodeType.SKIN, course.getSkin());
		// 心率
		if (course.getHeartRate() != 0)
			activity.HeartRate.setText(Convert.cash2Int(course.getHeartRate())
					+ "");

		// 血压
		if (course.getDBP() != 0)
			activity.DBP.setText(Convert.cash2Int(course.getDBP()) + "");

		if (course.getSBP() != 0)
			activity.SBP.setText(Convert.cash2Int(course.getSBP()) + "");

		// 水肿
		SpinnerUtil.setSpinnerValue(context, sysBo, activity.Edema,
				SysCodeType.EDEMA, course.getEdema());

		// 三头肌皮脂厚度
		if (course.getGripLeft() != 0)
			activity.TricepsSkinfold.setText(Convert.cash2Int(course
					.getTricepsSkinfold()) + "");

		// Height
		if (course.getHeight() != 0)
			activity.editHeight.setText(course.getHeight() + "");
		
		// Weight
		if (course.getWeight() != 0)
			activity.editWeight.setText(course.getWeight() + "");
		
		// BMI
		if (course.getBMI() != 0)
			activity.BMI.setText(course.getBMI() + "");

		// 握力 R
		if (course.getGripLeft() != 0)
			activity.GripLeft.setText(Convert.cash2Int(course.getGripLeft())
					+ "");

		// 握力 L
		if (course.getGripRight() != 0)
			activity.GripRight.setText(Convert.cash2Int(course.getGripRight())
					+ "");

		// BMR
		if (course.getBMR() != 0)
			activity.BMR.setText(Convert.cash2Int(course.getBMR()) + "");

		// 6分钟步行速度(m/s)
		if (course.getWalkSpeed() != 0)
			activity.WalkSpeed.setText(Convert.cash2Int(course.getWalkSpeed())
					+ "");

		// 引流量
		if (course.getDrainage() != 0)
			activity.Drainage.setText(Convert.cash2Int(course.getDrainage())
					+ "");

		// 胃肠减压量
		if (course.getGastrointestinalDecompression() != 0)
			activity.GastrointestinalDecompression.setText(Convert
					.cash2Int(course.getGastrointestinalDecompression()) + "");

		// 尿量
		if (course.getUrineVolume() != 0)
			activity.UrineVolume.setText(Convert.cash2Int(course
					.getUrineVolume()) + "");

		// 备注
		activity.Remark.setText(course.getRemark());

		// 未见患者
		if (course.getPatientNoShow() == 0) {
			activity.chkNoShow.setChecked(false);
		} else {
			activity.chkNoShow.setChecked(true);
		}

		// 查房日期
		activity.DatePickerViewRecordDate.setDate(course.getCourseRecordDate());

		// if(Global.AppMode.equals(SettingCode.APP_MODE_INNER)){
		// activity.chkNoShow.setVisibility(View.GONE);
		// }

	}

	/**
	 * 新建查房记录
	 * 
	 * @param course
	 * @throws Exception
	 */
	public String AddCourseRecord(CourseRecordInfoActivity activity,
			CourseRecord course) throws Exception {

		course.setCourseRecord_DBKey(StringUtil.getUniqueDBKey());
		course.setCourseRecordNo(course.getCourseRecord_DBKey());
		course.setCourseRecordDate(DateHelper.getInstance().getDataString_1(
				null));
		course.setCreateTime(DateHelper.getInstance().getDataString_1(null));
		course.setCreateBy(Global.loginUser.getUser_DBKey() + "");
		course.setCreateIP(Global.LocalIpAddress);
		course.setCreateProgram(Global.CreateProgram);
		course.setOperateFlag(OperateFlag.NEED_ADD_TO_SERVER);
		course = getCourseRecordInfo(activity, course);
		dao.create(course);
		return course.getCourseRecord_DBKey();
	}

	/**
	 * 编辑查房记录
	 * 
	 * @param course
	 * @throws Exception
	 */
	public void EditCourseRecord(CourseRecordInfoActivity activity,
			CourseRecord course) throws Exception {
		course = getCourseRecordInfo(activity, course);
		course.setUpdateTime(DateHelper.getInstance().getDataString_1(null));
		course.setUpdateBy(Global.loginUser.getUser_DBKey() + "");
		course.setUpdateIP(Global.LocalIpAddress);
		course.setUpdateProgram(Global.CreateProgram);
		if (course.getOperateFlag() == OperateFlag.NONE) {
			course.setOperateFlag(OperateFlag.NEED_EDIT_TO_SERVER);
		}
		dao.update(course);
	}

	public CourseRecord getCourseRecordInfo(CourseRecordInfoActivity activity,
			CourseRecord course) throws Exception {
		course.setNausea(SpinnerUtil.getSpinnerValue(activity.Nausea));
		course.setDiarrhea(SpinnerUtil.getSpinnerValue(activity.Diarrhea));
		course.setConstipation(SpinnerUtil
				.getSpinnerValue(activity.Constipation));
		course.setVomit(SpinnerUtil.getSpinnerValue(activity.Vomit));
		course.setAbdominalDistension(SpinnerUtil
				.getSpinnerValue(activity.AbdominalDistension));
		course.setAbdominalPain(SpinnerUtil
				.getSpinnerValue(activity.AbdominalPain));
		
		course.setNauseaRemark(activity.editNauseaRemark.getText().toString());
		course.setDiarrheaRemark(activity.editDiarrheaRemark.getText().toString());
		course.setConstipationRemark(activity.editConstipationRemark.getText().toString());
		course.setVomitRemark(activity.editVomitRemark.getText().toString());
		course.setAbdominalDistensionRemark(activity.editAbdominalDistensionRemark.getText().toString());
		course.setAbdominalPainRemark(activity.editAbdominalPainRemark.getText().toString());

		course.setTemperature(Convert.cash2Double(activity.Temperature
				.getText().toString()));
		course.setBreathe(Convert.cash2Double(activity.Breathe.getText()
				.toString()));
		course.setSkin(SpinnerUtil.getSpinnerValue(activity.Skin));
		course.setHeartRate(Convert.cash2Double(activity.HeartRate.getText()
				.toString()));
		course.setDBP(Convert.cash2Double(activity.DBP.getText().toString()));
		course.setSBP(Convert.cash2Double(activity.SBP.getText().toString()));
		course.setEdema(SpinnerUtil.getSpinnerValue(activity.Edema));

		course.setTricepsSkinfold(Convert.cash2Double(activity.TricepsSkinfold
				.getText().toString()));
		course.setHeight(Convert.cash2Double(activity.editHeight.getText().toString()));
		course.setWeight(Convert.cash2Double(activity.editWeight.getText().toString()));

		//如果患者基本信息中没填体重，则把问卷中的体重更新到患者基本信息中
		if(Global.currentPatient.getWeight() == 0){
			Global.currentPatient.setWeight(course.getWeight());
			activity.patientHospitalizeBasicInfoBo.getDao().update(Global.currentPatient);
		}

		course.setBMI(Convert.cash2Double(activity.BMI.getText().toString()));
		course.setGripLeft(Convert.cash2Double(activity.GripLeft.getText()
				.toString()));
		course.setGripRight(Convert.cash2Double(activity.GripRight.getText()
				.toString()));

		course.setBMR(Convert.cash2Double(activity.BMR.getText().toString()));
		course.setWalkSpeed(Convert.cash2Double(activity.WalkSpeed.getText()
				.toString()));

		course.setDrainage(Convert.cash2Double(activity.Drainage.getText()
				.toString()));
		course.setGastrointestinalDecompression(Convert
				.cash2Double(activity.GastrointestinalDecompression.getText()
						.toString()));
		course.setUrineVolume(Convert.cash2Double(activity.UrineVolume
				.getText().toString()));

		course.setRemark(activity.Remark.getText().toString());
		course.setPatientNoShow(activity.chkNoShow.isChecked() ? 1 : 0);
		course.setCourseRecordDate(activity.DatePickerViewRecordDate.getText()
				.toString());

		return course;
	}

	@Override
	public void doUploadResult(String result) throws Exception {
		if (!result.equals("")) {
			List<DBKeyEntity> lstSyncResults = JSON.parseArray(result,
					DBKeyEntity.class);
			for (DBKeyEntity dbKeyEntity : lstSyncResults) {
				CourseRecord courseRecord = dao.queryForId(dbKeyEntity
						.getOldDBKey());
				courseRecord.setOperateFlag(OperateFlag.NONE);
				dao.update(courseRecord);
				
//				if (dbKeyEntity.getOperateFlag() == OperateFlag.NEED_ADD_TO_SERVER) {
//					dao.deleteById(dbKeyEntity.getOldDBKey());
//				} else if (dbKeyEntity.getOperateFlag() == OperateFlag.NEED_EDIT_TO_SERVER) {
//
//				}
			}
		}
	}

	public void setCourseInfo(PatientHospitalizeBasicInfo patientinfo,
			ViewHolder holder) throws Exception {

		// 查房记录图标
		final ImageView imageViewCourseRecord = holder.imageViewCourseRecord;
		BadgeView badge5 = holder.badgeCourseRecord;
		int count = 0; // 今天做过多少次查房记录

		List<CourseRecord> lstCourseRecords = dao.query(20, 0,
				patientinfo.getPatientHospitalize_DBKey());
		if (lstCourseRecords.size() > 0) {

			imageViewCourseRecord
					.setBackgroundResource(R.drawable.courserecord);
			// 如果有风险，则显示橙色图标
			String nowDateString = DateHelper.getInstance().getDataString_2(
					null);
			String screeningDate = "";
			for (CourseRecord courseRecord : lstCourseRecords) {

				screeningDate = DateHelper.getInstance().getDataString_3(
						courseRecord.getCourseRecordDate());
				if (screeningDate.equals(nowDateString)) {
					count++;
				}
			}

		} else {
			imageViewCourseRecord
					.setBackgroundResource(R.drawable.courserecord2);
		}

		if (count > 0) {
			// 如果今天做过查房，则气泡提示
			badge5.setText(count + "");
			badge5.show();
		} else {
			badge5.hide();
		}

	}
}

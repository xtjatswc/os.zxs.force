package cn.kancare.mobile.dao.patient;

import java.sql.SQLException;
import java.util.List;

import android.content.Context;
import cn.kancare.mobile.bean.patient.PatientHospitalizeBasicInfo;
import cn.kancare.mobile.common.Global;
import cn.kancare.mobile.common.constant.SettingCode;
import cn.kancare.mobile.core.base.BaseDao;

import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.Where;

/**
 * @author Administrator
 * 
 */
public class PatientHospitalizeBasicInfoDao extends
		BaseDao<PatientHospitalizeBasicInfo> {

	public PatientHospitalizeBasicInfoDao(Context context) throws SQLException {
		super(context);
	}

	/*
	 * public PatientHospitalizeBasicInfo queryForId( String
	 * PatientHospitalize_DBKey) throws Exception { return
	 * dao.queryBuilder().where() .eq("PatientHospitalize_DBKey",
	 * PatientHospitalize_DBKey) .queryForFirst(); }
	 */

	/**
	 * 查询记录
	 * 
	 * @param inStatus true:在院，false:出院
	 */
	public List<PatientHospitalizeBasicInfo> query(int limit, int offset,
			String keyword, String department_DBKey, List<String> favorites,
			Boolean showMyPatient, Boolean inStatus) throws Exception {
		QueryBuilder<PatientHospitalizeBasicInfo, Integer> qBuilder = dao
				.queryBuilder();
		Where<PatientHospitalizeBasicInfo, Integer> where = qBuilder.where();
		if (keyword != "") {

			keyword = "%" + keyword + "%";
			where.or(where.like("BedCode", keyword),
					where.like("PatientName", keyword),
					where.like("PatientNameFirstLetter", keyword),
					where.like("ClinicistName", keyword),
					where.like("PatientNo", keyword),
					where.like("HospitalizationNumber", keyword),
					where.like("DiseaseName", keyword)

			);

			// 收藏的患者
			if (favorites != null && favorites.size() > 0) {
				where.and().in("PatientHospitalize_DBKey", favorites);
			}
		}
		if (department_DBKey != "" && !department_DBKey.equals("0")) {
			where.and().eq("Department_DBKey", department_DBKey);
		}
		if (showMyPatient) {
			where.and().eq("NutrientDoctor_DBKey",
					Global.loginUser.getUser_DBKey());
		}

		//在院状态
		if(inStatus){
			where.and().ne("TherapyStatus", "9");
		}else{
			where.and().eq("TherapyStatus", "9");
		}

		qBuilder.limit(limit).offset(offset);

		//排序方式写死，不按设置走
		Boolean ascDesc = true;//Global.PatientListOrderByAscDesc.equals(SettingCode.ORDER_BY_ASC);

		if (Global.PatientListOrderBy
				.equals(SettingCode.ORDER_BY_INHOSPITALDATE) || !inStatus) {

			qBuilder.orderBy("InHospitalData", false);
			qBuilder.orderBy("DepartmentName", true)
					.orderBy("BedCodePrefix", true)
					.orderBy("BedCodeSuffix", true).orderBy("BedCode", true);

		} else if (Global.PatientListOrderBy.equals(SettingCode.ORDER_BY_DEPARTMENT)) {

			qBuilder.orderBy("DepartmentName", ascDesc)
					.orderBy("BedCodePrefix", true)
					.orderBy("BedCodeSuffix", true).orderBy("BedCode", true);

		} else if (Global.PatientListOrderBy
				.equals(SettingCode.ORDER_BY_BEDNUMBER)) {

			qBuilder.orderBy("DepartmentName", true)
					.orderBy("BedCodePrefix", ascDesc)
					.orderBy("BedCodeSuffix", ascDesc)
					.orderBy("BedCode", ascDesc);

		} else if (Global.PatientListOrderBy
				.equals(SettingCode.ORDER_BY_DOCTOR)) {

			qBuilder.orderBy("ClinicistName", ascDesc);
			qBuilder.orderBy("InHospitalData", false);
			qBuilder.orderBy("DepartmentName", true)
					.orderBy("BedCodePrefix", true)
					.orderBy("BedCodeSuffix", true).orderBy("BedCode", true);

		}

		return qBuilder.query();

	}// ...other operations

	/**
	 * 每个床位只保留最新入院的患者，将其它的设置为出院状态
	 * 
	 * @throws SQLException
	 */
	public void updateOrderBy() throws SQLException {
		dao.updateRaw("update patienthospitalizebasicinfo set TherapyStatus='9', TherapyStatusName='出院' where TherapyStatus<>'9' and DepartmentName || BedCode || InHospitalData not IN\n" +
				"(\n" +
				"\tselect DepartmentName || BedCode || max(InHospitalData) from patienthospitalizebasicinfo  where TherapyStatus<>'9'  GROUP BY DepartmentName, BedCode\n" +
				")");

	}

	/**
	 * 清理历史数据
	 * 
	 * @throws SQLException
	 */
	public void clearHistoryPatient(String InHospitalData) throws SQLException {
		// 患者
		dao.executeRaw("delete from patienthospitalizebasicinfo where InHospitalData < '"
				+ InHospitalData + "'");
		clearHistoryPatient();
	}

	/**
	 * 清理历史数据
	 * 
	 * @throws SQLException
	 */
	public void clearHistoryPatient() throws SQLException {

		// 检验数据
		dao.executeRaw("delete from laboratoryindex where PatientHospitalize_DBKey not in (select PatientHospitalize_DBKey from patienthospitalizebasicinfo)");
		dao.executeRaw("delete from testresult where LaboratoryIndex_DBKey not in (select LaboratoryIndex_DBKey from laboratoryindex)");

		// 问卷
		dao.executeRaw("delete from patientquestionnaire where PatientHospitalize_DBKey not in (select PatientHospitalize_DBKey from patienthospitalizebasicinfo)");
		dao.executeRaw("delete from patientquestion where PatientQuestionnaire_DBKey not in (select PatientQuestionnaire_DBKey from patientquestionnaire)");
		dao.executeRaw("delete from patientquestionnaireresult where PatientQuestion_DBKey not in (select PatientQuestion_DBKey from patientquestion)");

		// 查房记录
		dao.executeRaw("delete from courserecord where  PatientHospitalize_DBKey not in (select PatientHospitalize_DBKey from patienthospitalizebasicinfo)");

		// 膳食调查
		dao.executeRaw("delete from mealrecord where  PatientHospitalize_DBKey not in (select PatientHospitalize_DBKey from patienthospitalizebasicinfo)");
		dao.executeRaw("delete from relationofdietaryfood where MealRecord_DBKey not in (select MealRecord_DBKey from mealrecord)");

		// 人体成分分析
		dao.executeRaw("delete from bodyanalysisreport where  PatientHospitalize_DBKey not in (select PatientHospitalize_DBKey from patienthospitalizebasicinfo)");

		// 医嘱
		dao.executeRaw("delete from nutrientadvicesummary where PatientHospitalize_DBKey not in (select PatientHospitalize_DBKey from patienthospitalizebasicinfo)");
		dao.executeRaw("delete from nutrientadvice where NutrientAdviceSummary_DBKey not in (select NutrientAdviceSummary_DBKey from nutrientadvicesummary)");
		dao.executeRaw("delete from nutrientadvicedetail where NutrientAdvice_DBKey not in (select NutrientAdvice_DBKey from nutrientadvice)");
	
		//
		dao.executeRaw("delete from patientfavorite where PatientHospitalize_DBKey not in (select PatientHospitalize_DBKey from patienthospitalizebasicinfo)");

	}

	@Override
	protected String getPrimaryKey() {
		return "PatientHospitalize_DBKey";
	}
}
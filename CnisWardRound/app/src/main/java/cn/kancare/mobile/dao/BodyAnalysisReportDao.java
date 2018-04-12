package cn.kancare.mobile.dao;

import java.util.List;

import android.content.Context;
import cn.kancare.mobile.bean.BodyAnalysisReport;
import cn.kancare.mobile.core.base.BaseDao;

import com.j256.ormlite.stmt.QueryBuilder;

public class BodyAnalysisReportDao extends BaseDao<BodyAnalysisReport>{
	
	public BodyAnalysisReportDao(Context context) throws Exception {
		super(context);
	}

	@Override
	protected String getPrimaryKey() {
		return "BodyAnalysisReportNo";
	}
	
	/**
	 * 查询记录
	 * 
	 * @param patient
	 */
	public List<BodyAnalysisReport> query(int limit, int offset,
			String PatientHospitalize_DBKey) throws Exception {

		QueryBuilder<BodyAnalysisReport, Integer> qBuilder = dao.queryBuilder();
		if (!PatientHospitalize_DBKey.equals("")) {
			qBuilder.where().eq("PatientHospitalize_DBKey",
					PatientHospitalize_DBKey);
		}
		qBuilder.limit(limit).offset(offset)
				.orderBy("BodyAnalysisReportNo", false);
		return qBuilder.query();

	}// ...other operations
}

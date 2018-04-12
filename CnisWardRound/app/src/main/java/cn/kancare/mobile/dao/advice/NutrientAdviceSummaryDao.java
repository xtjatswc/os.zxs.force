package cn.kancare.mobile.dao.advice;

import java.util.List;

import android.content.Context;
import cn.kancare.mobile.bean.advice.NutrientAdviceSummary;
import cn.kancare.mobile.core.base.BaseDao;

import com.j256.ormlite.stmt.QueryBuilder;

public class NutrientAdviceSummaryDao extends BaseDao<NutrientAdviceSummary> {

	public NutrientAdviceSummaryDao(Context context) throws Exception {
		super(context);
	}

	@Override
	protected String getPrimaryKey() {
		return "NutrientAdviceSummary_DBKey";
	}

	/**
	 * 查询记录
	 * 
	 * @param patient
	 */
	public List<NutrientAdviceSummary> query(int limit, int offset,
			String PatientHospitalize_DBKey) throws Exception {

		QueryBuilder<NutrientAdviceSummary, Integer> qBuilder = dao.queryBuilder();
		if (!PatientHospitalize_DBKey.equals("")) {
			qBuilder.where().eq("PatientHospitalize_DBKey",
					PatientHospitalize_DBKey);
		}
		qBuilder.limit(limit).offset(offset)
				.orderBy("NutrientAdviceSummary_DBKey", false);
		return qBuilder.query();

	}// ...other operations
	
}

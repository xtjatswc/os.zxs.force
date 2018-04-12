package cn.kancare.mobile.bo.advice;

import java.util.List;

import android.app.Activity;
import cn.kancare.mobile.activity.advice.fragment.AdviceInputFragment;
import cn.kancare.mobile.bean.advice.NutrientAdvice;
import cn.kancare.mobile.bean.advice.NutrientAdviceDetail;
import cn.kancare.mobile.bean.advice.NutrientAdviceSummary;
import cn.kancare.mobile.common.Global;
import os.zxs.force.common.constant.SyncConstant.OperateFlag;
import cn.kancare.mobile.core.base.BaseBo;
import cn.kancare.mobile.core.sync.DBKeyEntity;
import os.zxs.force.core.util.DateHelper;
import os.zxs.force.core.util.StringUtil;
import cn.kancare.mobile.dao.advice.NutrientAdviceSummaryDao;

import com.alibaba.fastjson.JSON;

public class NutrientAdviceSummaryBo extends BaseBo<NutrientAdviceSummaryDao> {

	public NutrientAdviceSummaryBo(Activity context) throws Exception {
		super(context);
	}

	@Override
	protected void setDao() throws Exception {
		dao = new NutrientAdviceSummaryDao(context);
	}

	@Override
	public void doDownloadJson(String json) throws Exception {
		if (json == "")
			return;

		List<NutrientAdviceSummary> models = JSON.parseArray(json,
				NutrientAdviceSummary.class);

		for (NutrientAdviceSummary model : models) {
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
	public void doUploadResult(String result) throws Exception {
		if (!result.equals("")) {
			List<DBKeyEntity> lstSyncResults = JSON.parseArray(result,
					DBKeyEntity.class);
			for (DBKeyEntity dbKeyEntity : lstSyncResults) {
				NutrientAdviceSummary nutrientAdviceSummary = dao
						.queryForId(dbKeyEntity.getOldDBKey());
				nutrientAdviceSummary.setOperateFlag(OperateFlag.NONE);
				dao.update(nutrientAdviceSummary);
			}
		}

	}

	public void DeleteAdvice(NutrientAdviceBo nutrientAdviceBo,
			NutrientAdviceDetailBo nutrientAdviceDetailBo,
			String NutrientAdviceSummary_DBKey) throws Exception {

		List<NutrientAdvice> listNutrientAdvices = nutrientAdviceBo
				.getDao()
				.queryByNutrientAdviceSummary_DBKey(NutrientAdviceSummary_DBKey);

		for (NutrientAdvice nutrientAdvice : listNutrientAdvices) {
			List<NutrientAdviceDetail> listNutrientAdviceDetails = nutrientAdviceDetailBo
					.getDao().queryAdviceDetail(
							nutrientAdvice.getNutrientAdvice_DBKey());
			for (NutrientAdviceDetail nutrientAdviceDetail : listNutrientAdviceDetails) {
				// 删除NutrientAdviceDetail
				nutrientAdviceDetailBo.getDao().deleteById(
						nutrientAdviceDetail.getNutrientAdviceDetail_DBKEY());
			}
			// 删除NutrientAdvice
			nutrientAdviceBo.getDao().deleteById(
					nutrientAdvice.getNutrientAdvice_DBKey());
		}

		// NutrientAdviceSummary
		getDao().deleteById(NutrientAdviceSummary_DBKey);
	}

	public void saveAdvice(AdviceInputFragment context) throws Exception {

		if (context.nutrientAdviceSummary == null) {
			// 新增
			context.nutrientAdviceSummary = new NutrientAdviceSummary();
			context.nutrientAdviceSummary
					.setNutrientAdviceSummary_DBKey(StringUtil
							.getShortUniqueDBKey());
			context.nutrientAdviceSummary
					.setNutrientAdviceSummaryNo(context.nutrientAdviceSummary
							.getNutrientAdviceSummary_DBKey());
			context.nutrientAdviceSummary
					.setPatientHospitalize_DBKey(Global.currentPatient
							.getPatientHospitalize_DBKey());
			String[] adviceDateArr = context.adviceInfoListener.getAdviceDate();
			if (adviceDateArr[0].equals(adviceDateArr[1])) {
				// 临时医嘱
				context.nutrientAdviceSummary.setAdviceType("2");
			} else {
				// 长期医嘱
				context.nutrientAdviceSummary.setAdviceType("1");
			}
			context.nutrientAdviceSummary.setAdviceBeginDate(adviceDateArr[0]);
			context.nutrientAdviceSummary.setAdviceEndDate(adviceDateArr[1]);
			// 已确认
			context.nutrientAdviceSummary.setAdviceApprovalStatusEN("3");
			context.nutrientAdviceSummary.setAdviceApprovalStatusPN("0");
			context.nutrientAdviceSummary.setAdviceApprovalStatusFood("0");
			// 院内医嘱
			context.nutrientAdviceSummary.setIsOutHospitalAdvice("0");

			// 创建日期、创建人
			context.nutrientAdviceSummary.setCreateTime(DateHelper
					.getInstance().getDataString_1(null));
			context.nutrientAdviceSummary.setCreateBy(Global.loginUser
					.getUser_DBKey() + "");
			context.nutrientAdviceSummary.setCreateIP(Global.LocalIpAddress);
			context.nutrientAdviceSummary
					.setCreateProgram(Global.CreateProgram);
			// 待上传
			context.nutrientAdviceSummary
					.setOperateFlag(OperateFlag.NEED_ADD_TO_SERVER);
			getDao().create(context.nutrientAdviceSummary);
		} else {
			// 更新医嘱日期
			String[] adviceDateArr = context.adviceInfoListener.getAdviceDate();
			if (adviceDateArr[0].equals(adviceDateArr[1])) {
				// 临时医嘱
				context.nutrientAdviceSummary.setAdviceType("2");
			} else {
				// 长期医嘱
				context.nutrientAdviceSummary.setAdviceType("1");
			}
			context.nutrientAdviceSummary.setAdviceBeginDate(adviceDateArr[0]);
			context.nutrientAdviceSummary.setAdviceEndDate(adviceDateArr[1]);
			getDao().update(context.nutrientAdviceSummary);
		}

		context.nutrientAdviceBo.SaveNutrientAdvice(context);
	}
}

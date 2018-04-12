package cn.kancare.mobile.bo.advice;

import java.util.List;

import android.app.Activity;
import cn.kancare.mobile.activity.advice.fragment.AdviceInputFragment;
import cn.kancare.mobile.bean.advice.NutrientAdvice;
import cn.kancare.mobile.common.Global;
import os.zxs.force.common.constant.SyncConstant.OperateFlag;
import cn.kancare.mobile.core.base.BaseBo;
import cn.kancare.mobile.core.sync.DBKeyEntity;
import os.zxs.force.core.util.DateHelper;
import os.zxs.force.core.util.StringUtil;
import cn.kancare.mobile.dao.advice.NutrientAdviceDao;

import com.alibaba.fastjson.JSON;

public class NutrientAdviceBo extends BaseBo<NutrientAdviceDao> {

	public NutrientAdviceBo(Activity context) throws Exception {
		super(context);
	}

	@Override
	protected void setDao() throws Exception {
		dao = new NutrientAdviceDao(context);
	}

	@Override
	public void doDownloadJson(String json) throws Exception {
		if (json == "")
			return;

		List<NutrientAdvice> models = JSON.parseArray(json,
				NutrientAdvice.class);

		for (NutrientAdvice model : models) {
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
				NutrientAdvice nutrientAdvice = dao.queryForId(dbKeyEntity
						.getOldDBKey());
				nutrientAdvice.setOperateFlag(OperateFlag.NONE);
				dao.update(nutrientAdvice);
			}
		}
	}

	public void SaveNutrientAdvice(AdviceInputFragment context)
			throws Exception {
		List<NutrientAdvice> listNutrientAdvices = getDao()
				.queryByNutrientAdviceSummary_DBKey(
						context.nutrientAdviceSummary
								.getNutrientAdviceSummary_DBKey());

		NutrientAdvice nutrientAdvice = null;
		if (listNutrientAdvices.size() == 0) {
			nutrientAdvice = new NutrientAdvice();
			nutrientAdvice.setOperateFlag(OperateFlag.NEED_ADD_TO_SERVER);
			nutrientAdvice.setNutrientAdvice_DBKey(StringUtil.getUniqueDBKey());
			nutrientAdvice
					.setNutrientAdviceSummary_DBKey(context.nutrientAdviceSummary
							.getNutrientAdviceSummary_DBKey());
			nutrientAdvice.setAdviceDate(context.nutrientAdviceSummary
					.getAdviceBeginDate());
			// 创建日期、创建人
			nutrientAdvice.setCreateTime(DateHelper.getInstance()
					.getDataString_1(null));
			nutrientAdvice.setCreateBy(Global.loginUser.getUser_DBKey() + "");
			nutrientAdvice.setCreateIP(Global.LocalIpAddress);
			nutrientAdvice.setCreateProgram(Global.CreateProgram);

			getDao().create(nutrientAdvice);
		}else {
			//编辑
			nutrientAdvice = listNutrientAdvices.get(0);
			nutrientAdvice.setAdviceDate(context.nutrientAdviceSummary
					.getAdviceBeginDate());
			getDao().update(nutrientAdvice);
		}
		context.nutrientAdvice = nutrientAdvice;
		
		context.nutrientAdviceDetailBo.SaveNutrientAdviceDetail(context,
				nutrientAdvice.getNutrientAdvice_DBKey());
	}

}

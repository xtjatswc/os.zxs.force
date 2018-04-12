package cn.kancare.mobile.bo.laboratoryindex;

import java.util.List;

import android.app.Activity;
import cn.kancare.mobile.activity.laboratoryindex.LaboratoryIndexInfoActivity;
import cn.kancare.mobile.bean.laboratoryindex.LaboratoryIndex;
import cn.kancare.mobile.common.Global;
import cn.kancare.mobile.common.constant.RequestCode;
import os.zxs.force.common.constant.SyncConstant.OperateFlag;
import cn.kancare.mobile.core.base.BaseBo;
import cn.kancare.mobile.core.sync.DBKeyEntity;
import os.zxs.force.core.util.StringUtil;
import cn.kancare.mobile.dao.laboratoryindex.LaboratoryIndexDao;

import com.alibaba.fastjson.JSON;

public class LaboratoryIndexBo extends BaseBo<LaboratoryIndexDao> {

	public LaboratoryIndexBo(Activity context) throws Exception {
		super(context);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void setDao() throws Exception {
		dao = new LaboratoryIndexDao(context);
	}

	@Override
	public void doDownloadJson(String json) throws Exception {
		if (json == "")
			return;

		List<LaboratoryIndex> models = JSON.parseArray(json,
				LaboratoryIndex.class);

		for (LaboratoryIndex model : models) {
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
	public void doUploadResult(String json) throws Exception {
		if (!json.equals("")) {
			TestResultBo testResultBo = new TestResultBo(context);

			List<DBKeyEntity> lstSyncResults = JSON.parseArray(json,
					DBKeyEntity.class);
			for (DBKeyEntity dbKeyEntity : lstSyncResults) {

				LaboratoryIndex laboratoryIndex = dao.queryForId(dbKeyEntity
						.getOldDBKey());
				laboratoryIndex.setOperateFlag(OperateFlag.NONE);
				dao.update(laboratoryIndex);
				/*
				 * if (dbKeyEntity.getOperateFlag() ==
				 * OperateFlag.NEED_ADD_TO_SERVER) {
				 * dao.deleteById(dbKeyEntity.getOldDBKey());
				 * 
				 * // 更新外键关联 testResultBo.getDao().UpdateLaboratoryIndex_DBKey(
				 * dbKeyEntity.getNewDBKey(), dbKeyEntity.getOldDBKey()); }
				 */
			}
		}

	}

	public void saveLaboratoryIndex(LaboratoryIndexInfoActivity activity,
			String testTime) throws Exception {

		if (activity.OperateType == RequestCode.NEW_LABORATORYINDEX) {
			LaboratoryIndex laboratoryIndex = new LaboratoryIndex();

			activity.LaboratoryIndex_DBKey = StringUtil.getUniqueDBKey();

			laboratoryIndex
					.setLaboratoryIndex_DBKey(activity.LaboratoryIndex_DBKey);
			laboratoryIndex.setPatientHospitalize_DBKey(Global.currentPatient
					.getPatientHospitalize_DBKey());
			laboratoryIndex.setOperateFlag(OperateFlag.NEED_ADD_TO_SERVER);

			laboratoryIndex.setTestTime(testTime + " 0:0:0");
			laboratoryIndex.setTestType(activity.editTestType.getText()
					.toString());

			getDao().create(laboratoryIndex);
		} else if (activity.OperateType == RequestCode.EDIT_LABORATORYINDEX) {
			LaboratoryIndex laboratoryIndex = getDao().queryForId(
					activity.LaboratoryIndex_DBKey);
			laboratoryIndex.setTestTime(testTime + " 0:0:0");
			laboratoryIndex.setTestType(activity.editTestType.getText()
					.toString());
			getDao().update(laboratoryIndex);
		}

	}
}

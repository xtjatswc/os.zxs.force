package cn.kancare.mobile.bo.laboratoryindex;

import java.sql.SQLException;
import java.util.List;

import android.app.Activity;
import cn.kancare.mobile.activity.laboratoryindex.LaboratoryIndexInfoActivity;
import cn.kancare.mobile.bean.laboratoryindex.TestItemDetail;
import cn.kancare.mobile.bean.laboratoryindex.TestResult;
import cn.kancare.mobile.common.Global;
import os.zxs.force.common.constant.SyncConstant.OperateFlag;
import cn.kancare.mobile.core.base.BaseBo;
import cn.kancare.mobile.core.sync.DBKeyEntity;
import os.zxs.force.core.util.Convert;
import os.zxs.force.core.util.DateHelper;
import os.zxs.force.core.util.StringUtil;
import cn.kancare.mobile.dao.laboratoryindex.TestResultDao;

import com.alibaba.fastjson.JSON;

public class TestResultBo extends BaseBo<TestResultDao> {

	public TestResultBo(Activity context) throws Exception {
		super(context);
	}

	@Override
	protected void setDao() throws Exception {
		dao = new TestResultDao(context);
	}

	@Override
	public void doDownloadJson(String json) throws Exception {
		if (json == "")
			return;

		List<TestResult> models = JSON.parseArray(json, TestResult.class);

		for (TestResult model : models) {
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

			List<DBKeyEntity> lstSyncResults = JSON.parseArray(json,
					DBKeyEntity.class);
			for (DBKeyEntity dbKeyEntity : lstSyncResults) {
				TestResult testResult = dao.queryForId(dbKeyEntity.getOldDBKey());
				testResult.setOperateFlag(OperateFlag.NONE);
				dao.update(testResult);
//				if (dbKeyEntity.getOperateFlag() == OperateFlag.NEED_ADD_TO_SERVER) {
//					dao.deleteById(dbKeyEntity.getOldDBKey());
//				}
			}
		}

	}

	public void saveTestResult(LaboratoryIndexInfoActivity activity,
			String testItemValue, int testItemDetail_DBKey) throws Exception {
		TestItemDetail testItemDetail;
		testItemDetail = activity.testitemDetailBo.getDao().queryForId(
				testItemDetail_DBKey);

		String newTestResult_DBKey = StringUtil.getUniqueDBKey();

		TestResult testResult = new TestResult();
		testResult.setOperateFlag(OperateFlag.NEED_ADD_TO_SERVER);
		testResult.setLaboratoryIndex_DBKey(activity.LaboratoryIndex_DBKey);
		testResult.setTestItemCode(testItemDetail.getTestItemCode());
		testResult.setTestItemValue(testItemValue);
		testResult.setTestItemName(testItemDetail.getTestItemName());
		testResult.setTestItemMinValue(testItemDetail.getTestItemMinValue());
		testResult.setTestItemMaxValue(testItemDetail.getTestItemMaxValue());
		testResult.setTestItemUnit(testItemDetail.getTestItemUnit());
		testResult.setTestItemDetail_DBKey(testItemDetail_DBKey);
		testResult.setTestResult_DBKey(newTestResult_DBKey);
		testResult.setTestResultNo(0);
		testResult.setIsPositive("0");
		testResult.setTestMedium_DBKey(1);
		testResult
				.setCreateTime(DateHelper.getInstance().getDataString_1(null));
		testResult.setCreateBy(Global.loginUser.getUser_DBKey() + "");
		testResult.setCreateIP(Global.LocalIpAddress);
		testResult.setCreateProgram(Global.CreateProgram);
		try {
			if (!testResult.getTestItemValue().equals("")) {
				double value = Convert.cash2Double(testResult
						.getTestItemValue());
				double max = Convert.cash2Double(testResult
						.getTestItemMaxValue());
				double min = Convert.cash2Double(testResult
						.getTestItemMinValue());
				if (value > max) {
					testResult.setIsOverMax("1");
				} else if (value < min) {
					testResult.setIsOverMin("1");
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		getDao().create(testResult);
	}
}

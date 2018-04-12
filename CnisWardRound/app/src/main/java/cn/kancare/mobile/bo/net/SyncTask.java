package cn.kancare.mobile.bo.net;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.widget.ProgressBar;
import android.widget.TextView;
import cn.kancare.mobile.R;
import cn.kancare.mobile.activity.frame.SyncFragment;
import cn.kancare.mobile.bo.sync.BedNumberSyncHandle;
import cn.kancare.mobile.bo.sync.BodyAnalysisReportSyncHandle;
import cn.kancare.mobile.bo.sync.ChinaFoodCompositionSyncHandle;
import cn.kancare.mobile.bo.sync.CourseRecordSyncHandle;
import cn.kancare.mobile.bo.sync.DepartmentSyncHandle;
import cn.kancare.mobile.bo.sync.LaboratoryIndexSyncHandle;
import cn.kancare.mobile.bo.sync.MealRecordSyncHandle;
import cn.kancare.mobile.bo.sync.NutrientAdviceSyncHandle;
import cn.kancare.mobile.bo.sync.PatientSyncHandle;
import cn.kancare.mobile.bo.sync.QuestionnaireResultSyncHandle;
import cn.kancare.mobile.bo.sync.QuestionnaireSyncHandle;
import cn.kancare.mobile.bo.sync.SysCodeSyncHandle;
import cn.kancare.mobile.bo.sync.UserSyncHandle;
import cn.kancare.mobile.common.Global;
import cn.kancare.mobile.common.constant.LogTag;
import cn.kancare.mobile.common.constant.RequestCode;
import os.zxs.force.core.log.CnislogBo;
import cn.kancare.mobile.core.sync.BaseSyncHandle;
import cn.kancare.mobile.core.sync.DoSyncListener;
import os.zxs.force.core.util.DateHelper;
import os.zxs.force.core.util.ExceptionUtil;
import os.zxs.force.core.util.NetUtil;
import os.zxs.force.core.util.PopUtil;

/**
 * 生成该类的对象，并调用execute方法之后 首先执行的是onProExecute方法 其次执行doInBackgroup方法
 * 
 */
public class SyncTask extends AsyncTask<Integer, Integer, String> {

	CnislogBo log;
	private SyncFragment fragment;
	public static String showMsg;
	public static String showMsg2;
	public static String showMsg3;

	ProgressDialog waitDialog;

	private TextView textView;
	private ProgressBar progressBar;
	private Boolean isAsyncPatient;
	private Boolean isAsyncCourserecord;
	private Boolean isAsyncMealRecord;
	private Boolean isAsyncNutrientAdvice;
	private Boolean isAsyncUser;
	private Boolean isAsyncDepartment;
	private Boolean isAsyncBednumber;
	private Boolean isAsyncSysCode;
	private Boolean isAsyncQuestionnaire;
	private Boolean isAsyncQuestionnaireResult;
	private Boolean isAsyncBodyAnalysisReport;
	private Boolean isAsyncLaboratoryIndex;
	private Boolean isAsyncChinaFoodComposition;

	public SyncTask(SyncFragment fragment) {
		super();
		this.fragment = fragment;
		this.textView = fragment.textView;
		this.progressBar = fragment.progressBar;
		this.isAsyncPatient = fragment.switchPatient.isChecked();
		this.isAsyncCourserecord = fragment.switchCourserecord.isChecked();
		this.isAsyncMealRecord = fragment.switchMealRecord.isChecked();
		this.isAsyncNutrientAdvice = fragment.switchNutrientAdvice.isChecked();
		this.isAsyncUser = fragment.switchUser.isChecked();
		this.isAsyncDepartment = fragment.switchDepartment.isChecked();
		this.isAsyncBednumber = fragment.switchBednumber.isChecked();
		this.isAsyncSysCode = fragment.switchSysCode.isChecked();
		this.isAsyncQuestionnaire = fragment.switchQuestionnaire.isChecked();
		this.isAsyncQuestionnaireResult = fragment.switchQuestionnaireResult
				.isChecked();
		this.isAsyncBodyAnalysisReport = fragment.switchBodyAnalysisReport
				.isChecked();
		this.isAsyncLaboratoryIndex = fragment.switchLaboratoryIndex
				.isChecked();
		this.isAsyncChinaFoodComposition = fragment.switchChinaFoodComposition
				.isChecked();
		log = new CnislogBo(Global.currentActivity);
	}

	private void doSync(BaseSyncHandle handle, String tag) {
		try {
			handle.sdate = fragment.textBeginDate.getText().toString();
			handle.edate = fragment.textEndDate.getText().toString();
			handle.isClearData = fragment.chkClear.isChecked();

			showMsg = "正在同步" + tag + "，请稍候……";
			publishProgress(-3);
			handle.doSync(new DoSyncListener() {
				public void over(int progress) {
					publishProgress(progress);
				}
			});

			showMsg2 = tag + "同步成功！";
			publishProgress(-2);
		} catch (Exception e) {
			showMsg3 = tag + "同步失败！";
			publishProgress(-1);
			ExceptionUtil.doException(log, LogTag.ASYNC, e);
		}
	}

	/**
	 * 这里的Integer参数对应AsyncTask中的第一个参数 这里的String返回值对应AsyncTask的第三个参数
	 * 该方法并不运行在UI线程当中，主要用于异步操作，所有在该方法中不能对UI当中的空间进行设置和修改
	 * 但是可以调用publishProgress方法触发onProgressUpdate对UI进行操作
	 */
	@Override
	protected String doInBackground(Integer... params) {
		int i = 0;

		// 测试网络
		if (CheckConnected.TestCnisWebConn()) {
			if (Global.LocalIpAddress == "0.0.0.0") {
				Global.LocalIpAddress = NetUtil.getLocalIpAddress();
			}
		} else {
			// return "与cnis服务器连接失败！";
			return RequestCode.SYNC_EXECUTE_NETWORK_FAILED;
		}

		// 判断服务端与移动端的版本号是否匹配
		if (CheckConnected.getServerVersion() < 2) {
			// return "请将服务端程序升级到最新版后，再点击同步！";
			return RequestCode.SYNC_EXECUTE_VERSION_FAILED;
		}

		if (isAsyncPatient) {
			doSync(new PatientSyncHandle(log), "患者信息");
		}

		if (isAsyncQuestionnaireResult) {
			doSync(new QuestionnaireResultSyncHandle(log), "调查问卷");
		}

		if (isAsyncCourserecord) {
			doSync(new CourseRecordSyncHandle(log), "查房记录");
		}
		
		if (isAsyncNutrientAdvice) {
			doSync(new NutrientAdviceSyncHandle(log), "肠内医嘱");
		}

		if (isAsyncMealRecord) {
			doSync(new MealRecordSyncHandle(log), "膳食调查");
		}

		if (isAsyncBodyAnalysisReport) {
			doSync(new BodyAnalysisReportSyncHandle(log), "人体成分报告");
		}

		if (isAsyncLaboratoryIndex) {
			doSync(new LaboratoryIndexSyncHandle(log), "实验室检查");
		}

		if (isAsyncUser) {
			doSync(new UserSyncHandle(log), "用户信息");
		}

		if (isAsyncDepartment) {
			doSync(new DepartmentSyncHandle(log), "科室");
		}

		if (isAsyncBednumber) {
			doSync(new BedNumberSyncHandle(log), "床位");
		}

		if (isAsyncQuestionnaire) {
			doSync(new QuestionnaireSyncHandle(log), "问卷题库");
		}

		if (isAsyncChinaFoodComposition) {
			doSync(new ChinaFoodCompositionSyncHandle(log), "食材、肠内制剂");
		}

		if (isAsyncSysCode) {
			doSync(new SysCodeSyncHandle(log), "基础字典");
		}

		// 同步完成
		return RequestCode.SYNC_EXECUTE_SUCESS;
	}

	/**
	 * 这里的String参数对应AsyncTask中的第三个参数（也就是接收doInBackground的返回值）
	 * 在doInBackground方法执行结束之后在运行，并且运行在UI线程当中 可以对UI空间进行设置
	 */
	@Override
	protected void onPostExecute(String result) {
		if (result == RequestCode.SYNC_EXECUTE_SUCESS) {
			textView.setText("数据同步执行结束！");
		} else if (result == RequestCode.SYNC_EXECUTE_NETWORK_FAILED){
			textView.setText("与cnis服务器连接失败！");
			PopUtil.show(Global.currentActivity, "网络不给力！");
		}else if (result == RequestCode.SYNC_EXECUTE_VERSION_FAILED){
			textView.setText("请将服务端程序升级到最新版后，再点击同步！");
			PopUtil.show(Global.currentActivity, textView.getText().toString());
		}
		waitDialog.dismiss();
	}

	// 该方法运行在UI线程当中,并且运行在UI线程当中 可以对UI空间进行设置
	@Override
	protected void onPreExecute() {
		textView.setText("开始执行数据同步，请稍候……");
		waitDialog = ProgressDialog.show(Global.currentActivity, "数据同步",
				"同步中，请稍候……");
	}

	/**
	 * 这里的Intege参数对应AsyncTask中的第二个参数
	 * 在doInBackground方法当中，，每次调用publishProgress方法都会触发onProgressUpdate执行
	 * onProgressUpdate是在UI线程中执行，所有可以对UI控件进行操作
	 */
	@Override
	protected void onProgressUpdate(Integer... values) {
		int value = values[0];
		String time = "[" + DateHelper.getInstance().getDataString_4(null)
				+ "]	";
		if (value >= 0) {
			progressBar.setProgress(value);
			textView.setText(showMsg);
		} else if (value == -1) {
			// 同步出异常了
			fragment.AddSyncTip(time + showMsg3, R.color.red);
		} else if (value == -2) {
			// 执行成功
			fragment.AddSyncTip(time + showMsg2, R.color.green);

		} else if (value == -3) {
			// 开始同步
			fragment.AddSyncTip(time + showMsg, R.color.green);

		}
	}

}
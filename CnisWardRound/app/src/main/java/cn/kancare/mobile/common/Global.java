package cn.kancare.mobile.common;

import android.app.Activity;
import cn.kancare.mobile.bean.basic.User;
import cn.kancare.mobile.bean.patient.PatientHospitalizeBasicInfo;
import cn.kancare.mobile.common.constant.SettingCode;
import os.zxs.force.core.App;
import os.zxs.force.core.constant.AppConstant;

public class Global extends os.zxs.force.common.Global {

	/*
	 * 医院个性化 HX 桓兴医院
	 * 
	 * QT 其它医院
	 */
	public static String BUILD_FLAG = "HX";

	// 后台同步接口路径名称
	public static final String WEB_API = "webapi";

	public static Activity currentActivity;

	// Web服务的ip+端口号
	public static String WEB_REQUEST_IP = "127.0.0.1:80";

	// http同步，每次获取的记录条数
	public static final int REQUEST_LIMIT_SIZE = 100;

	// 登录信息
	public static User loginUser = new User();
	public static String LocalIpAddress = "0.0.0.0";
	public static final String CreateProgram = "平板电脑移动查房";
	public static String packageName = App
			.getAppInfo(AppConstant.GET_PACKAGE_NAME);
	public static String rootPackageName = "cn.kancare.mobile";
	public static String version = "";

	// 当前患者信息
	public static PatientHospitalizeBasicInfo currentPatient;

	// 患者列表排序
	public static String PatientListOrderBy = SettingCode.ORDER_BY_BEDNUMBER;
	public static String PatientListOrderByAscDesc = SettingCode.ORDER_BY_DESC;

	// APP模式
	public static String AppMode = SettingCode.APP_MODE_SINGLE;

	// 个性化
	public static String Individuation = SettingCode.INDIVIDUATION_QT;

	// 隐藏历史患者
	public static String IsHideHistoryPatient = SettingCode.HISTORY_PATIENT_DISPLAY;


}

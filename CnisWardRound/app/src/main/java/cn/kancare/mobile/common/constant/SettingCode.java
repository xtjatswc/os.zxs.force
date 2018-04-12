package cn.kancare.mobile.common.constant;

public class SettingCode {
	public static final String SERVER_IP = "SERVER_IP";// 服务器ip
	public static final String PATIENT_LIST_ORDER_BY = "PATIENT_LIST_ORDER_BY";// 患者列表排序
	public static final String APP_MODE = "APP_MODE";// 单机版、内网版
	public static final String PATIENT_LIST_ORDER_BY_ASC_DESC = "PATIENT_LIST_ORDER_BY_ASC_DESC";// 排序规则
	public static final String INDIVIDUATION = "INDIVIDUATION";// 个性化
	public static final String HISTORY_PATIENT = "HIDE_HISTORY_PATIENT";// 隐藏历史患者
																		// 1、隐藏,
																		// ""、显示
	public static final String MACHINE_NO = "MACHINE_NO";// 设备编号

	// 患者列表排序
	public static final String ORDER_BY_DEPARTMENT = "2"; // 按科室排序
	public static final String ORDER_BY_BEDNUMBER = ""; // 按床号排序
	public static final String ORDER_BY_INHOSPITALDATE = "1"; // 按入院日期排序
	public static final String ORDER_BY_DOCTOR = "3"; // 按临床医生排序

	// APP模式
	public static final String APP_MODE_SINGLE = ""; // 单机版
	public static final String APP_MODE_INNER = "1"; // 内网版

	// 排序规则
	public static final String ORDER_BY_ASC = "1"; // 正序
	public static final String ORDER_BY_DESC = ""; // 倒序

	// 个性化
	public static final String INDIVIDUATION_LXH = "刘雪辉";
	public static final String INDIVIDUATION_QT = "";// 其它

	public static final String HISTORY_PATIENT_DISPLAY = "";
	public static final String HISTORY_PATIENT_HIDE = "1";// 隐藏
}

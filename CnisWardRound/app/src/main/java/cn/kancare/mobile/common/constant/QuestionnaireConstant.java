package cn.kancare.mobile.common.constant;

public class QuestionnaireConstant {

	public static final int NRS2002_TYPEVALUE = 1;
	public static final int PGSGA_TYPEVALUE = 2;
	public static final int MUST_TYPEVALUE = 3;
	public static final int MST_TYPEVALUE = 4;
	public static final int MNASF_TYPEVALUE = 5;
	public static final int NRI_TYPEVALUE = 6;
	public static final int SGA_TYPEVALUE = 7;

	// 题目类型
	public class QuestionnaireType {
		// 单选题
		public static final String SINGLE_CHOICE = "1";
		// 多选题
		public static final String MULTIPLE_CHOICE = "2";
	}

	// 问卷状态
	public class QuestionnaireStatus {
		// 已提交
		public static final String SUBMITTED = "1";
		// 待完成
		public static final String UNFINISHED = "0";
	}
}

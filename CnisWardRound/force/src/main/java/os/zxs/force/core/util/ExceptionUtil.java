package os.zxs.force.core.util;

import android.app.Activity;
import os.zxs.force.common.Global;
import os.zxs.force.core.log.CnislogBo;

/**
 * @author Administrator 异常工具类
 */
public class ExceptionUtil {

	public static void doException(Activity context, CnislogBo logBo,
			String logTag, Exception e) {
		String err = getExceptionMessage(e);
		logBo.error(logTag, err);
		PopUtil.AlertDialog(context, "异常信息", logTag + "：" + err);
	}
	
	public static void doException(CnislogBo logBo,
			String logTag, Exception e) {
		String err = getExceptionMessage(e);
		logBo.error(logTag, err);
	}

	/**
	 * 得到异常信息，及调用堆栈
	 * 
	 * @param e
	 * @return
	 */
	public static String getExceptionMessage(Exception e) {
		String message = e.toString();
		if (message.lastIndexOf(":") != -1)
			message = "Exception: "
					+ message.substring(0, message.lastIndexOf(":")) + "\r\n";
		return message + getTraceInfo(e.getStackTrace()).toString();
	}

	/**
	 * 得到当前调用该方法的类名、方法名、行号
	 */
	public static String getCurrentClazzMethod() {
		StringBuffer sb = new StringBuffer();
		getTraceInfo(sb, Thread.currentThread().getStackTrace()[3]);
		return sb.toString();
	}

	/**
	 * 得到调用堆栈信息
	 * 
	 * @return
	 */
	public static String getCurrentStack() {
		Throwable ex = new Throwable();

		StackTraceElement[] stackElements = ex.getStackTrace();

		return getTraceInfo(stackElements).toString();
	}

	private static StringBuffer getTraceInfo(StackTraceElement[] stacks) {
		StringBuffer sb = new StringBuffer();
		sb.append("\r\n");
		for (int i = 0; i < stacks.length; i++) {
			if (stacks[i].getClassName().contains(Global.rootPackageName)) {
				getTraceInfo(sb, stacks[i]);
				// break;
			}
		}
		return sb;
	}

	private static void getTraceInfo(StringBuffer sb, StackTraceElement stack) {
		sb.append("class: ").append(stack.getClassName()).append("; method: ")
				.append(stack.getMethodName()).append("; line: ")
				.append(stack.getLineNumber()).append(";").append("\r\n");
	}
}

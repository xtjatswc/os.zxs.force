package os.zxs.force.core.util;

import java.util.Date;
import os.zxs.force.common.Global;

public class StringUtil {

	/*
	 * 截取字符串中的字符跟数字 取前字符、后数字，如果最后又是字符，则不取，如 "ab125c",则截取为"ab"和"125"
	 */
	public static String[] subStrNum(String input) {

		if (input == null || input.trim().equals(""))
			return new String[] { "", "" };

		String str1 = "";
		String str2 = "";
		Boolean flag = false;
		for (char c : input.toCharArray()) {
			// 数字
			if (c >= 48 && c <= 57) {
				str2 += c;
				flag = true;
			} else {
				if (flag) {
					break;
				}
				str1 += c;
			}
		}

		return new String[] { str1, str2 };
	}

	private static String latestDBKey = "";

	public static String getUniqueDBKey() {
		String dbkey = "";
		while (true) {
			dbkey = "-"
					+ DateHelper.getInstance().getDataString(new Date(),
							"yyMMddHHmmssSSS") + Global.MachineNo;

			// 避免生成重复的dbkey
			if (!dbkey.equals(latestDBKey)) {
				latestDBKey = dbkey;
				break;
			}
		}
		return dbkey;
	}

	public static String getShortUniqueDBKey() {
		return "-" + (new Date().getTime() % 9876543210L) + Global.MachineNo;
	}
}

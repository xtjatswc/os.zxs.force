package os.zxs.force.core.util;

import java.util.ArrayList;

public class PinyinUtil {

	public enum HanyuPinyinCaseType {
		UPPERCASE, LOWERCASE
	}

	/**
	 * 汉字转换拼音(全)，字母原样返回，都转换为大写
	 * 
	 * @param input
	 * @return
	 */
	public static String getPinYin(String input) {
		ArrayList<HanziToPinyin.Token> tokens = HanziToPinyin.getInstance()
				.get(input);
		StringBuilder sb = new StringBuilder();
		if (tokens != null && tokens.size() > 0) {
			for (HanziToPinyin.Token token : tokens) {
				if (token.type == HanziToPinyin.Token.PINYIN) {
					sb.append(token.target);
				} else {
					sb.append(token.source);
				}
			}
		}
		return sb.toString();
	}

	/**
	 * 汉字转换拼音(全)，字母原样返回
	 * 
	 * @param input
	 * @param type
	 *            返回大写还是小写
	 * @return
	 */
	public static String getPinYin(String input, HanyuPinyinCaseType type) {
		String result = getPinYin(input);
		if (type == HanyuPinyinCaseType.LOWERCASE) {
			return result.toLowerCase();
		}
		return result;
	}

	/**
	 * 汉字转换拼音(全)，字母原样返回
	 * 
	 * @param input
	 * @param type
	 *            返回大写还是小写
	 * @param onlyFirstLetter
	 *            只返回首字母？
	 * @return
	 */
	public static String getPinYin(String input, HanyuPinyinCaseType type,
			Boolean onlyFirstLetter) {
		ArrayList<HanziToPinyin.Token> tokens = HanziToPinyin.getInstance()
				.get(input);
		StringBuilder sb = new StringBuilder();
		if (tokens != null && tokens.size() > 0) {
			for (HanziToPinyin.Token token : tokens) {
				if (token.type == HanziToPinyin.Token.PINYIN) {
					if (onlyFirstLetter) {
						if (token.target.length() > 0) {
							sb.append(token.target.substring(0, 1));
						}
					} else {
						sb.append(token.target);
					}
				} else {
					sb.append(token.source);
				}
			}
		}

		if (type == HanyuPinyinCaseType.LOWERCASE) {
			return sb.toString().toLowerCase();
		}

		return sb.toString();

	}
}

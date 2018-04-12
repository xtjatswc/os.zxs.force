package os.zxs.force.core.util;

public class Convert {
	public static int cash2Int(String value) {
		try {
			return Double.valueOf(value).intValue();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public static int cash2Int(Object value) {
		try {
			return Double.valueOf(String.valueOf(value)).intValue();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public static int cash2Int(double value) {
		try {
			return (int) value;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public static double cash2Double(String value) {
		try {
			return Double.parseDouble(value);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return 0;
	}

	/**
	 * 四舍五入
	 * 
	 * @param decimal
	 *            保留的小数位数
	 * @return
	 */
	public static double Round(Double obj, int decimal) {
		try {
			return Double.parseDouble(String.format("%." + decimal + "f", obj));
		} catch (Exception e) {
			// TODO: handle exception
		}
		return 0;
	}

	/**
	 * 四舍五入
	 * 
	 * @param decimal
	 *            保留的小数位数
	 * @return
	 */
	public static double Round(String obj, int decimal) {
		try {
			return Double.parseDouble(String.format("%." + decimal + "f",
					cash2Double(obj)));
		} catch (Exception e) {
			// TODO: handle exception
		}
		return 0;
	}

	/**
	 * 四舍五入
	 * 
	 * @param decimal
	 *            保留的小数位数
	 * @return
	 */
	public static String RoundString(Double obj, int decimal) {
		try {
			return String.format("%." + decimal + "f", obj);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "0";
	}

	/**
	 * 四舍五入 去掉无效的0，比如0.30转换为0.3
	 * 
	 * @param decimal
	 *            保留的小数位数
	 * @return
	 */
	public static String RoundString2(String obj, int decimal) {
		return RoundString2(cash2Double(obj), decimal);
	}

	/**
	 * 四舍五入 去掉无效的0，比如0.30转换为0.3
	 * 
	 * @param decimal
	 *            保留的小数位数
	 * @return
	 */
	public static String RoundString2(Double obj, int decimal) {
		try {

			String s = RoundString(obj, decimal);
			if (s.indexOf(".") > 0) {
				// 正则表达
				s = s.replaceAll("0+?$", "");// 去掉后面无用的零
				s = s.replaceAll("[.]$", "");// 如小数点后面全是零则去掉小数点
			}

			return s;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "0";
	}
}

package com.qx.core.utils;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class NumberFormatUtils {

	/**
	 * 将long格式，以分为单位的金额格式化成货币格式
	 * 
	 * @author zz
	 * @since 2017-3-24
	 * @param amount
	 * @return String
	 */
	public static String formatLong2Currency(long amount) {
		StringBuilder sb = new StringBuilder();
		sb.append(amount);
		while (sb.length() < 3) {
			sb.insert(0, "0");
		}
		sb.insert(sb.length() - 2, ".");
		return sb.toString();
	}

	/**
	 * 
	 * @Title: formatString2rate @Description: 将字符串费率乘100转费率 @param @param
	 * rate @param @return @return double @throws
	 */
	public static double formatString2rate(String rate) {
		return Math.ceil(Double.valueOf(rate) * 100);
	}
	/**
	 * 
	 * @Title: formatString2rate @Description: 将字符串费率除100转费率 @param @param
	 * rate @param @return @return double @throws
	 */
	public static double formatString2rateDivision(String rate) {
		return Math.ceil(Double.valueOf(rate) / 100);
	}

	

	/**
	 * 
	 * 货币格式化<BR>
	 * 方法名：formatCurrency<BR>
	 * 创建人：wangbeidou <BR>
	 * 时间：2017年3月9日-上午9:56:43 <BR>
	 * 
	 * @param pInput
	 * @param inLocale
	 * @param pattern
	 * @return String<BR>
	 * @exception <BR>
	 * @since 2.0
	 */
	public static String formatCurrency(double pInput, Locale inLocale, String pattern) {

		DecimalFormatSymbols symbols = new DecimalFormatSymbols(inLocale);
		DecimalFormat formatter = new DecimalFormat(pattern, symbols);
		formatter.setMinimumFractionDigits(2);
		return formatter.format(pInput);
	}

	/**
	 * 
	 * 格式化输出 浮点数 <BR>
	 * 方法名：format<BR>
	 * 创建人：wangbeidou <BR>
	 * 时间：2017年3月9日-上午9:57:08 <BR>
	 * 
	 * @param d
	 *            双精度浮点数
	 * @param max
	 *            小数点后-最大保留位数
	 * @param min
	 *            小数点后-最小保留位数(默认为 2 ,不足补0)
	 * @return String<BR>
	 * @exception <BR>
	 * @since 2.0
	 */
	public static String format(Double d, Integer max, Integer min) {
		if (null == d) {
			return "";
		}
		Integer _min = (null == min || min < 0) ? 2 : min;
		String pattern = "0";
		DecimalFormat formatter = new DecimalFormat(pattern);
		if (null != _min) {
			formatter.setMinimumFractionDigits(_min);
		}
		if (null != max) {
			formatter.setMaximumFractionDigits(max);
		}
		return formatter.format(d);
	}
}

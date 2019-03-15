package com.qx.core.utils;

import java.math.BigDecimal;

/**
 * 数值计算工具类
 * 
 * NumberCalculateUtil<BR>
 * 创建人:wangbeidou <BR>
 * 时间：2017年3月14日-上午10:33:30 <BR>
 * @version 2.0
 *
 */
public class NumberCalculateUtil {

	/**
	 *  加法  
	 * 两个double数值相加<BR>
	 * 方法名：add<BR>
	 * 创建人：wangbeidou <BR>
	 * 时间：2017年3月14日-上午10:37:47 <BR>
	 * @param v1
	 * @param v2
	 * @return double<BR>
	 * @exception <BR>
	 * @since  2.0
	 */
	public static double add(double v1, double v2) {
		BigDecimal b1 = BigDecimal.valueOf(v1);
		BigDecimal b2 = BigDecimal.valueOf(v2);
		return b1.add(b2).doubleValue();
	}

	/**
	 * 
	 * 减法  <BR>
	 * 方法名：sub<BR>
	 * 创建人：wangbeidou <BR>
	 * 时间：2017年3月14日-上午10:39:35 <BR>
	 * @param v1
	 * @param v2
	 * @return double<BR>
	 * @exception <BR>
	 * @since  2.0
	 */
	public static double sub(double v1, double v2) {
		BigDecimal b1 = BigDecimal.valueOf(v1);
		BigDecimal b2 = BigDecimal.valueOf(v2);
		return b1.subtract(b2).doubleValue();
	}

	/**
	 * 
	 * 乘法<BR>
	 * 方法名：mul<BR>
	 * 创建人：wangbeidou <BR>
	 * 时间：2017年3月14日-上午10:40:17 <BR>
	 * @param v1
	 * @param v2
	 * @return double<BR>
	 * @exception <BR>
	 * @since  2.0
	 */
	public static double mul(double v1, double v2) {
		BigDecimal b1 = BigDecimal.valueOf(v1);
		BigDecimal b2 = BigDecimal.valueOf(v2);
		return b1.multiply(b2).doubleValue();
	}

	/**
	 * 
	 * 除法  <BR>
	 * 方法名：div<BR>
	 * 创建人：wangbeidou <BR>
	 * 时间：2017年3月14日-上午10:40:32 <BR>
	 * @param v1
	 * @param v2
	 * @return double<BR>
	 * @exception <BR>
	 * @since  2.0
	 */
	public static double div(double v1, double v2) {
		BigDecimal b1 = BigDecimal.valueOf(v1);
		BigDecimal b2 = BigDecimal.valueOf(v2);
		return b1.divide(b2, 2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	/**
	 *  加法  
	 * 两个double数值相加<BR>
	 * 方法名：add<BR>
	 * 创建人：wangbeidou <BR>
	 * 时间：2017年3月14日-上午10:37:47 <BR>
	 * @param v1
	 * @param v2
	 * @return double<BR>
	 * @exception <BR>
	 * @since  2.0
	 */
	public static long add(long v1, long v2) {
		return v1 + v2;
	}

	/**
	 * 
	 * 减法  <BR>
	 * 方法名：sub<BR>
	 * 创建人：wangbeidou <BR>
	 * 时间：2017年3月14日-上午10:39:35 <BR>
	 * @param v1
	 * @param v2
	 * @return double<BR>
	 * @exception <BR>
	 * @since  2.0
	 */
	public static long sub(long v1, long v2) {
		return v1 - v2;
	}

	/**
	 * 
	 * 乘法<BR>
	 * 方法名：mul<BR>
	 * 创建人：wangbeidou <BR>
	 * 时间：2017年3月14日-上午10:40:17 <BR>
	 * @param v1
	 * @param v2
	 * @return double<BR>
	 * @exception <BR>
	 * @since  2.0
	 */
	public static long mul(long v1, long v2) {
		return v1 * v2;
	}

	/**
	 * 
	 * 除法  <BR>
	 * 方法名：div<BR>
	 * 创建人：wangbeidou <BR>
	 * 时间：2017年3月14日-上午10:40:32 <BR>
	 * @param v1
	 * @param v2
	 * @return double<BR>
	 * @exception <BR>
	 * @since  2.0
	 */
	public static long div(long v1, long v2) {
		BigDecimal b1 = BigDecimal.valueOf(v1);
		BigDecimal b2 = BigDecimal.valueOf(v2);
		return b1.divide(b2, 2, BigDecimal.ROUND_HALF_UP).longValue();
	}
}

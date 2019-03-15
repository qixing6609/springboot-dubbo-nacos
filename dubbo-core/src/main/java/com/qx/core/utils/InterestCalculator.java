package com.qx.core.utils;

import java.math.BigInteger;


/**
 * @author zz
 * @date 2017年1月18日 下午4:11:35
 * @Description: 利息计算器
 */
public final class InterestCalculator {

	public static final BigInteger I12 = BigInteger.valueOf(12);
	public static final BigInteger I360 = BigInteger.valueOf(360);
	public static final BigInteger I30 = BigInteger.valueOf(30);
	public static final BigInteger I100000 = BigInteger.valueOf(100000);

	/**
	 * 计算利息
	 * @Description: 根据传入的参数计算预计的利息金额
	 * @param amount     本金，单位为分
	 * @param periodType 期限类型：1#日| 2#月| 3#年
	 * @param period     期限
	 * @param rate       利率，单位是十万分之一
	 * @return long      利息金额
	 */
	@Deprecated
	protected static long calcOld(long amount, int periodType, int period, int rate) {
		// 一个更糙快猛的实现
		long ret = amount * period * rate;
		// 期限类型
		switch (periodType) {
		case 1: // 日
			ret /= 360;
			break;
		case 2: // 月
			ret /= 12;
			break;
		case 3: // 年
			break;
		default: // 默认按月计算
			ret /= 12;
			break;
		}
		ret /= 100000;
		return ret;
	}

	/**
	 * 计算利息
	 * @Description: 根据传入的参数计算预计的利息金额
	 * @param amount		本金，单位为分
	 * @param periodType	期限类型：1#日| 2#月| 3#年
	 * @param period		期限
	 * @param rate			利率，单位是十万分之一
	 * @return BigInteger	利息金额
	 */
	protected static BigInteger calcCore(long amount, int periodType, int period, int rate) {
		// 本金
		BigInteger bi = BigInteger.valueOf(amount);
		// 年利率
		bi = bi.multiply(BigInteger.valueOf(rate));
		// 期限
		bi = bi.multiply(BigInteger.valueOf(period));
		// 除法
		switch (periodType) {
		case 1: // 日
			bi = bi.divide(I360);
			break;
		case 2: // 月
			bi = bi.divide(I12);
			break;
		case 3: // 年
			break;
		default: // 默认按月计算
			bi = bi.divide(I12);
			break;
		}
		bi = bi.divide(I100000);
		return bi;
	}
	
	/**
	 * 计算利息
	 * @Description: 根据传入的参数计算预计的利息金额
	 * @param amount		本金，单位为分
	 * @param periodType	期限类型：1#日| 2#月| 3#年
	 * @param period		期限
	 * @param rate			利率，单位是十万分之一
	 * @return long			利息金额
	 */
	public static long calc(long amount, int periodType, int period, int rate) {
		BigInteger bi = calcCore(amount, periodType, period, rate);
		return bi.longValue();
	}

	/**
	 * 计算实际到期利息，包括计划中的利息和逾期利息
	 * @param principal		本金，单位为分
	 * @param periodType	期限类型：1#日| 2#月| 3#年
	 * @param period		期限
	 * @param rate			利率，单位是十万分之一
	 * @param overdueDay	逾期天数
	 * @return long			利息金额
	 */
	public static long calcActural(long principal, int periodType, int period, int rate, int overdueDay) {
		BigInteger i1 = calcCore(principal, periodType, period, rate);
		if (overdueDay > 0) {
			BigInteger i2 = calcCore(principal, 1, overdueDay, rate);
			i1 = i1.add(i2);
		}
		return i1.longValue();
	}
	
	/**
	 * 
	 * 描述：计算每天的利息
	 * 方法名：countInterestEvDay
	 * 创建人：gaojy
	 * 时间：2017年3月22日下午3:36:48
	 * @param interest 总利息
	 * @param periodType 期限类型
	 * @param period 期限
	 * @return
	 */
	public static long countInterestEvDay(long interest, int periodType, int period) {
		long everyDayInterest = 0;
		BigInteger bi = BigInteger.valueOf(interest);
		switch (periodType) {
//		case EasyDepositDef.PERIOD_TYPE_YEAR://年
//			everyDayInterest = bi.divide(I360.multiply(BigInteger.valueOf(period))).longValue();
//			break;
//		case EasyDepositDef.PERIOD_TYPE_MONTH://月
//			everyDayInterest = bi.divide(I30.multiply(BigInteger.valueOf(period))).longValue();
//			break;
//		case EasyDepositDef.PERIOD_TYPE_DAY://日
//			everyDayInterest = bi.divide(BigInteger.valueOf(period)).longValue();
//			break;
		default:
			everyDayInterest = bi.divide(I360.multiply(BigInteger.valueOf(period))).longValue();
			break;
		}

		return everyDayInterest;
	}

}

package com.qx.core.utils;

/**
 * @author zz
 * @date 2017年1月9日 上午9:43:16
 * @Description: 环境变量配置bean
 * 
 */
public final class EnvConfig {
	
	private static String envName = null;
	
	private static String smsAlarmName = null;
	
	private static boolean isOnline = false;
	
	private static boolean isSmsAlarmOpen = true;
	
	synchronized public static void setEnv(String env) {
		if (envName == null) {
			envName = env;
			isOnline = "online".equals(envName);
		}
	}
	
	synchronized public static void setSmsAlarm(String smsAlarm){
		if(smsAlarmName==null){
			smsAlarmName = smsAlarm;
			isSmsAlarmOpen = "open".equals(smsAlarmName);
		}
	}
	
	public static String getEnvName() {
		return envName;
	}
	
	public static boolean isOnline() {
		return isOnline;
	}

	public static boolean isSmsAlarmOpen() {
		return isSmsAlarmOpen;
	}

	public static String getSmsAlarmName() {
		return smsAlarmName;
	}

	
	
}

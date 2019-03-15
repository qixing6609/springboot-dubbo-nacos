package com.qx.core.utils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UMengMessageUtil {
	private static Logger logger = LoggerFactory.getLogger(UMengMessageUtil.class);
	
	protected static final String appKey = "5a4210b3a40fa339e3000071";
	// The host
	protected static final String host = "http://msg.umeng.com";
	
	// The upload path
	protected static final String uploadPath = "/upload";
	
	// The post path
	protected static final String postPath = "/api/send";
	
	public String notification(String deviceType, String type, String alias_type, String alias, String custom){
		Map<String, Object> data = new HashMap<>();
		// 必填 应用唯一标识
		data.put("appkey", appKey);
		// 必填 时间戳，10位或者13位均可，时间戳有效期为10分钟
		String timestamp = Integer.toString((int)(System.currentTimeMillis() / 1000));
		data.put("timestamp", timestamp);
		/**
		 *  必填 消息发送类型,其值可以为:
		 *  unicast-单播
         *   listcast-列播(要求不超过500个device_token)
         *   filecast-文件播
         *   (多个device_token可通过文件形式批量发送）
         *   broadcast-广播
         *   groupcast-组播
         *   (按照filter条件筛选特定用户群, 具体请参照filter参数)
         *   customizedcast(通过开发者自有的alias进行推送), 
         *     包括以下两种case:
         *     - alias: 对单个或者多个alias进行推送
         *     - file_id: 将alias存放到文件后，根据file_id来推送
		 */
		data.put("type", type);
		/**
		 * 可选 设备唯一表示
         *  当type=unicast时,必填, 表示指定的单个设备
         *  当type=listcast时,必填,要求不超过500个,
         *  多个device_token以英文逗号间隔
		 */
		data.put("device_tokens", null);
		/**
		 * 可选 当type=customizedcast时，必填，alias的类型, 
         *    alias_type可由开发者自定义,开发者在SDK中
         *    调用setAlias(alias, alias_type)时所设置的alias_type
		 */
		data.put("alias_type", alias_type);
		/**
		 * 可选 当type=customizedcast时, 开发者填写自己的alias。
         *   要求不超过50个alias,多个alias以英文逗号间隔。
         *   在SDK中调用setAlias(alias, alias_type)时所设置的alias
		 */
		data.put("alias", alias);
		/**
		 * 可选 当type=filecast时，file内容为多条device_token, 
         *   device_token以回车符分隔
         *   当type=customizedcast时，file内容为多条alias，
         *   alias以回车符分隔，注意同一个文件内的alias所对应
         *   的alias_type必须和接口参数alias_type一致。
         *   注意，使用文件播前需要先调用文件上传接口获取file_id, 
		 */
		data.put("file_id", null);
		/**
		 * 可选 终端用户筛选条件,如用户标签、地域、应用版本以及渠道等
		 */
		data.put("filter", null);
		
		Map<String, Object> payload = null;
		if ("android".equals(deviceType)) {
			payload = androidPayload("message", custom);
		}
		
		if ("ios".equals(deviceType)) {
			payload = iosPayload(custom);
		}
		
		/**
		 * 必填 消息内容(Android最大为1840B)
		 */
		data.put("payload", payload);
		/**
		 * 可选 正式/测试模式。测试模式下，广播/组播只会将消息发给测试设备。
		 */
		data.put("production_mode", null);
		/**
		 * 可选 发送消息描述，建议填写。
		 */
		data.put("description", null);
		
		
		return null;
	}
	
	/**
	 * 
	 * android消息体<BR>
	 * 方法名：androidPayload<BR>
	 * 创建人：wangbeidou <BR>
	 * 时间：2018年2月6日-上午11:54:22 <BR>
	 * @param display_type
	 * @param custom
	 * @return Map<String,Object><BR>
	 * @exception <BR>
	 * @since  2.0
	 */
	private Map<String, Object> androidPayload(String display_type, String custom){
		Map<String, Object> data = new HashMap<>();
		/**
		 * 必填 消息类型，值可以为:
         * notification-通知，message-消息
		 */
		data.put("display_type", display_type);
		
		/**
		 * 必填 消息体。
		 */
		Map<String, Object> body = new HashMap<>();
		if ("notification".equals(display_type)) {
			// 必填 通知栏提示文字
			body.put("ticker", null);
			// 必填 通知标题
			body.put("title", null);
			// 必填 通知文字描述 
			body.put("text", null);
			/**
			 *  自定义通知图标:
			 *  可选 状态栏图标ID, R.drawable.[smallIcon],
             *  如果没有, 默认使用应用图标。
             *  图片要求为24*24dp的图标,或24*24px放在drawable-mdpi下。
             *  注意四周各留1个dp的空白像素
			 */
			body.put("icon", null);
			/**
			 * 可选 通知栏拉开后左侧图标ID, R.drawable.[largeIcon].
             * 图片要求为64*64dp的图标,
             * 可设计一张64*64px放在drawable-mdpi下,
             *  注意图片四周留空，不至于显示太拥挤
			 */
			body.put("largeIcon", null);
			/**
			 * 可选 通知栏大图标的URL链接。该字段的优先级大于largeIcon。
             * 该字段要求以http或者https开头。
			 */
			body.put("img", null);
			/**
			 * 可选 通知声音，R.raw.[sound]. 
             *  如果该字段为空，采用SDK默认的声音, 即res/raw/下的
             *  umeng_push_notification_default_sound声音文件
             * 如果SDK默认声音文件不存在，
             * 则使用系统默认的Notification提示音。
			 */
			body.put("sound", null);
			/**
			 * 可选 默认为0，用于标识该通知采用的样式。使用该参数时, 
             * 开发者必须在SDK里面实现自定义通知栏样式。
			 */
			body.put("builder_id", null);
			/**
			 * 通知到达设备后的提醒方式
			 * 可选 收到通知是否震动,默认为"true".
			 */
			body.put("play_vibrate", null);
			/**
			 * 可选 收到通知是否闪灯,默认为"true"
			 */
			body.put("play_lights", null);
			/**
			 * 可选 收到通知是否发出声音,默认为"true"
			 */
			body.put("play_sound", null);
			/**
			 * 点击"通知"的后续行为，默认为打开app。
			 * 必填 值可以为:
             *   "go_app": 打开应用
             *   "go_url": 跳转到URL
             *   "go_activity": 打开特定的activity
             *   "go_custom": 用户自定义内容。
			 */
			body.put("after_open", null);
			/**
			 * 可选 当"after_open"为"go_url"时，必填。
             *  通知栏点击后跳转的URL，要求以http或者https开头  
			 */
			body.put("url", null);
			/**
			 * 可选 当"after_open"为"go_activity"时，必填。
             * 通知栏点击后打开的Activity
			 */
			body.put("activity", null);
		}
		/**
		 * 可选 display_type=message, 或者
		 * display_type=notification且
		 * "after_open"为"go_custom"时，
		 * 该字段必填。用户自定义内容, 可以为字符串或者JSON格式。
		 */
		body.put("custom", custom);
		
		data.put("body", body);
		
		return data;
	}
	
	/**
	 * 
	 * ios消息体<BR>
	 * 方法名：iosPayload<BR>
	 * 创建人：wangbeidou <BR>
	 * 时间：2018年2月6日-上午11:53:59 <BR>
	 * @param alert
	 * @return Map<String,Object><BR>
	 * @exception <BR>
	 * @since  2.0
	 */
	private Map<String, Object> iosPayload(String alert){
		Map<String, Object> data = new HashMap<>();
		
		Map<String, Object> aps = new HashMap<>();
		aps.put("alert", alert);
		aps.put("badge", null);
		aps.put("sound", null);
		aps.put("content-available", null);
		aps.put("category", null);
		
		data.put("aps", aps);
		return data;
	}

}

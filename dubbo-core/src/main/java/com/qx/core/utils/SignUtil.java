package com.qx.core.utils;

import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

/**
 * 签名工具类
 * 
 * SignUtil<BR>
 * 创建人:wangbeidou <BR>
 * 时间：2018年6月28日-下午4:36:13 <BR>
 * 
 * @version 2.0
 *
 */
public class SignUtil {

	public static void main(String[] args) {
		// 签名校验
		Map<String, Object> signMap = new TreeMap<>();
		System.out.println("签名：" + sign(signMap));
		;
	}

	/**
	 * 
	 * 签名<BR>
	 * 方法名：sign<BR>
	 * 创建人：wangbeidou <BR>
	 * 时间：2018年6月28日-下午4:36:31 <BR>
	 * 
	 * @param parameters
	 * @return String<BR>
	 * @exception <BR>
	 * @since 2.0
	 */
	public static String sign(Map<String, Object> parameters) {
		Map<String, Object> sortedmap = getSortedData(parameters);
		StringBuffer bs = new StringBuffer();
		for (Entry<String, Object> o : sortedmap.entrySet()) {
			if (!"sign".equals(o.getKey())) {
				bs.append(o.getKey() + "=" + o.getValue() + "&");
			}
		}
		String result = bs.deleteCharAt(bs.length() - 1).toString();
		System.out.println(result);
		return MD5.EncoderByMd5(result);
	}

	public static Map<String, Object> getSortedData(Map<String, Object> map) {
		if (map == null) {
			return null;
		}

		Map<String, Object> m = new TreeMap<String, Object>();
		for (Entry<String, Object> o : map.entrySet()) {
			m.put(o.getKey(), o.getValue());
		}
		return m;
	}

	/**
	 * 加密字符串
	 *
	 * @param payload
	 * @param secret
	 * @return
	 */
	public static String base64Hmac256(String payload, String secret) {
		try {
			payload = payload.replaceAll("\\s*", "");
			Mac sha256Hmac = Mac.getInstance("HmacSHA256");
			SecretKeySpec secretKey = new SecretKeySpec(secret.getBytes("UTF-8"), "HmacSHA256");
			sha256Hmac.init(secretKey);
			return Base64.encodeBase64String(sha256Hmac.doFinal(payload.getBytes()));
		} catch (Exception ignored) {
			return "";
		}
	}

}

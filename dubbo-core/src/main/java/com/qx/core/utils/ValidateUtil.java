package com.qx.core.utils;


import java.util.Collection;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.qx.core.common.exception.WebBusinessException;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;



public class ValidateUtil {
	public static void isTrue(boolean expression, String message) {
		if (!expression)
			throw new WebBusinessException(message);
	}

	public static void isTrue(boolean expression) {
		isTrue(expression, "[Assertion failed] - this expression must be true");
	}

	public static void isNull(Object object, String message) {
		if (object != null)
			throw new WebBusinessException(message);
	}

	public static void isNull(Object object) {
		isNull(object, "[Assertion failed] - the object argument must be null");
	}

	public static void notNull(Object object, String message) {
		if (object == null)
			throw new WebBusinessException(message);
	}

	public static void notNull(Object object) {
		notNull(object,
				"[Assertion failed] - this argument is required; it must not be null");
	}

	public static void hasLength(String text, String message) {
		if (!StringUtils.hasLength(text))
			throw new WebBusinessException(message);
	}

	public static void hasLength(String text) {
		hasLength(
				text,
				"[Assertion failed] - this String argument must have length; it must not be null or empty");
	}

	public static void hasText(String text, String message) {
		if (!StringUtils.hasText(text))
			throw new WebBusinessException(message);
	}

	public static void hasText(String text) {
		hasText(text,
				"[Assertion failed] - this String argument must have text; it must not be null, empty, or blank");
	}

	public static void doesNotContain(String textToSearch, String substring,
			String message) {
		if ((StringUtils.hasLength(textToSearch))
				&& (StringUtils.hasLength(substring))
				&& (textToSearch.indexOf(substring) != -1))
			throw new WebBusinessException(message);
	}

	public static void doesNotContain(String textToSearch, String substring) {
		doesNotContain(textToSearch, substring,
				"[Assertion failed] - this String argument must not contain the substring ["
						+ substring + "]");
	}

	public static void notEmpty(Object[] array, String message) {
		if (ObjectUtils.isEmpty(array))
			throw new WebBusinessException(message);
	}

	public static void notEmpty(Object[] array) {
		notEmpty(
				array,
				"[Assertion failed] - this array must not be empty: it must contain at least 1 element");
	}

	public static void noNullElements(Object[] array, String message) {
		if (array != null)
			for (int i = 0; i < array.length; i++)
				if (array[i] == null)
					throw new WebBusinessException(message);
	}

	public static void noNullElements(Object[] array) {
		noNullElements(array,
				"[Assertion failed] - this array must not contain any null elements");
	}

	public static void notEmpty(Collection<?> collection, String message) {
		if (CollectionUtils.isEmpty(collection))
			throw new WebBusinessException(message);
	}

	public static void notEmpty(Collection<?> collection) {
		notEmpty(
				collection,
				"[Assertion failed] - this collection must not be empty: it must contain at least 1 element");
	}

	public static void notEmpty(Map<?, ?> map, String message) {
		if (CollectionUtils.isEmpty(map))
			throw new WebBusinessException(message);
	}

	public static void notEmpty(Map<?, ?> map) {
		notEmpty(
				map,
				"[Assertion failed] - this map must not be empty; it must contain at least one entry");
	}

	public static void regexCheck(String data, String regex, String message) {
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(data);
		boolean isMatched = matcher.matches();
		if (!isMatched)
			throw new WebBusinessException(message);
	}
	
//	public static void checkVerifyCode(String verifyCode, String message) {
//		String code = (String) ThreadContent.request().getSession().getAttribute("verifyCode");
//		if (!verifyCode.equals(code))
//			throw new WebBusinessException(message);
//	}
	
	public static void checkRange(String data, int start,int end,String message) {
		int l = data.length();
		if (l<start || l>end)
			throw new WebBusinessException(message);
	}
	
	public static void isDate(String data, String pattern, String message) {
		if(!StringUtils.isEmpty(data)){
			try{
				DateUtil.convertString2Date(data, pattern);
			}catch(Exception e){
				throw new WebBusinessException(message);
			}
		}
	}
	
}
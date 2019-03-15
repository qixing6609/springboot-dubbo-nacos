package com.qx.core.utils;

import java.util.Date;
import java.util.Map;

/**
 * Created by Administrator on 2017/3/6.
 */
public class MapUtil {

    /**
     * 判断map中的值是否为空
     *
     * @param map
     * @param key
     * @return
     */
    public static boolean notBlank(Map<String, Object> map, String key) {
        if (map.get(key) != null && !map.get(key).toString().trim().equals("")) {
            return true;
        }
        return false;

    }

    public static boolean notZero(Map<String, Object> map, String key) {
        if (map.get(key) != null && !map.get(key).toString().equals("0")) {
            return true;
        }
        return false;

    }

    public static String getValue(Object o) {
        if (o == null) {
            return "";
        } else {
            return o.toString();
        }
    }

    public static void formatDateInMap(Map<String, Object> hashMap, String key) {
        hashMap.put(key, DateUtil.formatDate(((Date) hashMap.get(key)), DateUtil.DATE_TIME_LOCALE));
    }


}

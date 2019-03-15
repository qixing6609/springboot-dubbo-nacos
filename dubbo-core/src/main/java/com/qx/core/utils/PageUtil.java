package com.qx.core.utils;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/3/6.
 */
public class PageUtil {

    /**
     * 为列表添加序号
     *
     * @param mapList
     */
    public static void addIndex(List<Map<String, Object>> mapList, Integer currentPage, Integer pageSize,String key) {
        if (mapList != null && currentPage != null && pageSize != null && mapList.size() > 0 && currentPage > 0 && pageSize > 0) {
            int index = (currentPage - 1) * pageSize + 1;
            for (Map<String, Object> hashMap : mapList) {
                hashMap.put(key, index);
                index++;
            }
        }
    }

    public static <T> List<T> getPageList(int currentPage,int pageSize,List<T> data){
        int toCount = Math.min(currentPage * pageSize, data.size());
        List<T> pageingResult = data.subList((currentPage - 1) * pageSize, toCount);
        return  pageingResult;
    }
}

package com.shsnc.util;

import java.util.Comparator;
import java.util.Map;

/**
 * list里面的map排序
 * */
public class ListMapSortComparator implements Comparator<Map<String, String>> {

    private String key;

    private String order;

    public ListMapSortComparator(String key,String order) {
        this.key = key;
        this.order = order;
    }

    /**
     * 如果o1小于o2,返回一个负数;如果o1大于o2，返回一个正数;如果他们相等，则返回0;
     * compareTo:比较两个值，如果前者大于后者，返回1，等于返回0，小于返回-1，
     * @param o1
     * @param o2
     * @return
     */
    @Override
    public int compare(Map<String, String> o1, Map<String, String> o2){
        if (order.equals("asc")) {
            return o1.get(key).compareTo(o2.get(key));
        }else {
            return o2.get(key).compareTo(o1.get(key));
        }
    }

}

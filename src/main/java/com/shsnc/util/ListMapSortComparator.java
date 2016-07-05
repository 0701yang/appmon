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

    @Override
    public int compare(Map<String, String> o1, Map<String, String> o2){
        if (order.equals("asc")) {
            return o1.get(key).compareTo(o2.get(key));
        }else {
            return o2.get(key).compareTo(o1.get(key));
        }
    }

}

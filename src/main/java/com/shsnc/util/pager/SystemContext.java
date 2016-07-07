package com.shsnc.util.pager;


/**
 * 用来传递列表对象的ThreadLocal数据
 * @author
 *
 */
public class SystemContext {
    /**
     * 分页大小
     */
    private static ThreadLocal<Integer> pageSize = new ThreadLocal<Integer>();
    /**
     * 分页的起始页
     */
    private static ThreadLocal<Integer> pageOffset = new ThreadLocal<Integer>();
    /**
     * 列表的排序字段
     */
    private static ThreadLocal<String> sort = new ThreadLocal<String>();
    /**
     * 列表的排序方式
     */
    private static ThreadLocal<String> order = new ThreadLocal<String>();

    private static ThreadLocal<String> realPath = new ThreadLocal<String>();



    public static String getRealPath() {
        return realPath.get();
    }
    public static void setRealPath(String _realPath) {
        SystemContext.realPath.set(_realPath);
    }
    public static Integer getPageSize() {
        Integer ps =  pageSize.get();
        if(ps == null){
            return Integer.MAX_VALUE;//它代表int所能表示的最大值 0x7FFFFFFF
        }
        return ps;
    }
    public static void setPageSize(Integer _pageSize) {
        pageSize.set(_pageSize);
    }
    public static Integer getPageOffset() {
        Integer of = pageOffset.get();
        if(of==null){
            return  0;
        }
        return of;
    }
    public static void setPageOffset(Integer _pageOffset) {
        pageOffset.set(_pageOffset);
    }
    public static String getSort() {
        return sort.get();
    }
    public static void setSort(String _sort) {
        SystemContext.sort.set(_sort);
    }
    public static String getOrder() {
        return order.get();
    }
    public static void setOrder(String _order) {
        SystemContext.order.set(_order);
    }

    public static void removePageSize() {
        pageSize.remove();
    }

    public static void removePageOffset() {
        pageOffset.remove();
    }

    public static void removeSort() {
        sort.remove();
    }

    public static void removeOrder() {
        order.remove();
    }

    public static void removeRealPath() {
        realPath.remove();
    }

}


package com.shsnc.util;

import com.github.miemiedev.mybatis.paginator.domain.Order;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.shsnc.util.pager.SystemContext;

/**
 * 再次封装PageBounds分页对象
 */
public class PageBoundsUtil {
    /**
     * 再次封装Mybatis PageBounds分页对象
     * @return pageBounds分页对象
     */
    public static PageBounds pageBoundsExtend(){
        Integer pagesize = SystemContext.getPageSize();
        Integer pageOffset = SystemContext.getPageOffset();
        if(null == pageOffset || pageOffset <0 ) pageOffset = 0 ; // 分页的起始页
        if(null == pagesize || pagesize <0) pagesize = 10;//分页大小
        PageBounds pageBounds = new PageBounds(pageOffset/pagesize+1,pagesize);
        return pageBounds;
    }

    /**
     * 再次封装PageBounds分页对象
     * @param order
     * @return pageBounds分页对象
     */
    public static  PageBounds pageBoundsOrderExtend(String order){
        Integer pagesize = SystemContext.getPageSize();
        Integer pageOffset = SystemContext.getPageOffset();
        if(null == pageOffset || pageOffset <0 ) pageOffset = 0 ; // 分页的起始页
        if(null == pagesize || pagesize <0) pagesize = 10;//分页大小
        PageBounds pageBounds = new PageBounds(pageOffset/pagesize+1,pagesize , Order.formString(order));
        return pageBounds;
    }



}

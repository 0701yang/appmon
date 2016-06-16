package com.shsnc.dataSource;


import org.aspectj.lang.JoinPoint;
import org.springframework.stereotype.Component;

@Component
public class DataSourceInterceptor {

    public void setdataSourceOne(JoinPoint jp) {
        DatabaseContextHolder.setCustomerType("dataSource_mon");
    }

    public void setdataSourceTwo(JoinPoint jp) {
        DatabaseContextHolder.setCustomerType("dataSource_sd1");
    }
}
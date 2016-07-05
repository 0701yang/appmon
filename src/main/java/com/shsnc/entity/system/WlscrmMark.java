package com.shsnc.entity.system;

import java.util.Date;

public class WlscrmMark {
    private Date time ;
    private String ip ;
    private String port ;
    private Short threadtotal;
    private Short threadidle ;
    private Short threadstandby ;
    private Short threadrun ;
    private Short mark ;

    public WlscrmMark() {
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public Short getThreadtotal() {
        return threadtotal;
    }

    public void setThreadtotal(Short threadtotal) {
        this.threadtotal = threadtotal;
    }

    public Short getThreadidle() {
        return threadidle;
    }

    public void setThreadidle(Short threadidle) {
        this.threadidle = threadidle;
    }

    public Short getThreadstandby() {
        return threadstandby;
    }

    public void setThreadstandby(Short threadstandby) {
        this.threadstandby = threadstandby;
    }

    public Short getThreadrun() {
        return threadrun;
    }

    public void setThreadrun(Short threadrun) {
        this.threadrun = threadrun;
    }

    public Short getMark() {
        return mark;
    }

    public void setMark(Short mark) {
        this.mark = mark;
    }
}

package com.shsnc.entity.system;

import java.io.Serializable;
import java.util.List;

public class WlscrmThread extends WlscrThreadKey implements Serializable {
    private Short threadtotal;

    private Short threadidle;

    private Short threadstandby;

    private Short threadrun;

    private WlscrmServers wlscrmServers;

    public WlscrmServers getWlscrmServers() {
        return wlscrmServers;
    }

    public void setWlscrmServers(WlscrmServers wlscrmServers) {
        this.wlscrmServers = wlscrmServers;
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
}
package com.shsnc.entity.system;

import java.io.Serializable;
import java.util.Date;

public class WlscrThreadKey implements Serializable {
    private Date time;

    private String ip;

    private String port;

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
        this.ip = ip == null ? null : ip.trim();
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port == null ? null : port.trim();
    }
}
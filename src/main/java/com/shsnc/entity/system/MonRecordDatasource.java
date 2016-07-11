package com.shsnc.entity.system;

import java.io.Serializable;
import java.util.Date;


public class MonRecordDatasource implements Serializable {

    private String serverName;
    private String ds;
    private String maxIdle;
    private String numActive;
    private short idle;
    private Date runTime;
    private String maxActive;


    public MonRecordDatasource() {
    }

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    public String getDs() {
        return ds;
    }

    public void setDs(String ds) {
        this.ds = ds;
    }

    public String getMaxIdle() {
        return maxIdle;
    }

    public void setMaxIdle(String maxIdle) {
        this.maxIdle = maxIdle;
    }

    public String getNumActive() {
        return numActive;
    }

    public void setNumActive(String numActive) {
        this.numActive = numActive;
    }

    public short getIdle() {
        return idle;
    }

    public void setIdle(short idle) {
        this.idle = idle;
    }

    public Date getRunTime() {
        return runTime;
    }

    public void setRunTime(Date runTime) {
        this.runTime = runTime;
    }

    public String getMaxActive() {
        return maxActive;
    }

    public void setMaxActive(String maxActive) {
        this.maxActive = maxActive;
    }
}

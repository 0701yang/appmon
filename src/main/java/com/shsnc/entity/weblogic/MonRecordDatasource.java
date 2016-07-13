package com.shsnc.entity.weblogic;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


public class MonRecordDatasource implements Serializable {

    private String server_name;

    private String ds;

    private String max_idle;

    private String num_active;

    private Short idle;

    private Date run_time;

    private String max_active;

    private Short usage;

    private Short state;

    public String getServer_name() {
        return server_name;
    }

    public void setServer_name(String server_name) {
        this.server_name = server_name;
    }

    public String getDs() {
        return ds;
    }

    public void setDs(String ds) {
        this.ds = ds;
    }

    public String getMax_idle() {
        return max_idle;
    }

    public void setMax_idle(String max_idle) {
        this.max_idle = max_idle;
    }

    public String getNum_active() {
        return num_active;
    }

    public void setNum_active(String num_active) {
        this.num_active = num_active;
    }

    public Short getIdle() {
        return idle;
    }

    public void setIdle(Short idle) {
        this.idle = idle;
    }

    public Date getRun_time() {
        return run_time;
    }

    public void setRun_time(Date run_time) {
        this.run_time = run_time;
    }

    public String getMax_active() {
        return max_active;
    }

    public void setMax_active(String max_active) {
        this.max_active = max_active;
    }

    public Short getUsage() {
        return usage;
    }

    public void setUsage(Short usage) {
        this.usage = usage;
    }

    public Short getState() {
        return state;
    }

    public void setState(Short state) {
        this.state = state;
    }
}
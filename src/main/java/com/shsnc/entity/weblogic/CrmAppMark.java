package com.shsnc.entity.weblogic;

import java.io.Serializable;

/**
 *  CRMAPPMARK
 */
public class CrmAppMark implements Serializable {
    private String servername ;
    private short crmapp_mark;

    public CrmAppMark() {
    }

    public String getServername() {
        return servername;
    }

    public void setServername(String servername) {
        this.servername = servername;
    }

    public short getCrmapp_mark() {
        return crmapp_mark;
    }

    public void setCrmapp_mark(short crmapp_mark) {
        this.crmapp_mark = crmapp_mark;
    }
}

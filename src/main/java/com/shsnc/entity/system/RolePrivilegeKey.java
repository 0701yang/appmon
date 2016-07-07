package com.shsnc.entity.system;

import java.io.Serializable;

public class RolePrivilegeKey implements Serializable {
    private String privilegeid;

    private String roleid;

    public String getPrivilegeid() {
        return privilegeid;
    }

    public void setPrivilegeid(String privilegeid) {
        this.privilegeid = privilegeid == null ? null : privilegeid.trim();
    }

    public String getRoleid() {
        return roleid;
    }

    public void setRoleid(String roleid) {
        this.roleid = roleid == null ? null : roleid.trim();
    }
}
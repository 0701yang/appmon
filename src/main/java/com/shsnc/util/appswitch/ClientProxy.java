package com.shsnc.util.appswitch;

import com.shsnc.entity.serverswitch.MonServer;
import com.shsnc.service.serverswitch.MonServerService;
import org.jboss.remoting.transporter.TransporterClient;

import javax.annotation.Resource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.HashMap;

public final class ClientProxy {
    private static final HashMap LOCATOR = new HashMap();

    public static Object getObject(long serverId, Class interfaceClass)
            throws Exception {
        String sql = String.format("select server_id, name, locator, state,remarks, server_type from mon_server where server_id=%s", serverId);
        Connection con = null;
        ResultSet rs = null;
        MonServer objMonMbeanServer = null;
        DataBase db = null;
        try {
            db = new DataBase();
            rs = db.query(sql);
            objMonMbeanServer = new MonServer();
            if (rs != null) {
                while (rs.next()) {
                    objMonMbeanServer.setServerId(rs.getInt("SERVER_ID"));
                    objMonMbeanServer.setName(rs.getString("NAME"));
                    objMonMbeanServer.setLocator(rs.getString("LOCATOR"));
                    objMonMbeanServer.setState(rs.getString("STATE"));
                    objMonMbeanServer.setRemarks(rs.getString("REMARKS"));
                    objMonMbeanServer.setServerType(rs.getString("SERVER_TYPE"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.coles();
        }

        Object rtn = null;
        if (!LOCATOR.containsKey(interfaceClass)) {
            synchronized (LOCATOR) {
                if (!LOCATOR.containsKey(interfaceClass)) {

                    System.out.println("static 中getObject：" + objMonMbeanServer.getName() + "serverId:" + serverId);

                    LOCATOR.put(new Long(serverId), objMonMbeanServer.getLocator());
                }
            }
        }
        String locator = (String) LOCATOR.get(new Long(serverId));
        rtn = TransporterClient.createTransporterClient(locator, interfaceClass);
        return rtn;
    }

    public static void destroyObject(Object obj) {
        TransporterClient.destroyTransporterClient(obj);
    }
}

package com.shsnc.service.serverswitch.switchaction;

import com.ai.appframe2.complex.mbean.standard.cc.ClientControlMBean;
import com.shsnc.entity.serverswitch.MonServer;
import com.shsnc.service.serverswitch.switchaction.BaseAction;
import com.shsnc.util.appswitch.ClientProxy;
import com.shsnc.service.serverswitch.MonServerService;
import com.shsnc.util.appswitch.DataBase;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class AppSwitchAction
  extends BaseAction
{

  public void switchApp(HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    String tmp = request.getParameter("condition");
    String[] tmp1 = StringUtils.split(tmp, "^");
    List error = new ArrayList();
    List success = new ArrayList();
    for (int i = 0; i < tmp1.length; i++)
    {
      String[] tmp2 = StringUtils.split(tmp1[i], ":");
      long id = Long.parseLong(tmp2[0]);
      String destAppCluster = tmp2[1].trim();
      String sql=String.format("select server_id, name, locator, state,remarks, server_type from mon_server where server_id=%s",(int)id);
      ResultSet rs=null;
      MonServer objMonMbeanServer=null;
      DataBase db=null;
      try {
       db=new DataBase();
        rs=db.query(sql);
        objMonMbeanServer=new MonServer();
        if(rs!=null){
          while (rs.next()){
            objMonMbeanServer.setServerId(rs.getInt("SERVER_ID"));
            objMonMbeanServer.setName(rs.getString("NAME"));
            objMonMbeanServer.setLocator(rs.getString("LOCATOR"));
            objMonMbeanServer.setState(rs.getString("STATE"));
            objMonMbeanServer.setRemarks(rs.getString("REMARKS"));
            objMonMbeanServer.setServerType(rs.getString("SERVER_TYPE"));
          }
        }
      }catch(Exception e){
        e.printStackTrace();
      }finally{
        db.coles();
      }

      ClientControlMBean objClientControlMBean = null;
      try
      {
        objClientControlMBean = (ClientControlMBean) ClientProxy.getObject(id, ClientControlMBean.class);
        objClientControlMBean.connect(destAppCluster);
        success.add(objMonMbeanServer.getName());
      }
      catch (Exception ex)
      {
        error.add(objMonMbeanServer.getName());
      }
      finally
      {
        if (objClientControlMBean != null) {
          ClientProxy.destroyObject(objClientControlMBean);
        }
      }
    }
    StringBuffer sb = new StringBuffer();
    for (Iterator iter = success.iterator(); iter.hasNext();)
    {
      String item = (String)iter.next();
      sb.append(item + " 成功\n");
    }
    for (Iterator iter = error.iterator(); iter.hasNext();)
    {
      String item = (String)iter.next();
      sb.append(item + " 失败\n");
    }
    showInfo(response, sb.toString());
  }
}

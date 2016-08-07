package com.shsnc.controller.serverswitch;

import com.shsnc.controller.base.BaseController;
import com.shsnc.entity.serverswitch.MonNode;
import com.shsnc.entity.serverswitch.MonServer;
import com.shsnc.entity.serverswitch.MonTree;
import com.shsnc.service.serverswitch.MonNodeService;
import com.shsnc.service.serverswitch.MonServerService;
import com.shsnc.service.serverswitch.MonTreeService;
import com.shsnc.util.appswitch.ParallelUtil;
import org.apache.commons.fileupload.servlet.ServletRequestContext;
import org.apache.shiro.web.servlet.ShiroHttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletInputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.util.*;

/**
 * 显示集群切换的内容
 */
@Controller

public class SwitchController extends BaseController {

    @Resource(name="monTreeService")
    private MonTreeService montreeService;

    @Resource(name="monNodeService")
    private MonNodeService monNodeService;

    @Resource(name="monServerService")
    private MonServerService monServerService;

    @Autowired
    HttpServletRequest request;

    @RequestMapping(value = "/switch")
    public ModelAndView allSwitchInfo(){
        List<MonTree> trees=montreeService.findRootNodes();
        ModelAndView mv=this.getModelAndView();
        for(int i=0;i<trees.size();i++){
          List<MonNode> nodes=monNodeService.findChildrenNodes((int)trees.get(i).getTreeId());
            mv.addObject("childrenNode"+i,nodes);

        }
        mv.addObject("rootTree",trees);
        mv.setViewName("system/serverswitch/switch");
        return mv;
    }

    /**
     * 一键式切换_WEB
     * @return
     */
    @RequestMapping(value = "/mon/control/AppSwitch_web")
    public ModelAndView findWebServer(){
        ModelAndView mv =this.getModelAndView();
        request.setAttribute("serverType","server");
        mv.addObject("webServer",monServerService.findWebServer());
        mv.setViewName("system/serverswitch/webswitch");
        return mv;
    }

    /**
     * 一键式切换_INTER
     * @return
     */
    @RequestMapping(value = "/mon/control/AppSwitch_inter")
    public ModelAndView findInterServer(){
        ModelAndView mv =this.getModelAndView();
        request.setAttribute("serverType","server");
        mv.addObject("interServer",monServerService.findInterServer());
        mv.setViewName("system/serverswitch/interswitch");
        return mv;
    }

    @RequestMapping(value = "/mon/control/AppSwitch")
    public ModelAndView findAppSwitchServer(@RequestParam("server_type") String serverType){
        if(serverType!=null && serverType.length()!=0){
                request.setAttribute("serverType",serverType);
                request.getSession().setAttribute("serverFrom",serverType);
        }
        String server=request.getParameter("server");
        if(server!=null && server.length()!=0){
            serverType=(String)request.getSession().getAttribute("serverFrom");
        }
        ModelAndView mv =this.getModelAndView();
        ParallelUtil.ConnectApp[] objConnectApp = null;
        TreeMap map = null;
        TreeMap canSelectedMap = null;
        HashMap mapping = null;
        HashMap tmp =null;
        MonServer[] objMonServer=null;
        try {
            List<MonServer> servers=monServerService.findAppServer(serverType);
            int num=0;
            for (int i=0;i<servers.size();i++){
                num++;
            }
           objMonServer=new MonServer[num];
            for (int i=0;i<servers.size();i++){

                objMonServer[i]= servers.get(i);
            }

            objConnectApp = ParallelUtil.getCurrentConnectAppCluster(objMonServer);
            map = new TreeMap();
            mapping = new HashMap();
            canSelectedMap = new TreeMap();
            for (int i = 0; i < objConnectApp.length; i++) {
                ArrayList list = (ArrayList)map.get(objConnectApp[i].oldAppCluster);
                if(list==null){
                    list = new ArrayList();
                    map.put(objConnectApp[i].oldAppCluster,list);
                }
                list.add(objConnectApp[i].serverName);
                mapping.put(objConnectApp[i].serverName,String.valueOf(objConnectApp[i].serverId));

                //可以选择的列表
                tmp = (HashMap)canSelectedMap.get(objConnectApp[i].oldAppCluster);
                if(tmp==null){
                    tmp = new HashMap();
                    canSelectedMap.put(objConnectApp[i].oldAppCluster,tmp);
                }
                for (int j = 0; j < objConnectApp[i].canSelectAppCluster.length; j++) {
                    tmp.put(objConnectApp[i].canSelectAppCluster[j],null);
                }
            }
        }
        catch (Throwable ex) {
            ex.printStackTrace();
        }
        request.setAttribute("objConnectApp",objConnectApp);
        request.setAttribute("map",map);
        request.setAttribute("canSelectedMap",canSelectedMap);
        request.setAttribute("mapping",mapping);
        request.setAttribute("objMonServer",objMonServer);
        mv.setViewName("system/serverswitch/appswitch");
        return mv;
    }
}

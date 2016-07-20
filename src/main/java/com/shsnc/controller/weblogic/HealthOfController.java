package com.shsnc.controller.weblogic;


import com.shsnc.controller.base.BaseController;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

/**
 * 健康度页面
 */
@Controller
@RequestMapping(value = "/healthof")
public class HealthOfController extends BaseController {

    /**
     * 显示健康度页面
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/{msg}")
    public ModelAndView view1(@PathVariable("msg")String msg) throws Exception {
        ModelAndView mv = this.getModelAndView();
        //shiro管理的session
        Subject currentUser = SecurityUtils.getSubject();
        Session session = currentUser.getSession();
        List<Map<String , String>> o =  (List<Map<String , String>>)session.getAttribute("health");//把计算好的健康度放到session中

        for (Map<String , String > m : o){
               if(m.get("url").equals(msg)){
                   mv.addObject("count" , m);
               }
        }

        mv.setViewName("system/healthof/list");
        return mv;
    }







}

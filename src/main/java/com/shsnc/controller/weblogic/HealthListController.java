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
 * 健康度页面:下拉列表
 */
@Controller
@RequestMapping(value = "/healthlist")
public class HealthListController extends BaseController {

    /**
     * 健康度下拉框:
     * 1、渠道支撑应用层:/healthlist/list/channel
     * 2、业务能力集成层:/healthlist/list/operational
     * 3、核心能力中心层:/healthlist/list/core
     * 4、基础能力层:/healthlist/list/basics
     * 5、接口子域:/healthlist/list/interface
     *
     * @param health
     * @return
     */
    @RequestMapping(value = "/list/{health}")
    public ModelAndView list(@PathVariable("health") String health) {
        ModelAndView mv = this.getModelAndView();


        mv.setViewName("system/healthof/"+health);
        return mv;
    }


}

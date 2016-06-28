package com.shsnc.controller;

import com.shsnc.entity.system.Bean;
import com.shsnc.service.ZcjWlscrmService;
import com.shsnc.util.pager.Pager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

/**
 * 线程监控
 */
@Controller
@RequestMapping(value = "/weblogic")
public class WeblogicController extends BaseController{
    @Resource(name = "zcjWlscrmService")
    private ZcjWlscrmService zcjWlscrmService;

    /**
     * CRM-APP 列表信息
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/findcrm")
    public ModelAndView findCrm() throws Exception {
        ModelAndView mv = this.getModelAndView();
        Pager<Bean> bean = zcjWlscrmService.findCrm();
        mv.addObject("bean", bean);
        mv.addObject("list","findcrm");
        mv.setViewName("system/weblogic/list");
        return mv;
    }

    /**
     * OSB 列表信息
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/findosb")
    public ModelAndView findOsb() throws Exception {
        ModelAndView mv = this.getModelAndView();
        Pager<Bean> bean = zcjWlscrmService.findOsb();
        mv.addObject("bean", bean);
        mv.addObject("list","findosb");
        mv.setViewName("system/weblogic/list");
        return mv;
    }


}

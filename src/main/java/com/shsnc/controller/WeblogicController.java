package com.shsnc.controller;

import com.shsnc.entity.system.Bean;
import com.shsnc.service.ZcjWlscrmService;
import com.shsnc.util.Const;
import com.shsnc.util.pager.Pager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 线程监控,健康度
 */
@Controller
@RequestMapping(value = "/weblogic")
public class WeblogicController extends BaseController{
    @Resource(name = "zcjWlscrmService")
    private ZcjWlscrmService zcjWlscrmService;

    /**
     * 营业厅渠道CRM
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/msg1")
    public ModelAndView msg1() throws Exception {
        ModelAndView mv = this.getModelAndView();
        Pager<Bean> bean = zcjWlscrmService.findCrm(Const.MSG1);
        mv.addObject("bean", bean);
        mv.addObject("list","findcrm");
        mv.setViewName("system/weblogic/list");
        return mv;
    }

    /**
     * 营业厅渠道OSB
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/osb1")
    public ModelAndView osb1() throws Exception {
        ModelAndView mv = this.getModelAndView();
        Pager<Bean> bean = zcjWlscrmService.findOsb(Const.OSB1);
        mv.addObject("bean", bean);
        mv.addObject("list","findosb");
        mv.setViewName("system/weblogic/list");
        return mv;
    }


}

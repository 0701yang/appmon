package com.shsnc.controller;


import com.shsnc.entity.system.Bean;
import com.shsnc.service.ZcjWlscrmService;
import com.shsnc.util.pager.Pager;
import org.springframework.expression.ExpressionException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

/**
 * 健康度页面
 */
@Controller
@RequestMapping(value = "/healthof")
public class HealthOfController extends BaseController {
    @Resource(name = "zcjWlscrmService")
    private ZcjWlscrmService zcjWlscrmService;

    /**
     * 显示健康度页面
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/view")
    public ModelAndView view() throws Exception {
        ModelAndView mv = this.getModelAndView();

        mv.setViewName("system/healthof/list");
        return mv;
    }



}

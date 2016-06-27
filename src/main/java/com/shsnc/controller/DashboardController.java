package com.shsnc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by zhangliling on 16/6/27.
 */
@Controller
@RequestMapping(value = "/dashboard")
public class DashboardController extends BaseController{
    /**
     * 主页
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/index")
    public ModelAndView index() throws Exception {
        ModelAndView mv = this.getModelAndView();
        mv.setViewName("main/dashboard");
        return mv;
    }




}

package com.shsnc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 主页
 */
@Controller
@RequestMapping(value = "/dashboard")
public class DashboardController extends BaseController{

    /**
     * 主页(显示健康度)
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/index")
    public ModelAndView index() throws Exception {
        ModelAndView mv = this.getModelAndView();
        //Todo 健康度的计算
        mv.setViewName("main/dashboard");
        return mv;
    }




}

package com.shsnc.controller;

import com.shsnc.util.Const;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

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

        Map<String,String> map = Const.map();
        mv.addObject("map" , map);
        mv.setViewName("main/dashboard");
        return mv;
    }




}

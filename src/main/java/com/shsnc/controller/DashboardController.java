package com.shsnc.controller;

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

        Map<String,String> map = new TreeMap<>();
        map.put("msg1" , "营业厅渠道");
        map.put("msg2" , "代理商渠道");
        map.put("msg3" , "移动终端渠道");
        map.put("msg4" , "短信渠道");
        map.put("msg5" , "自助式语言渠道");
        map.put("msg6" , "互联网渠道");
        map.put("msg7" , "协助式渠道");
        map.put("msg8" , "集团客户渠道");
        mv.addObject("map" , map);
        mv.setViewName("main/dashboard");
        return mv;
    }




}

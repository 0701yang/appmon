package com.shsnc.controller;


import com.shsnc.service.ZcjWlscrmService;
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

    /**
     * 显示健康度页面(营业厅渠道)
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/view1")
    public ModelAndView view1() throws Exception {
        ModelAndView mv = this.getModelAndView();

        mv.addObject("msg" , "营业厅渠道");
        mv.setViewName("system/healthof/list");
        return mv;
    }

    /**
     * 显示健康度页面(代理商渠道)
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/view2")
    public ModelAndView view2() throws Exception {
        ModelAndView mv = this.getModelAndView();

        mv.addObject("msg" , "代理商渠道");
        mv.setViewName("system/healthof/list");
        return mv;
    }

    /**
     * 显示健康度页面(移动终端渠道)
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/view3")
    public ModelAndView view3() throws Exception {
        ModelAndView mv = this.getModelAndView();

        mv.addObject("msg" , "移动终端渠道");
        mv.setViewName("system/healthof/list");
        return mv;
    }
    /**
     * 显示健康度页面(短信渠道)
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/view4")
    public ModelAndView view4() throws Exception {
        ModelAndView mv = this.getModelAndView();

        mv.addObject("msg" , "短信渠道");
        mv.setViewName("system/healthof/list");
        return mv;
    }

    /**
     * 显示健康度页面(自助式语言渠道)
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/view5")
    public ModelAndView view5() throws Exception {
        ModelAndView mv = this.getModelAndView();

        mv.addObject("msg" , "自助式语言渠道");
        mv.setViewName("system/healthof/list");
        return mv;
    }

    /**
     * 显示健康度页面(互联网渠道)
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/view6")
    public ModelAndView view6() throws Exception {
        ModelAndView mv = this.getModelAndView();

        mv.addObject("msg" , "互联网渠道");
        mv.setViewName("system/healthof/list");
        return mv;
    }

    /**
     * 显示健康度页面(协助式渠道)
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/view7")
    public ModelAndView view7() throws Exception {
        ModelAndView mv = this.getModelAndView();

        mv.addObject("msg" , "协助式渠道");
        mv.setViewName("system/healthof/list");
        return mv;
    }

    /**
     * 显示健康度页面(集团客户渠道)
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/view8")
    public ModelAndView view8() throws Exception {
        ModelAndView mv = this.getModelAndView();

        mv.addObject("msg" , "集团客户渠道");
        mv.setViewName("system/healthof/list");
        return mv;
    }


}

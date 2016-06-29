package com.shsnc.controller;


import com.shsnc.util.Const;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

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
    @RequestMapping(value = "/{msg}")
    public ModelAndView view1(@PathVariable("msg")String msg) throws Exception {
        ModelAndView mv = this.getModelAndView();

        mv.addObject("msg" , Const.map().get(msg));
        mv.addObject("crm" , msg);
        mv.addObject("osb" , Const.maposb().get(msg));
        mv.setViewName("system/healthof/list");
        return mv;
    }
}

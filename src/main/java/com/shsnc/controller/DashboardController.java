package com.shsnc.controller;

import com.shsnc.service.ZcjWlscrmMarkService;
import com.shsnc.util.Const;
import com.shsnc.util.ListMapSortComparator;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.*;

/**
 * 主页
 */
@Controller
@RequestMapping(value = "/dashboard")
public class DashboardController extends BaseController {
    @Resource(name = "zcjWlscrmMarkService")
    private ZcjWlscrmMarkService zcjWlscrmMarkService;

    /**
     * 主页(显示健康度)
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/index")
    public ModelAndView index() throws Exception {
        ModelAndView mv = this.getModelAndView();
        // 健康度的计算
        Map<String, String[]> maps = Const.maprcrm();//crm
        Map<String, String> map = Const.map();//各个渠道的名称
        Map<String, String> osbmap = Const.maposb();//osb
        List<Map<String, String>> list = new ArrayList<>();
        for (Map.Entry<String, String[]> entry : maps.entrySet()) {
            String[] s = entry.getValue();
            Integer markcrmtwo = zcjWlscrmMarkService.markcrmtwo(s) == null ? 0 : zcjWlscrmMarkService.markcrmtwo(s);//crm-app的分数:特殊情况
            Integer markcrmone = zcjWlscrmMarkService.markcrmone(s) == null ? 0 : zcjWlscrmMarkService.markcrmone(s);//crm-app的分数:情况一
            Integer markosb = zcjWlscrmMarkService.markosb(osbmap.get(entry.getKey())) == null ? 0 : zcjWlscrmMarkService.markosb(osbmap.get(entry.getKey()));//osb的分数

            Map<String, String> msg = new HashMap<>();
            msg.put("url", entry.getKey());  //url地址
            msg.put("name", map.get(entry.getKey())); //渠道的名字
            msg.put("osb", markosb.toString());//osb的分数
            if (markcrmtwo > 0) {
                msg.put("crmtotal", String.valueOf((int)(60 * 0.6 + markosb * 0.4)));//渠道的总分数
                msg.put("crm", "60");
            } else {
                msg.put("crmtotal", String.valueOf((int)(markcrmone * 0.6 + markosb * 0.4)));//渠道的总分数
                msg.put("crm", markcrmone.toString());
            }
            list.add(msg);
        }
        Collections.sort(list, new ListMapSortComparator("crmtotal", "asc"));//根据渠道的总分数进行排序
        //shiro管理的session
        Subject currentUser = SecurityUtils.getSubject();
        Session session = currentUser.getSession();
        session.setAttribute("health", list);//把计算好的健康度放到session中

        mv.addObject("list", list);
        mv.setViewName("main/dashboard");
        return mv;
    }


}

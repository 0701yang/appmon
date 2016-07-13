package com.shsnc.controller.weblogic;

import com.shsnc.controller.base.BaseController;
import com.shsnc.service.weblogic.CrmAppMarkService;
import com.shsnc.service.weblogic.ZcjWlscrmMarkService;
import com.shsnc.util.Const;
import com.shsnc.util.ListMapSortComparator;
import javafx.beans.binding.When;
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
    @Resource(name = "crmAppMarkService")
    private CrmAppMarkService crmAppMarkService ;

    /**
     * 主页(显示健康度)
     * 分数计算方式:
     *          1、>60 , 取所有分数的平均值
     *          2、<60 , 取 60-(60/总的个数)*小于60分的个数
     *          3、分数颜色 : 90~100(绿色),80~90(黄色),60~80(橙色),60以下(红色)
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
            int a = crmAppMarkService.findAll();//查询处所有个数
            int b = crmAppMarkService.findAllSix();//查询处所有的小于60的分数的个数
            int markcrmone;
            if(b==0){//如果小于60的不存在
                markcrmone = crmAppMarkService.findSixByCrm(s) == null ? 0 : crmAppMarkService.findSixByCrm(s);//根据crm进行查询个数
            }else {
                markcrmone = 60-(60/a)/b;
            }
            int markosb = zcjWlscrmMarkService.markosb(osbmap.get(entry.getKey()))==null ? 0 : zcjWlscrmMarkService.markosb(osbmap.get(entry.getKey()));//osb的分数
            int total = markosb >= markcrmone ? markcrmone : markosb;//总分
            Map<String, String> msg = new HashMap<>();
            msg.put("url", entry.getKey());  //url地址
            msg.put("name", map.get(entry.getKey())); //渠道的名字
            msg.put("osb", String.valueOf(markosb));//osb的分数
            msg.put("crm", String.valueOf(markcrmone));//crm的分数
            msg.put("total", String.valueOf(total));//总分
            //总的颜色
            if(total >=90){
                msg.put("color" , "green");
            }else if(total >= 80 && total<90){
                msg.put("color" , "blue");
            }else if(total >=60 && total < 80){
                msg.put("color" , "yellow");
            }else if(total < 60){
                msg.put("color" , "red");
            }
            //crm颜色
            if(markcrmone >=90){
                msg.put("crmcolor" , "green");
            }else if(markcrmone >= 80 && total<90){
                msg.put("crmcolor" , "blue");
            }else if(markcrmone >=60 && total < 80){
                msg.put("crmcolor" , "yellow");
            }else if(markcrmone < 60){
                msg.put("crmcolor" , "red");
            }
            //osb颜色
            if(markosb >=90){
                msg.put("osbcolor" , "green");
            }else if(markosb >= 80 && total<90){
                msg.put("osbcolor" , "blue");
            }else if(markosb >=60 && total < 80){
                msg.put("osbcolor" , "yellow");
            }else if(markosb < 60){
                msg.put("osbcolor" , "red");
            }
            list.add(msg);
        }
        Collections.sort(list, new ListMapSortComparator("total", "asc"));//根据渠道的总分数进行排序
        //shiro管理的session
        Subject currentUser = SecurityUtils.getSubject();
        Session session = currentUser.getSession();
        session.setAttribute("health", list);//把计算好的健康度放到session中

        mv.addObject("list", list);
        mv.setViewName("main/dashboard");
        return mv;
    }


}

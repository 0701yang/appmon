package com.shsnc.controller;

import com.shsnc.entity.system.Bean;
import com.shsnc.entity.system.WlscrmThread;
import com.shsnc.service.ZcjWlscrmService;
import com.shsnc.util.Const;
import com.shsnc.util.pager.Pager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

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
    @RequestMapping(value = "/crm/{crm}")
    public ModelAndView crm(@PathVariable("crm") String crm) throws Exception {
        ModelAndView mv = this.getModelAndView();
        String[] a =Const.maprcrm().get(crm);
        Pager<Bean> bean = zcjWlscrmService.findCrm(a);
        mv.addObject("bean", bean);
        mv.addObject("list",crm);
        mv.setViewName("system/weblogic/list");
        return mv;
    }

    /**
     * 营业厅渠道OSB
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/osb/{osb}")
    public ModelAndView osb(@PathVariable("osb") String osb) throws Exception {
        ModelAndView mv = this.getModelAndView();

        Pager<Bean> bean = zcjWlscrmService.findOsb(Const.maposb().get(osb));
        mv.addObject("bean", bean);
        mv.addObject("list",osb);
        mv.setViewName("system/weblogic/list");
        return mv;
    }

    /**
     * 历史信息查询
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/history")
    public ModelAndView history(@RequestParam("ip")String ip , @RequestParam("port")String port ) throws Exception{
        ModelAndView mv = this.getModelAndView();
        Pager<WlscrmThread> historyList = zcjWlscrmService.findHistory(ip, port);
        mv.addObject("historyList" ,historyList);
        mv.addObject("ip" , ip);
        mv.addObject("port" , port);
        mv.setViewName("system/weblogic/history");
        return mv;
    }

    /**
     * 曲线
     * @param ip
     * @param port
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/charts")
    @ResponseBody
    public List<Map> charts(@RequestParam("ip")String ip , @RequestParam("port")String port) throws Exception{

        Pager<WlscrmThread> wlscrmThreadPager =zcjWlscrmService.findHistory(ip , port);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        List<Map> list = new ArrayList<>();
        for (WlscrmThread bean : wlscrmThreadPager.getDatas()) {
            Date date = bean.getTime();
            String s = sdf.format(date);
            Date date2 = sdf.parse(s);
            DateFormat dFormat = DateFormat.getTimeInstance();
            int threadrun = bean.getThreadrun().intValue();
            Map<Object, Object> map = new HashMap<>();
            map.put("time", dFormat.format(date2));
            map.put("threadrun", threadrun);
            list.add(map);
        }
        return list;
    }


}

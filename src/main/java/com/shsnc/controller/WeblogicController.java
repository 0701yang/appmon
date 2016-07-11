package com.shsnc.controller;

import com.shsnc.entity.system.Bean;
import com.shsnc.entity.system.MonRecordDatasource;
import com.shsnc.entity.system.WlscrmThread;
import com.shsnc.service.MrdService;
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
public class WeblogicController extends BaseController {
    @Resource(name = "zcjWlscrmService")
    private ZcjWlscrmService zcjWlscrmService;

    @Resource(name = "mrdService")
    private MrdService mrdService;
    /**
     * CRM
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/crm/{crm}")
    public ModelAndView crm(@PathVariable("crm") String crm) throws Exception {
        ModelAndView mv = this.getModelAndView();
        String[] a = Const.maprcrm().get(crm);
        Pager<Bean> bean = zcjWlscrmService.findCrm(a);

        mv.addObject("bean", bean);
        mv.addObject("list", crm);
        mv.setViewName("system/weblogic/list");
        return mv;
    }

    /**
     * OSB
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/osb/{osb}")
    public ModelAndView osb(@PathVariable("osb") String osb) throws Exception {
        ModelAndView mv = this.getModelAndView();

        Pager<Bean> bean = zcjWlscrmService.findOsb(Const.maposb().get(osb));
        mv.addObject("bean", bean);
        mv.addObject("list", osb);
        mv.setViewName("system/weblogic/list");
        return mv;
    }

    /**
     * 历史信息查询
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/history")
    public ModelAndView history(@RequestParam("ip") String ip, @RequestParam("port") String port) throws Exception {
        ModelAndView mv = this.getModelAndView();
        List<WlscrmThread> historyList = zcjWlscrmService.findHistory(ip, port);
        mv.addObject("historyList", historyList);
        mv.addObject("ip", ip);
        mv.addObject("port", port);
        mv.setViewName("system/weblogic/history");
        return mv;
    }

    /**
     * 曲线
     *
     * @param ip
     * @param port
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/charts")
    @ResponseBody
    public List<Map> charts(@RequestParam("ip") String ip, @RequestParam("port") String port) throws Exception {

        List<WlscrmThread> wlscrmThreadPager = zcjWlscrmService.findHistory(ip, port);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        List<Map> list = new ArrayList<>();
        for (WlscrmThread bean : wlscrmThreadPager) {
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

    /**
     * crm图表
     * @param name
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/crmCharts")
    @ResponseBody
    public List<Map<Object, Object>> crmCharts(@RequestParam("name")String name ) throws Exception{
        List<MonRecordDatasource> mrdList = mrdService.findByName(name);
        List<String> snlist = new ArrayList<String>();
        List<Map<Object, Object>> list = new ArrayList<Map<Object, Object>>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for (MonRecordDatasource bean : mrdList){
            snlist.add(bean.getDs());//名字
        }
        HashSet<String> hs = new HashSet<String>(snlist); //去掉重复的数据保存在hashset中

        for (String str : hs) {
            Map<Object, Object> map = new HashMap<Object, Object>();
            List<Integer> aa= new ArrayList<>();
            List<String> bb = new ArrayList<>();
            map.put("cname" , str);
            for (MonRecordDatasource mrd : mrdList){
                if(str.equals(mrd.getDs())){
                    int b = Integer.parseInt(mrd.getNumActive());
                    aa.add(b);
                    bb.add(DateFormat.getTimeInstance().format(sdf.parse(mrd.getRunTime().toString())));
                }
            }
            map.put("cnumber" , aa);
            map.put("ctime" , bb) ;
            list.add(map);
        }
    return list;

}

    /**
     * WebLogic性能监控 页面
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/list")
    public ModelAndView list() throws  Exception{
        ModelAndView mv = this.getModelAndView();
        List<String> systems = zcjWlscrmService.findBySystemAll(); // 查询所有的信息

        mv.addObject("systems" , systems);
        mv.setViewName("system/weblogic/button");
        return mv;
    }

    /**
     * 根据按钮查询出所有的信息
     *
     * @param system
     * @param value
     * @param name
     * @param time
     * @param start
     * @param end
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/button")
    public ModelAndView buttonFindHistory(
            @RequestParam("system") String system,
            @RequestParam("value") String value,
            @RequestParam("name") String name,
            @RequestParam("bug") String bug,
            @RequestParam("time") String time,
            @RequestParam("start") String start,
            @RequestParam("end") String end
    ) throws Exception {
        ModelAndView mv = this.getModelAndView();
        Map<String , String> map = new HashMap<>();
        map.put("system",system);
        map.put("value",value);
        map.put("name",name);
        map.put("bug",bug);
        map.put("time",time);
        map.put("start",start);
        map.put("end",end);
        Pager<Bean> bean = zcjWlscrmService.findByButton(map);

        mv.addObject("bean", bean);
        mv.setViewName("system/weblogic/buttonlist");
        return mv;
    }

    /**
     * 第二个下拉框
     * @param systemValue
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/findValue")
    @ResponseBody
    public List<String> findValue(@RequestParam("systemValue") String systemValue) throws  Exception{
        return zcjWlscrmService.findByValueAll(systemValue);
    }

    /**
     * 第3个下拉框
     * @param systemName
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/findName")
    @ResponseBody
    public List<String> findName(@RequestParam("systemName") String systemName) throws  Exception{
        return zcjWlscrmService.findByNameAll(systemName);
    }

}

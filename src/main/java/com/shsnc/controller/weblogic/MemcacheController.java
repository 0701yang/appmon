package com.shsnc.controller.weblogic;

import com.shsnc.controller.base.BaseController;
import com.shsnc.util.mecache.GetKeyTool;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

/**
 * memcache数据可视化工具
 */
@Controller
@RequestMapping(value = "/memcache")
public class MemcacheController extends BaseController {

    private GetKeyTool getKeyTool = new GetKeyTool();

    /**
     * 列表展示
     *
     * @return
     */
    @RequestMapping(value = "/list")
    public ModelAndView memcachelist() throws Exception{
        ModelAndView mv = this.getModelAndView();
        mv.setViewName("system/memcache/list");
        return mv;
    }

    /**
     * 根据server名字查询出所有的host
     *
     * @param server
     * @return
     */
    @RequestMapping("/select")
    @ResponseBody
    public List<String> server(@RequestParam(value = "server") String server) throws Exception{
        String[] servers = getKeyTool.getProperty("config", server).split(",");//获取配置文件value值
        List<String> stringList = new ArrayList<>();
        Collections.addAll(stringList, servers);//数组转换为list
        return stringList;
    }

    /**
     * 查询
     *
     * @param hostip  IP地址
     * @param key     key的值
     * @return
     */
    @RequestMapping("/showlist")
    public ModelAndView showlist(
            @RequestParam(value = "server") String server,
            @RequestParam(value = "hostip") String hostip,
            @RequestParam(value = "key") String key) throws Exception {
        ModelAndView mv = this.getModelAndView();
        List<Map<Object, Object>> list = new ArrayList<>();
        if (Objects.equals(hostip, "ALL")) {
            String[] a = getKeyTool.getProperty("config", server).split(",");//获取配置文件value值
            for (String server1 : a) {
                Map map = getKeyTool.split(key, server1);
                list.add(map);
            }
        } else {
            Map li = getKeyTool.split(key, hostip);
            list.add(li);
        }
        mv.addObject("datas", list);
        mv.setViewName("system/memcache/addlist");
        return mv;
    }

}

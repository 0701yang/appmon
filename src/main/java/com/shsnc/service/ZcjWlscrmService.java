package com.shsnc.service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.shsnc.entity.system.Bean;
import com.shsnc.entity.system.WlscrmThread;
import com.shsnc.util.pager.PageBoundsUtil;
import com.shsnc.util.pager.Pager;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("zcjWlscrmService")
public class ZcjWlscrmService {
    @Resource(name = "sqlSessionTemplate")
    private SqlSessionTemplate sqlSessionTemplate;


    /**
     * 查询Crm
     * @return
     * @throws Exception
     */
    public Pager<Bean> findCrm(String[] string) throws Exception {
        PageBounds pageBounds = PageBoundsUtil.pageBoundsOrderExtend("APP,MODULE,IP,PORT");
        int count = sqlSessionTemplate.selectOne("WlscrmThreadMapper.countcrm",string);
        List<Bean> list = sqlSessionTemplate.selectList("WlscrmThreadMapper.findHistoryCrm", string, pageBounds);
        Pager<Bean> pages = new Pager<Bean>(count, list);

        return pages;
    }

    /**
     * 查询Osb
     * @return
     * @throws Exception
     */
    public Pager<Bean> findOsb(String string) throws Exception {
        PageBounds pageBounds = PageBoundsUtil.pageBoundsOrderExtend("APP,MODULE,IP,PORT");
        int count = sqlSessionTemplate.selectOne("WlscrmThreadMapper.countosb",string);
        List<Bean> list = sqlSessionTemplate.selectList("WlscrmThreadMapper.findHistoryOsb", string, pageBounds);
        Pager<Bean> pages = new Pager<Bean>(count, list);

        return pages;
    }

    /**
     *  查询历史信息
     * @param ip
     * @param port
     * @return
     * @throws Exception
     */
    public Pager<WlscrmThread> findHistory(String ip , String port) throws Exception{
        PageBounds pageBounds = PageBoundsUtil.pageBoundsOrderExtend("time.asc");
        Map<String , Object> map = new HashMap<String , Object>();
        map.put("ip" , ip);
        map.put("port" , port);
        int count = sqlSessionTemplate.selectOne("WlscrmThreadMapper.countHistory" , map);
        List<WlscrmThread> list = sqlSessionTemplate.selectList("WlscrmThreadMapper.history", map, pageBounds);
        Pager<WlscrmThread> pages = new Pager<WlscrmThread>(count, list);

        return pages;
   }


}

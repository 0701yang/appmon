package com.shsnc.service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.shsnc.entity.system.Bean;
import com.shsnc.util.pager.PageBoundsUtil;
import com.shsnc.util.pager.Pager;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

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



}

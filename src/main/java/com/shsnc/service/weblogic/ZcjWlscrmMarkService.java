package com.shsnc.service.weblogic;

import org.apache.commons.collections.CollectionUtils;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;


@Service("zcjWlscrmMarkService")
public class ZcjWlscrmMarkService {

    @Resource(name = "sqlSessionTemplate")
    private SqlSessionTemplate sqlSessionTemplate;

    /**
     * 查询健康度分数: crm正常情况一
     * @param string
     * @return
     */
    public Integer markcrmone(String[] string){
        List<String> apacheList = new ArrayList<String>();
        CollectionUtils.addAll(apacheList, string);
        return  sqlSessionTemplate.selectOne("WlscrmMarkMapper.markcrmone",apacheList);
    }

    /**
     * 查询健康度分数: crm特殊情况二
     * @param string
     * @return
     */
    public Integer markcrmtwo(String[] string){
        List<String> apacheList = new ArrayList<String>();
        CollectionUtils.addAll(apacheList, string);
        return  sqlSessionTemplate.selectOne("WlscrmMarkMapper.markcrmtwo",apacheList);
    }

    /**
     * CRM异常的个数
     * @param string
     * @return
     */
    public Integer crmerror(String[] string){
        List<String> apacheList = new ArrayList<String>();
        CollectionUtils.addAll(apacheList, string);
        return  sqlSessionTemplate.selectOne("WlscrmMarkMapper.crmerror",apacheList);
    }

    /**
     * osb分数
     * @param string
     * @return
     */
    public Integer markosb(String string){
        return  sqlSessionTemplate.selectOne("WlscrmMarkMapper.markosb",string);
    }

    /**
     * osb异常的个数
     * @param string
     * @return
     */
    public Integer osberror(String string){
        return  sqlSessionTemplate.selectOne("WlscrmMarkMapper.osberror",string);
    }

}

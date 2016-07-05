package com.shsnc.service;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


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
        return  sqlSessionTemplate.selectOne("WlscrmMarkMapper.markcrmone",string);
    }

    /**
     * 查询健康度分数: crm特殊情况二
     * @param string
     * @return
     */
    public Integer markcrmtwo(String[] string){
        return  sqlSessionTemplate.selectOne("WlscrmMarkMapper.markcrmtwo",string);
    }

    /**
     * CRM异常的个数
     * @param string
     * @return
     */
    public Integer crmerror(String[] string){
        return  sqlSessionTemplate.selectOne("WlscrmMarkMapper.crmerror",string);
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

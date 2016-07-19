package com.shsnc.service.weblogic;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Service("zcjWlscrmMarkService")
public class ZcjWlscrmMarkService {

    @Resource(name = "sqlSessionTemplate")
    private SqlSessionTemplate sqlSessionTemplate;

    /**
     * osb分数
     * @param string
     * @return
     */
    public Integer markosb(String string){
        return  sqlSessionTemplate.selectOne("WlscrmMarkMapper.markosb",string);
    }

}

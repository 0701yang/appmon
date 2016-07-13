package com.shsnc.service.weblogic;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("crmAppMarkService")
public class CrmAppMarkService {

    @Resource(name = "sqlSessionTemplate")
    private SqlSessionTemplate sqlSessionTemplate;

    /**
     * 查询<60的所有数据的个数
     * @return
     * @throws Exception
     */
    public int findAllSix() throws Exception{
        return sqlSessionTemplate.selectOne("CrmAppMarkMapper.findAllSix");
    }

    /**
     * 查询所有数据的个数
     * @return
     * @throws Exception
     */
    public int findAll() throws Exception{
        return sqlSessionTemplate.selectOne("CrmAppMarkMapper.findAll");
    }

    /**
     * 根据crm查询个数
     * @param strings
     * @return
     * @throws Exception
     */
    public Integer findSixByCrm(String[] strings) throws Exception{
        return sqlSessionTemplate.selectOne("CrmAppMarkMapper.findSixByCrm",strings);
    }


}

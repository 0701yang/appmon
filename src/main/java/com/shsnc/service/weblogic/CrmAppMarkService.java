package com.shsnc.service.weblogic;

import org.apache.commons.collections.CollectionUtils;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service("crmAppMarkService")
public class CrmAppMarkService {

    @Resource(name = "sqlSessionTemplate")
    private SqlSessionTemplate sqlSessionTemplate;

    /**
     * 查询<60的所有数据的个数
     * @return
     * @throws Exception
     */
    public Integer findAllSix(String[] strings) throws Exception{
        List<String> apacheList = new ArrayList<String>();
        CollectionUtils.addAll(apacheList, strings);
        return sqlSessionTemplate.selectOne("CrmAppMarkMapper.findAllSix",apacheList);
    }

    /**
     * 查询所有数据的个数
     * @return
     * @throws Exception
     */
    public Integer findAll() throws Exception{
        return sqlSessionTemplate.selectOne("CrmAppMarkMapper.findAll");
    }

    /**
     * 根据crm查询个数
     * @param strings
     * @return
     * @throws Exception
     */
    public Integer findSixByCrm(String[] strings) throws Exception{
        List<String> apacheList = new ArrayList<String>();
        CollectionUtils.addAll(apacheList, strings);
        return sqlSessionTemplate.selectOne("CrmAppMarkMapper.findSixByCrm",apacheList);
    }


}

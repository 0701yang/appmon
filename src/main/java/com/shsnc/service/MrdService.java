package com.shsnc.service;

import com.shsnc.entity.system.MonRecordDatasource;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("mrdService")
public class MrdService {

    @Resource(name = "sqlSessionTemplate")
    private SqlSessionTemplate sqlSessionTemplate;

    /**
     * 根据name查询
     * @param name
     * @return
     * @throws Exception
     */
    public List<MonRecordDatasource> findByName(String name) throws Exception{
        List<MonRecordDatasource> list = sqlSessionTemplate.selectList("MrdMapper.findByName",name);
        return list;
    }


}

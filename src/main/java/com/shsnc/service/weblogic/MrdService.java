package com.shsnc.service.weblogic;

import com.shsnc.dataSource.DatabaseContextHolder;
import com.shsnc.entity.weblogic.MonRecordDatasource;
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
        DatabaseContextHolder.setCustomerType("dataSource_sd1");
        return sqlSessionTemplate.selectList("MrdMapper.findByName",name);
    }


}

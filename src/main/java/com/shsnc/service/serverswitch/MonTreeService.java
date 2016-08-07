package com.shsnc.service.serverswitch;

import com.shsnc.entity.serverswitch.MonTree;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Administrator on 2016/7/25.
 */
@Service("monTreeService")
public class MonTreeService {
    @Resource(name = "sqlSessionTemplate")
    private SqlSessionTemplate sqlSessionTemplate;

    public List<MonTree> findRootNodes(){
        List<MonTree> rootTrees=sqlSessionTemplate.selectList("MonTreeMapper.findRootNodes",1125);
        return rootTrees;
    }
    public void GetInfo(){
       MonTree tree= (MonTree) sqlSessionTemplate.selectOne("MonTreeMapper.findTreeById");
        System.out.println(tree.getName());
    }

}

package com.shsnc.service.serverswitch;

import com.shsnc.entity.serverswitch.MonNode;
import com.shsnc.entity.serverswitch.MonTree;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Administrator on 2016/7/27.
 */
@Service("monNodeService")
public class MonNodeService {

    @Resource(name="sqlSessionTemplate")
    private SqlSessionTemplate sqlSessionTemplate;

    public List<MonNode> findChildrenNodes(int treeParentId){
        List<MonNode> childrenNodes=sqlSessionTemplate.selectList("MonNodeMapper.findChildrenNodes",treeParentId);
        return childrenNodes;
    }
}

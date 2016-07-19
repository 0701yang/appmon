package com.shsnc.service.system;

import com.shsnc.entity.system.Privilege;
import com.shsnc.entity.system.RolePrivilegeKey;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("privilegeService")
public class PrivilegeService {

    @Resource(name = "sqlSessionTemplate")
    private SqlSessionTemplate sqlSessionTemplate;

    /**
     * 查询所有权限
     * @return
     * @throws Exception
     */
    public List<Privilege> findAll() throws Exception{
        return sqlSessionTemplate.selectList("PrivilegeMapper.findAll");
    }

    /**
     * 根据id查询出r-p表数据
     * @return
     * @throws Exception
     */
    public List<RolePrivilegeKey> findByRoleId(String id) throws Exception{
        return sqlSessionTemplate.selectList("RolePrivilegeMapper.findByRoleId",id);
    }

    /**
     * 保存
     * @param privilegeKey
     * @throws Exception
     */
    public void save(RolePrivilegeKey privilegeKey) throws Exception{
        sqlSessionTemplate.insert("RolePrivilegeMapper.save", privilegeKey);
    }

    /**
     * 删除
     * @param s
     * @throws Exception
     */
    public void del(String s) throws Exception{
        sqlSessionTemplate.insert("RolePrivilegeMapper.del", s);
    }

    /**
     * 查询所有
     * @return
     * @throws Exception
     */
    public List<RolePrivilegeKey> findKey() throws Exception{
        return sqlSessionTemplate.selectList("RolePrivilegeMapper.findKey");
    }



}

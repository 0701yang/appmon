package com.shsnc.service.system;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.shsnc.entity.system.Role;
import com.shsnc.entity.system.User;
import com.shsnc.util.pager.PageBoundsUtil;
import com.shsnc.util.pager.Pager;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("roleService")
public class RoleService {

    @Resource(name = "sqlSessionTemplate")
    private SqlSessionTemplate sqlSessionTemplate;


    /**
     * 查询所有的角色信息
     * @return
     * @throws Exception
     */
    public Pager<Role> findRole() throws Exception{
        PageBounds pageBounds = PageBoundsUtil.pageBoundsOrderExtend("ID.ASC");
        List<Role> list = sqlSessionTemplate.selectList("RoleMapper.listAllRole",null,pageBounds);
        int count = sqlSessionTemplate.selectOne("RoleMapper.count");
        Pager<Role> pages = new Pager<Role>(count, list);

        return pages;
    }

    /**
     * 保存
     * @param role
     * @throws Exception
     */
    public void save(Role role) throws Exception {
        sqlSessionTemplate.insert("RoleMapper.save", role);
    }

    /**
     *  判断是否存在角色名
     * @param name
     * @return
     * @throws Exception
     */
    public Pager<Role> findByRoleName(String name) throws Exception {
        PageBounds pageBounds = PageBoundsUtil.pageBoundsExtend();
        List<Role> list = sqlSessionTemplate.selectList("RoleMapper.findByName", name, pageBounds);
        int count = sqlSessionTemplate.selectOne("RoleMapper.countByName",name);
        Pager<Role> pages = new Pager<Role>(count, list);
        return pages;
    }

    /**
     * 删除
     * @param id
     * @throws Exception
     */
    public void del(String id) throws Exception{
        sqlSessionTemplate.delete("RoleMapper.del",id);
    }

    /**
     * 修改
     * @param role
     * @throws Exception
     */
    public void edit(Role role) throws Exception{
        sqlSessionTemplate.delete("RoleMapper.edit",role);
    }

    /**
     *  根据id查询角色信息
     * @param id
     * @return
     * @throws Exception
     */
    public Pager<Role> findById(String id) throws Exception {
        PageBounds pageBounds = PageBoundsUtil.pageBoundsOrderExtend("ID.ASC");
        List<Role> list = sqlSessionTemplate.selectList("RoleMapper.findById", id, pageBounds);
        int count = sqlSessionTemplate.selectOne("RoleMapper.countById",id);
        Pager<Role> pages = new Pager<Role>(count, list);
        return pages;
    }


}

package com.shsnc.service;

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

}

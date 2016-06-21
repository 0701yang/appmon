package com.shsnc.service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.shsnc.entity.system.Role;
import com.shsnc.util.PageBoundsUtil;
import com.shsnc.util.pager.Pager;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("roleService")
public class RoleService {
    @Resource(name = "sqlSessionTemplate")
    private SqlSessionTemplate sqlSessionTemplate;

    public Pager<Role> listRole() throws Exception{
        int count = 9;
        PageBounds pageBounds = PageBoundsUtil.pageBoundsOrderExtend("id.desc");
        List<Role> list = sqlSessionTemplate.selectList("RoleMapper.listAllRole",pageBounds);
        Pager<Role> pages = new Pager<Role>(count, list);

        return pages;

    }


}

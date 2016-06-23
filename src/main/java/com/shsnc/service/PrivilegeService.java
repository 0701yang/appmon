package com.shsnc.service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.shsnc.entity.system.Privilege;
import com.shsnc.util.pager.PageBoundsUtil;
import com.shsnc.util.pager.Pager;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("privilegeService")
public class PrivilegeService {

    @Resource(name = "sqlSessionTemplate")
    private SqlSessionTemplate sqlSessionTemplate;

    /**
     * 查询所有的权限信息
     * @return
     * @throws Exception
     */
    public Pager<Privilege> findPrivilege() throws Exception{
        PageBounds pageBounds = PageBoundsUtil.pageBoundsOrderExtend("ID.ASC");
        List<Privilege> list = sqlSessionTemplate.selectList("PrivilegeMapper.listAllPrivilege",null,pageBounds);
        int count = sqlSessionTemplate.selectOne("PrivilegeMapper.count");
        Pager<Privilege> pages = new Pager<Privilege>(count, list);

        return pages;
    }

}

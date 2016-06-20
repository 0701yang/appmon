package com.shsnc.service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.shsnc.util.pager.Pager;
import com.shsnc.entity.system.User;
import com.shsnc.util.PageBoundsUtil;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("userService")
public class UserService {


    @Resource(name = "sqlSessionTemplate")
    private SqlSessionTemplate sqlSessionTemplate;

    /**
     * 查询所有的用户信息
     * @return
     * @throws Exception
     */
    public Pager<User> findUser() throws Exception{
        int count = 4;
        PageBounds pageBounds = PageBoundsUtil.pageBoundsOrderExtend("id.desc");
        List<User> list = sqlSessionTemplate.selectList("UserMapper.listAllUser",pageBounds);
        Pager<User> pages = new Pager<User>(count, list);

        return pages;
    }
}

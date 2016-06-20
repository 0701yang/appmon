package com.shsnc.service.dao;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.shsnc.entity.Pager;
import com.shsnc.entity.system.User;
import com.shsnc.util.PageBoundsUtil;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by zhangliling on 16/6/20.
 */
@Service("userService")
public class UserService {


    @Resource(name = "sqlSessionTemplate")
    private SqlSessionTemplate sqlSessionTemplate;


    public Pager<User> findUser() throws Exception{
        int count = 4;
        PageBounds pageBounds = PageBoundsUtil.pageBoundsOrderExtend("id.desc");
        List<User> list = sqlSessionTemplate.selectList("UserMapper.listAllUser",pageBounds);
        Pager<User> pages = new Pager<User>(count, list);

        return pages;
    }
}

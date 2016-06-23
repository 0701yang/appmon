package com.shsnc.service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.shsnc.entity.system.User;
import com.shsnc.util.PageData;
import com.shsnc.util.pager.PageBoundsUtil;
import com.shsnc.util.pager.Pager;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

@Service("userService")
public class UserService {

    @Resource(name = "sqlSessionTemplate")
    private SqlSessionTemplate sqlSessionTemplate;

    /**
     * 查询所有的用户信息
     *
     * @return
     * @throws Exception
     */
    public Pager<User> findUser() throws Exception {
        PageBounds pageBounds = PageBoundsUtil.pageBoundsOrderExtend("ID.ASC");
        List<User> list = sqlSessionTemplate.selectList("UserMapper.listAllUser", null, pageBounds);
        int count = sqlSessionTemplate.selectOne("UserMapper.count");
        Pager<User> pages = new Pager<User>(count, list);

        return pages;
    }

    /**
     * 根据name查询用户信息
     * @param pd
     * @return
     * @throws Exception
     */
    public Pager<User> findByUId(PageData pd) throws Exception {
        PageBounds pageBounds = PageBoundsUtil.pageBoundsOrderExtend("ID.ASC");
        List<User> list = sqlSessionTemplate.selectList("UserMapper.findByUId", pd.getString("username"), pageBounds);
        int count = sqlSessionTemplate.selectOne("UserMapper.countByName",pd.getString("username"));
        Pager<User> pages = new Pager<User>(count, list);

        return pages;
    }

    /**
     * 保存
     * @param
     * @return
     * @throws Exception
     */
    public void save(User user) throws Exception {
         sqlSessionTemplate.insert("UserMapper.save", user);
    }

}

package com.shsnc.service.system;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.shsnc.entity.system.User;
import com.shsnc.util.pager.PageBoundsUtil;
import com.shsnc.util.pager.Pager;
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
     * @param name
     * @return
     * @throws Exception
     */
    public Pager<User> findByName(String name) throws Exception {
        PageBounds pageBounds = PageBoundsUtil.pageBoundsOrderExtend("ID.ASC");
        List<User> list = sqlSessionTemplate.selectList("UserMapper.findByName", name, pageBounds);
        int count = sqlSessionTemplate.selectOne("UserMapper.countByName",name);
        Pager<User> pages = new Pager<User>(count, list);
        return pages;
    }

    /**
     *  根据id查询用户信息
     * @param id
     * @return
     * @throws Exception
     */
    public User findById(String id) throws Exception {
        return sqlSessionTemplate.selectOne("UserMapper.findById", id);
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

    /**
     * 删除
     * @param id
     * @throws Exception
     */
    public void del(String id) throws Exception{
        sqlSessionTemplate.delete("UserMapper.del",id);
    }

    /**
     * 修改
     * @param user
     * @throws Exception
     */
    public void edit(User user) throws Exception{
        sqlSessionTemplate.update("UserMapper.edit",user);
    }

    /**
     * 根据用户名和密码查询用户
     * @param user
     * @return
     * @throws Exception
     */
    public User findByUserNameAndPassword(User user) throws Exception{
        User u = sqlSessionTemplate.selectOne("UserMapper.findByUserNameAndPassword",user);
        return u;
    }



}

package com.shsnc.service;


import com.shsnc.dao.DaoSupport;
import com.shsnc.util.PageData;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("userService")
public class UserService {

    @Resource(name = "daoSupport")
    private DaoSupport dao;

    public List<PageData> listAllUser(PageData pd)throws Exception{
        return (List<PageData>) dao.findForList("UserMapper.listAllUser", pd);
    }

}

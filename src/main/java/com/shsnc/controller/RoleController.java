package com.shsnc.controller;

import com.shsnc.entity.system.Role;
import com.shsnc.service.RoleService;
import com.shsnc.util.pager.Pager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;


/**
 * 角色界面
 */
@Controller
@RequestMapping(value="/role")
public class RoleController extends BaseController{

    @Resource(name="roleService")
    private RoleService roleService;

    /**
     * 查询所有的角色信息
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/list")
    public ModelAndView list() throws Exception{
        ModelAndView mv = this.getModelAndView();
        Pager<Role> roleList = roleService.findRole();

        mv.setViewName("system/role/list");
        mv.addObject("roleList", roleList);
        return mv;
    }
}

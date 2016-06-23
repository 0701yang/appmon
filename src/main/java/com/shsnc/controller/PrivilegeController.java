package com.shsnc.controller;


import com.shsnc.entity.system.Privilege;
import com.shsnc.entity.system.Role;
import com.shsnc.service.PrivilegeService;
import com.shsnc.service.RoleService;
import com.shsnc.util.pager.Pager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

/**
 * 权限界面
 */
@Controller
@RequestMapping(value="/privilege")
public class PrivilegeController extends BaseController{

    @Resource(name="privilegeService")
    private PrivilegeService privilegeService;


    /**
     * 查询所有的权限信息信息
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/list")
    public ModelAndView list() throws Exception{
        ModelAndView mv = this.getModelAndView();
        Pager<Privilege> privilegeList = privilegeService.findPrivilege();

        mv.setViewName("system/privilege/list");
        mv.addObject("privilegeList", privilegeList);
        return mv;
    }

}

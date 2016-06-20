package com.shsnc.controller;

import com.shsnc.entity.system.Role;
import com.shsnc.entity.system.User;
import com.shsnc.service.RoleService;
import com.shsnc.service.UserService;
import com.shsnc.util.pager.Pager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;


/**
 * 类名称：UserController
 * 创建人：lixinhao
 * 创建时间：
 * @version
 */
@Controller
@RequestMapping(value="/user")
public class UserController extends BaseController {
	
	@Resource(name="userService")
	private UserService userService;
	@Resource(name="roleService")
	private RoleService roleService;



	/**
	 * 查询所有的用户信息
	 * @return
	 * @throws Exception
     */
	@RequestMapping(value="/listUsers")
	public ModelAndView  listUser() throws Exception{
		ModelAndView mv = this.getModelAndView();
		Pager<User> userList = userService.findUser();

		mv.setViewName("system/user/list");
		mv.addObject("userList", userList);
		return mv;
	}

	/**
	 * 添加用户并跳转到添加页面
	 * @return
	 * @throws Exception
     */
	@RequestMapping(value="/toAddUser")
	public ModelAndView  toAddUser() throws Exception{
		ModelAndView mv = this.getModelAndView();
		//准备数据
		Pager<Role>  roleList = roleService.listRole();

		mv.setViewName("system/user/add");
		mv.addObject("msg" , "saveUser");
		mv.addObject("roleList", roleList);

		return mv;
	}






}

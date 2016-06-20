package com.shsnc.controller;

import com.shsnc.entity.Page;
import com.shsnc.entity.Pager;
import com.shsnc.entity.system.User;
import com.shsnc.service.dao.UserService;
import com.shsnc.util.PageData;
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

	/**
	 * 查询所有的用户信息
	 * @return
	 * @throws Exception
     */
	@RequestMapping(value="/listUsers")
	public ModelAndView  listUser() throws Exception{
		ModelAndView mv = this.getModelAndView();
		//ageData pd = new PageData();
		//zpd = this.getPageData();
		//page.setPd(pd);
		Pager<User> userlist = userService.findUser();
		mv.setViewName("system/user/list");
		mv.addObject("userlist", userlist);
		//mv.addObject("pd", pd);
		return mv;
	}




}

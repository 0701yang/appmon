package com.shsnc.controller;

import com.shsnc.service.UserService;
import com.shsnc.util.PageData;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;


/**
 * 类名称：UserController
 * 创建人：FH 
 * 创建时间：2014年6月28日
 * @version
 */
@Controller
@RequestMapping(value="/user")
public class UserController extends BaseController {
	
	@Resource(name="userService")
	private UserService userService;


	@RequestMapping(value="/list")
	public ModelAndView  list() throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		List<PageData> userList = userService.listAllUser(pd);			//列出用户列表
		mv.setViewName("system/user/list");
		mv.addObject("userList", userList);
		mv.addObject("pd", pd);
		return mv;

	}

}

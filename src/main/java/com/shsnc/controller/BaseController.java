package com.shsnc.controller;


import javax.servlet.http.HttpServletRequest;

import com.shsnc.util.UuidUtil;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;


public class BaseController {
	

	private static final long serialVersionUID = 6357869213649815390L;
	
	/**
	 * 得到ModelAndView
	 */
	public ModelAndView getModelAndView(){
		return new ModelAndView();
	}
	
	/**
	 * 得到request对象
	 */
	public HttpServletRequest getRequest() {

		return ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
	}

	/**
	 * 得到32位的uuid
	 * @return
	 */
	public String get32UUID(){
		
		return UuidUtil.get32UUID();
	}
	
}

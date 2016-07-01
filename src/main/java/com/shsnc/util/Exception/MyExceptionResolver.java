package com.shsnc.util.Exception;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 抛出异常跑出
 */
public class MyExceptionResolver implements HandlerExceptionResolver{

    public ModelAndView resolveException(HttpServletRequest request,

                                         HttpServletResponse response,
                                         Object handler,
                                         Exception ex) {
        ex.printStackTrace();
        ModelAndView mv = new ModelAndView("error/error");
        mv.addObject("exception", ex.toString().replaceAll("\n", "<br/>"));
        return mv;
    }

}

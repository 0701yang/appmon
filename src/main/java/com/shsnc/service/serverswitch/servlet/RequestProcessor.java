package com.shsnc.service.serverswitch.servlet;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;

public class RequestProcessor
{
  private static transient Log log = LogFactory.getLog(RequestProcessor.class);
  private static final String DEFAULT_VIEW = "view";
  public static final String ACTION = "action";
  protected Class[] types = { HttpServletRequest.class, HttpServletResponse.class };
  
  public void process(HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    String className = null;
    String methodName = null;
    try
    {
      className = getClassName(request);
      if ((StringUtils.isBlank(className)) || (StringUtils.isEmpty(className))) {
        throw new Exception("类名为空!");
      }
      methodName = getMethodName(request);
      if ((StringUtils.isBlank(methodName)) || (StringUtils.isEmpty(methodName))) {
        throw new Exception("方法名为空!");
      }

      exeMethod(className, methodName, request, response);
    }
    catch (Exception ex)
    {
      log.error("解析请求的类和方法出错 ex:" + ex);
      throw ex;
    }
  }
  
  public void exeMethod(String pClassName, String pMethodName, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    try
    {
      if (log.isDebugEnabled() == true) {
        log.debug("开始处理方法:" + pMethodName);
      }
      if(pClassName.equals("TreeAction")){
        pClassName="com.asiainfo.mon.web.action.TreeAction";
      }
      Class cls = Class.forName(pClassName);
      Object clsObj = cls.getDeclaredConstructor(null).newInstance(null);
      Object[] args = { request, response };
      Method method = cls.getMethod(pMethodName, this.types);
      method.invoke(clsObj, args);
      if (log.isDebugEnabled() == true) {
        log.debug("结束处理方法:" + pMethodName);
      }
    }
    catch (Exception ex)
    {
      log.error(ex.getMessage(), ex);
      throw ex;
    }
  }
  
  private String getClassName(HttpServletRequest request)
  {
    try
    {
      String requestUrl = request.getPathInfo();
      if ((StringUtils.isEmpty(requestUrl)) || (StringUtils.isBlank(requestUrl))) {
        throw new Exception("请求参数中没有类名或者属性文件的key");
      }
      String[] detailUrl = StringUtils.split(requestUrl, '/');
      if (detailUrl.length == 1) {
        detailUrl = new String[] { "view", detailUrl[0] };
      }
      if (detailUrl.length < 2) {
        throw new Exception("没有找到模块参数，应该符合这个规范:http://localhost:8080/business/so/test?action=save");
      }
      String moduleName = detailUrl[0];
      String key = detailUrl[1];
      if ((StringUtils.isEmpty(key)) || (StringUtils.isBlank(key))) {
        throw new Exception("请求参数中没有类名或者属性文件的key");
      }
      String className = null;
      if (log.isDebugEnabled() == true) {
        log.debug("使用类模式");
      }
      className = key;
      if (log.isDebugEnabled() == true) {
        log.debug("类名:" + className);
      }
      return className;
    }
    catch (Exception ex) {}
    return null;
  }
  
  private String getMethodName(HttpServletRequest request)
    throws Exception
  {
    String methodName = request.getParameter("action");
    if (log.isDebugEnabled() == true) {
      log.debug("方法名:" + methodName);
    }
    if ((StringUtils.isEmpty(methodName) == true) || (StringUtils.isBlank(methodName) == true)) {
      throw new Exception("请求参数中没有action这个参数或者action对应的这个参数为空");
    }
    return methodName;
  }
  
  public static void main(String[] args)
  {
    String str = "/so/test?save=sds";
    String[] ss = StringUtils.split(str, '/');
    for (int i = 0; i < ss.length; i++) {
      System.out.println(ss[i]);
    }
  }
}

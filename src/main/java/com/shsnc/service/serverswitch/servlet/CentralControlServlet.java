package com.shsnc.service.serverswitch.servlet;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class CentralControlServlet
  extends HttpServlet
{
  private static transient Log log = LogFactory.getLog(CentralControlServlet.class);
  private static RequestProcessor rp = new RequestProcessor();
  
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
    throws ServletException, IOException
  {
    try
    {
      rp.process(req, resp);
    }
    catch (Exception ex)
    {
      throw new ServletException(ex);
    }
  }
  
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
    throws ServletException, IOException
  {
    try
    {
      rp.process(req, resp);
    }
    catch (Exception ex)
    {
      throw new ServletException(ex);
    }
  }
}

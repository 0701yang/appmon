package com.shsnc.service.serverswitch.switchaction;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public abstract class BaseAction
{
  public String getStringFormInputStreamString(HttpServletRequest request)
    throws IOException
  {
    StringBuffer rtn = new StringBuffer();
    ServletInputStream aReader = request.getInputStream();
    byte[] buf = new byte[8192];
    for (;;)
    {
      int result = aReader.readLine(buf, 0, buf.length);
      if (result == -1) {
        break;
      }
      String line = new String(buf, 0, result, "UTF-8");
      rtn.append(line);
    }
    return rtn.toString();
  }
  
  protected String getParameter(HttpServletRequest request, String name)
    throws Exception
  {
    String rtn = new String(request.getParameter(name).getBytes("ISO-8859-1"), "GBK");
    return rtn;
  }
  
  public void showInfo(HttpServletResponse response, String rtn)
  {
    try
    {
      response.setContentType("text/xml; charset=GBK");
      response.setHeader("Cache-Control", "no-cache");
      response.getWriter().write(rtn);
    }
    catch (IOException ex)
    {
      ex.printStackTrace();
    }
  }
  
  public void showInfo(HttpServletResponse response, String rtn, String encoding)
  {
    try
    {
      response.setContentType("text/xml; charset=" + encoding + "");
      response.setHeader("Cache-Control", "no-cache");
      response.getWriter().write(rtn);
    }
    catch (IOException ex)
    {
      ex.printStackTrace();
    }
  }
  
  public void showHtmlInfo(HttpServletResponse response, String rtn)
  {
    try
    {
      response.setContentType("text/html; charset=GBK");
      response.setHeader("Cache-Control", "no-cache");
      response.getWriter().write(rtn);
    }
    catch (IOException ex)
    {
      ex.printStackTrace();
    }
  }
  
  public static void main(String[] args)
    throws Exception
  {
    byte[] b = "asdsaasd阿瑟大撒".getBytes();
    System.out.println(new String(b, 0, b.length, "UTF-8"));
  }
}

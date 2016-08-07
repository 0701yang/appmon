package com.shsnc.util.appswitch.tag;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

public class HtmlColumnTag
  extends TagSupport
{
  private static transient Log log = LogFactory.getLog(ColumnTag.class);
  private String title = null;
  private String width = "100";
  
  public int doStartTag()
    throws JspException
  {
    try
    {
      DataGridTag parent = (DataGridTag)getParent();
    }
    catch (Exception ex)
    {
      DataGridTag parent;
      log.error("错误:", ex);
      throw new JspException(ex);
    }
    return 0;
  }
  
  public void setTitle(String title)
  {
    this.title = title;
  }
  
  public void setWidth(String width)
  {
    this.width = width;
  }
}

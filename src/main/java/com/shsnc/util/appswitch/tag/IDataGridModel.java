package com.shsnc.util.appswitch.tag;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

public abstract interface IDataGridModel
{
  public abstract void setRequest(HttpServletRequest paramHttpServletRequest)
    throws Exception;
  
  public abstract void setCondition(HashMap paramHashMap)
    throws Exception;
  
  public abstract long count()
    throws Exception;
  
  public abstract Object[] getData(long paramLong1, long paramLong2)
    throws Exception;
}

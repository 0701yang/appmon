package com.shsnc.util.appswitch;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Properties;

public final class MiscUtil
{
  private static transient Log log = LogFactory.getLog(MiscUtil.class);
  private static String TRACE_PATH = "/tmp/aitrc";
  private static String REGION_ID_KEY = "RegionId";
  private static String AWR_PATH = "/tmp/awr";
  
  static
  {
    try
    {
      Properties p = Resource.loadPropertiesFromClassPath("properties/jdbc.properties");
      String path = p.getProperty("trace.path");
      if (!StringUtils.isBlank(path)) {
        TRACE_PATH = path;
      }
      String key = p.getProperty("userinfo.regionid.key");
      if (!StringUtils.isBlank(key)) {
        REGION_ID_KEY = key;
      }
      String awrPath = p.getProperty("awr.path");
      if (!StringUtils.isBlank(awrPath)) {
        AWR_PATH = awrPath;
      }
    }
    catch (Throwable ex)
    {
      log.error("��ʼ������", ex);
    }
  }
  
  public static String getTracePathPrefix()
    throws Exception
  {
    return TRACE_PATH;
  }
  
  public static String getUserInfoRegionIdKey()
    throws Exception
  {
    return REGION_ID_KEY;
  }
  
  public static String getAwrPath()
  {
    return AWR_PATH;
  }
}

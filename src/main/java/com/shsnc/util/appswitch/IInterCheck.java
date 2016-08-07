package com.shsnc.util.appswitch;

import java.util.Map;

public abstract interface IInterCheck
{
  public static final String EXCEPTION = "EXCEPTION";
  public static final String TIMEOUT = "TIMEOUT";
  public static final String OK = "OK";
  
  public abstract CheckStatus check(Map paramMap)
    throws Exception;
}

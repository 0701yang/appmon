package com.shsnc.entity.serverswitch;

public class MonPHost
{
  private String hostname;
  private String username;
  private String password;
  private String state;
  private long sshport;
  private String ip;
  
  public void setHostname(String hostname)
  {
    this.hostname = hostname;
  }
  
  public void setUsername(String username)
  {
    this.username = username;
  }
  
  public void setPassword(String password)
  {
    this.password = password;
  }
  
  public void setState(String state)
  {
    this.state = state;
  }
  
  public void setSshport(long sshport)
  {
    this.sshport = sshport;
  }
  
  public void setIp(String ip)
  {
    this.ip = ip;
  }
  
  public String getHostname()
  {
    return this.hostname;
  }
  
  public String getUsername()
  {
    return this.username;
  }
  
  public String getPassword()
  {
    return this.password;
  }
  
  public String getState()
  {
    return this.state;
  }
  
  public long getSshport()
  {
    return this.sshport;
  }
  
  public String getIp()
  {
    return this.ip;
  }
}

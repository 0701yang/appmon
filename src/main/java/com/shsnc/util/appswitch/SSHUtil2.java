package com.shsnc.util.appswitch;

import com.trilead.ssh2.Connection;
import com.trilead.ssh2.SCPClient;
import com.trilead.ssh2.Session;
import com.trilead.ssh2.StreamGobbler;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SSHUtil2
{
  public static int collectTraceFile(String ip, int sshPort, String username, String password)
    throws Exception
  {
    int rtn = 0;
    Connection conn = null;
    Session sess = null;
    try
    {
      conn = new Connection(ip, sshPort);
      
      conn.connect();
      boolean isAuthenticated = conn.authenticateWithPassword(username, password);
      if (!isAuthenticated) {
        throw new IOException("��֤ʧ��");
      }
      String fileName = "collect_trc.sh";
      byte[] bb = FileUtils.readFileToByteArray(new File(MiscUtil.getTracePathPrefix() + "/collect_trc.sh"));
      
      upload(conn, ip, sshPort, username, password, bb, "/tmp/", fileName);
      sess = conn.openSession();
      
      sess.execCommand(" chmod +x /tmp/" + fileName + " && /tmp/" + fileName + " && rm -rf /tmp/" + fileName);
      
      List list = new ArrayList();
      InputStream stdout = new StreamGobbler(sess.getStdout());
      BufferedReader br = new BufferedReader(new InputStreamReader(stdout));
      for (;;)
      {
        String line = br.readLine();
        if (line == null) {
          break;
        }
        if (StringUtils.contains(line, "trc")) {
          list.add(line);
        }
      }
      rtn = list.size();
      download(conn, ip, sshPort, username, password, (String[])list.toArray(new String[0]), MiscUtil.getTracePathPrefix() + "/data/");
    }
    catch (Exception ex)
    {
      throw ex;
    }
    finally
    {
      if (sess != null) {
        sess.close();
      }
      if (conn != null) {
        conn.close();
      }
    }
    return rtn;
  }
  
  public static void upload(Connection conn, String ip, int sshPort, String username, String password, byte[] bytes, String destPath, String fileName)
    throws Exception
  {
    try
    {
      SCPClient scp = conn.createSCPClient();
      scp.put(bytes, fileName, destPath);
    }
    catch (Exception ex)
    {
      throw ex;
    }
  }
  
  public static void download(Connection conn, String ip, int sshPort, String username, String password, String[] remoteFileName, String localPath)
    throws Exception
  {
    try
    {
      SCPClient scp = conn.createSCPClient();
      scp.get(remoteFileName, localPath);
    }
    catch (Exception ex)
    {
      throw ex;
    }
  }
}

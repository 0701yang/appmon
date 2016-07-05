package com.shsnc.util.mecache;


import org.apache.commons.lang.StringUtils;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.URLEncoder;
import java.util.*;
import java.util.zip.GZIPInputStream;

public class GetKeyTool {
    private static final byte[] BYTE_GET = {103, 101, 116, 32};
    private static final byte[] BYTE_CRLF = {13, 10};
    private static final Integer timeoutSeconds = 5;
    private  Object[] value = null;

    /**
     * 查询信息
     * @param key
     * @param server
     * @return
     * @throws Exception
     */
    public Map split(String key , String server) {
        String[] b = server.split(":");
        String host = b[0];
        List<List> list1 = new ArrayList<List>();
        Integer port = Integer.parseInt(b[1]);
        Map<Object, Object> map = new HashMap<Object, Object>();
        try {
            value = (Object[]) get(key, host, port);
        } catch (Exception e) {

            e.printStackTrace();
        }
        try{
            for (Object aValue : value) {
                List<String> list = new ArrayList<>();
                String[] str = aValue.toString().split("\\t");//根据tab分割
                Collections.addAll(list, str);
                Collections.addAll(list1, list);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        map.put("key" , key);
        map.put("list",list1 );
        map.put("ip" ,server);

        return  map;
    }



    /**
     * 根据key  host  port  进行操作
     *
     * @param key
     * @param host
     * @param port
     * @return
     * @throws Exception
     */
    public Object get(String key, String host, Integer port) throws Exception {
        //判断某字符串是否为空或长度为0或由空白符(whitespace)
        if (StringUtils.isBlank(key)) {
            return null;
        }
        Object rtn = null;
        Socket socket = new Socket();
        try {
            socket.setTcpNoDelay(true);
            socket.setKeepAlive(true);
            socket.connect(new InetSocketAddress(host, port), timeoutSeconds * 1000);
            socket.setSoTimeout(timeoutSeconds * 1000);
            BufferedInputStream in = new BufferedInputStream(socket.getInputStream());
            BufferedOutputStream out = new BufferedOutputStream(socket.getOutputStream());
            key = encodeKey(key);
            out.write(BYTE_GET);
            out.write(key.getBytes());
            out.write(BYTE_CRLF);
            try {
                out.flush();
            } catch (Exception ex) {

                ex.printStackTrace();
            }
            rtn = getObjectFromStream(in, out);
            socket.close();
            socket=null;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (socket != null) {
                socket.close();
                socket=null;
            }
        }
        return rtn;
    }
    private Object getObjectFromStream(InputStream in, OutputStream out) throws IOException, ClassNotFoundException {
        String cmd = readLine(in);
        if (cmd == null) {
            return null;
        }
        if (cmd.startsWith("VALUE")) {
            String[] part = StringUtils.split(cmd, " ");
            int flag = Integer.parseInt(part[2]);
            int length = Integer.parseInt(part[3]);
            byte[] bs = new byte[length];
            int count = 0;
            while (count < bs.length) {
                count += in.read(bs, count, bs.length - count);
            }
            if (count != bs.length) {
                throw new IOException("length execption");
            }
            readLine(in);
            String endstr = readLine(in);
            if ("END".equals(endstr)) {
                if ((flag & 0x2) != 0) {
                    GZIPInputStream gzi = new GZIPInputStream(new ByteArrayInputStream(bs));
                    ByteArrayOutputStream bos = new ByteArrayOutputStream(bs.length);
                    count = 0;
                    byte[] tmp = new byte['1'];
                    while ((count = gzi.read(tmp)) != -1) {
                        bos.write(tmp, 0, count);
                    }
                    bs = bos.toByteArray();
                    gzi.close();
                }
                return byte2Object(bs);
            }
            throw new IOException("getstream exception");
        }
        return null;
    }

    /**
     *
     * @param in
     * @return
     * @throws IOException
     */
    private String readLine(InputStream in) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        boolean eol = false;
        byte[] b = new byte[1];
        while (in.read(b, 0, 1) != -1) {
            if (b[0] == 13) {
                eol = true;
            } else {
                if ((eol) && (b[0] == 10)) {
                    break;
                }
                eol = false;
            }
            bos.write(b, 0, 1);
        }
        if (bos.size() == 0) {
            return null;
        }
        return bos.toString().trim();
    }

    /**
     * 编码一致
     *
     * @param key
     * @return
     * @throws UnsupportedEncodingException
     */
    private String encodeKey(String key) throws UnsupportedEncodingException {
        return URLEncoder.encode(key, "UTF-8");
    }


    /**
     * 查询value值
     * @param name 配置文件的名字
     * @param key key值
     * @return
     */
    public String getProperty(String name , String key){
        Properties prop  = new Properties();
        String a = null;
        try {
            prop.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("/properties/" + name + ".properties"));
            a = prop.getProperty(key);
        }catch (IOException e){
            e.printStackTrace();
        }
        return a;
    }

    private Object byte2Object(byte[] b) throws IOException, ClassNotFoundException {
        return IOFactory.bytes2object(b);
    }
}

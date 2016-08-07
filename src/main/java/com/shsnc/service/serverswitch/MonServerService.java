package com.shsnc.service.serverswitch;

import com.shsnc.entity.serverswitch.MonServer;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Administrator on 2016/7/29.
 */
@Service("monServerService")
public class MonServerService {

    @Resource(name="sqlSessionTemplate")
    private SqlSessionTemplate sqlSessionTemplate;

    public List<MonServer> findWebServer(){
       List<MonServer> servers= sqlSessionTemplate.selectList("MonServerMapper.findWebServer");
        return servers;
    }
    public List<MonServer> findInterServer(){
        List<MonServer> servers= sqlSessionTemplate.selectList("MonServerMapper.findInterServer");
        return servers;
    }
    public List<MonServer> findAppServer(String serverType){
        List<MonServer> servers= sqlSessionTemplate.selectList("MonServerMapper.findAppServer",serverType);
        return servers;
    }
    public MonServer findServerById(int serverId){
        MonServer servers= sqlSessionTemplate.selectOne("MonServerMapper.findServerByServerId",serverId);
        return servers;
    }
}

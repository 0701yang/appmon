package com.shsnc.service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.shsnc.entity.system.Bean;
import com.shsnc.entity.system.WlscrmThread;
import com.shsnc.util.pager.PageBoundsUtil;
import com.shsnc.util.pager.Pager;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("zcjWlscrmService")
public class ZcjWlscrmService {
    @Resource(name = "sqlSessionTemplate")
    private SqlSessionTemplate sqlSessionTemplate;


    /**
     * 查询Crm
     * @return
     * @throws Exception
     */
    public Pager<Bean> findCrm(String[] string) throws Exception {
        PageBounds pageBounds = PageBoundsUtil.pageBoundsOrderExtend("APP,MODULE,IP,PORT");
        int count = sqlSessionTemplate.selectOne("WlscrmThreadMapper.countcrm",string);
        List<Bean> list = sqlSessionTemplate.selectList("WlscrmThreadMapper.findHistoryCrm", string, pageBounds);

        return new Pager<Bean>(count, list);
    }

    /**
     * 查询Osb
     * @return
     * @throws Exception
     */
    public Pager<Bean> findOsb(String string) throws Exception {
        PageBounds pageBounds = PageBoundsUtil.pageBoundsOrderExtend("APP,MODULE,IP,PORT");
        int count = sqlSessionTemplate.selectOne("WlscrmThreadMapper.countosb",string);
        List<Bean> list = sqlSessionTemplate.selectList("WlscrmThreadMapper.findHistoryOsb", string, pageBounds);

        return new Pager<Bean>(count, list);
    }

    /**
     *  查询历史信息
     * @param ip
     * @param port
     * @return
     * @throws Exception
     */
    public Pager<WlscrmThread> findHistory(String ip , String port) throws Exception{
        PageBounds pageBounds = PageBoundsUtil.pageBoundsOrderExtend("time.asc");
        Map<String , Object> map = new HashMap<String , Object>();
        map.put("ip" , ip);
        map.put("port" , port);
        int count = sqlSessionTemplate.selectOne("WlscrmThreadMapper.countHistory" , map);
        List<WlscrmThread> list = sqlSessionTemplate.selectList("WlscrmThreadMapper.history", map, pageBounds);

        return new Pager<WlscrmThread>(count, list);
   }

    /**
     * 根据按钮查询数据
     * @param map
     * @return
     * @throws Exception
     */
    public Pager<Bean> findByButton(Map<String,String> map ) throws Exception{
        PageBounds pageBounds = PageBoundsUtil.pageBoundsExtend();
        int count = sqlSessionTemplate.selectOne("WlscrmServersMapper.counttotal" , map);
        List<Bean> list = sqlSessionTemplate.selectList("WlscrmServersMapper.findByButton", map,pageBounds);

        return new Pager<Bean>(count, list);
    }

    /**
     * 查询出所有的system
     * @return
     * @throws Exception
     */
    public List<String> findBySystemAll() throws Exception{
        return sqlSessionTemplate.selectList("WlscrmServersMapper.findBySystemAll");
    }

    /**
     * 查询出所有的value
     * @return
     * @throws Exception
     */
    public List<String> findByValueAll(String string) throws Exception{
        return sqlSessionTemplate.selectList("WlscrmServersMapper.findByValueAll",string);
    }

    /**
     * 查询出所有的Name
     * @return
     * @throws Exception
     */
    public List<String> findByNameAll(String string) throws Exception{
        return sqlSessionTemplate.selectList("WlscrmServersMapper.findByNameAll",string);
    }



}

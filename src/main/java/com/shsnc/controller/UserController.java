package com.shsnc.controller;

import com.shsnc.entity.system.Role;
import com.shsnc.entity.system.User;
import com.shsnc.service.RoleService;
import com.shsnc.service.UserService;
import com.shsnc.util.PageData;
import com.shsnc.util.pager.Pager;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 用户界面
 */
@Controller
@RequestMapping(value = "/user")
public class UserController extends BaseController {

    @Resource(name = "userService")
    private UserService userService;
    @Resource(name = "roleService")
    private RoleService roleService;

    /**
     * 主页
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/index")
    public ModelAndView index() throws Exception {
        ModelAndView mv = this.getModelAndView();
        mv.setViewName("main/index");
        return mv;
    }

    /**
     * 查询所有的用户信息
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/list")
    public ModelAndView list() throws Exception {
        ModelAndView mv = this.getModelAndView();
        Pager<User> userList = userService.findUser();

        mv.setViewName("system/user/list");
        mv.addObject("userList", userList);
        return mv;
    }

    /**
     * 添加用户并跳转到添加页面
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/add")
    public ModelAndView add() throws Exception {
        ModelAndView mv = this.getModelAndView();
        PageData pd = this.getPageData();
        Pager<Role> roleList = roleService.findRole();    //准备数据

        mv.setViewName("system/user/add");
        mv.addObject("msg", "save");
        mv.addObject("pd", pd);
        mv.addObject("roleList", roleList);

        return mv;
    }

    /**
     * 保存用户
     * @param
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/save")
    public ModelAndView save() throws Exception{
        ModelAndView mv = this.getModelAndView();
        PageData pd = new PageData();
        pd = this.getPageData();
        User user = new User();
        user.setId(this.get32UUID());
        user.setUsername(pd.getString("username"));
        user.setBz(pd.getString("bz"));
        user.setCreatedate(new Date());
        user.setEmail(pd.getString("email"));
        user.setFullname(pd.getString("fullname"));
        user.setPassword(new SimpleHash("SHA-1" , pd.getString("username"),pd.getString("password")).toString());
        user.setTelephone(pd.getString("telephone"));
        user.setRoleid(pd.getString("roleid"));

        if(userService.findByName(pd).getDatas().size() == 0 ){
//            if(Jurisdiction.buttonJurisdiction(menuUrl, "add")){
//                userService.saveU(pd);
//            } //判断新增权限
            userService.save(user);
            mv.addObject("msg","success");
        }else{
            mv.addObject("msg","failed");
        }
        mv.setViewName("redirect:list");

        return mv;
    }

    /**
     * 删除
     * @param
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/del")
    public ModelAndView del() throws Exception{
        ModelAndView mv = this.getModelAndView();
        PageData pd = new PageData();
        try{
            pd = this.getPageData();
            //if(Jurisdiction.buttonJurisdiction(menuUrl, "del")){userService.deleteU(pd);}
            userService.del(pd.getString("id"));
        } catch(Exception e){
           // logger.error(e.toString(), e);
        }
        mv.setViewName("redirect:list");
        return mv;
    }

    /**
     * 去修改页面
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/toEdit")
    public ModelAndView toEdit() throws Exception{
        ModelAndView mv = this.getModelAndView();
        PageData pd =this.getPageData();

        Pager<Role> roleList = roleService.findRole();	//列出所有二级角色
        Pager<User> user =userService.findById(pd.getString("id"));		//根据ID读取
        pd.put("fullname" , user.getDatas().get(0).getFullname());
        pd.put("email" , user.getDatas().get(0).getEmail());
        pd.put("telephone" , user.getDatas().get(0).getTelephone());
        pd.put("bz" , user.getDatas().get(0).getBz());
        pd.put("username" , user.getDatas().get(0).getUsername());
        pd.put("password" , user.getDatas().get(0).getPassword());
        pd.put("telephone" , user.getDatas().get(0).getTelephone());
        pd.put("roleid" , user.getDatas().get(0).getRoleid());

        mv.setViewName("system/user/add");
        mv.addObject("msg", "edit");
        mv.addObject("pd", pd);
        mv.addObject("roleList", roleList);

        return mv;
    }


    /**
     * 修改
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/edit")
    public ModelAndView edit() throws Exception{
        ModelAndView mv = this.getModelAndView();
        PageData pd = new PageData();
        pd = this.getPageData();
        if(pd.getString("PASSWORD") != null && !"".equals(pd.getString("PASSWORD"))){
            pd.put("PASSWORD", new SimpleHash("SHA-1", pd.getString("USERNAME"), pd.getString("PASSWORD")).toString());
        }
        //if(Jurisdiction.buttonJurisdiction(menuUrl, "edit")){userService.editU(pd);}
        Pager<User> pu = userService.findById(pd.getString("id"));
        userService.edit(pu.getDatas().get(0));
        mv.addObject("msg","success");
        mv.setViewName("redirect:list");
        return mv;

    }





    /*************************************************************************************/
    /**
     * 判断用户是否存在
     * @return
     */
    @RequestMapping(value = "/hasU")
    @ResponseBody
    public Object hasU() {
        Map<String, String> map = new HashMap<String, String>();
        String errInfo = "success";
        PageData pd = new PageData();
        try {
            pd = this.getPageData();
            if (userService.findByName(pd).getDatas().size() != 0) {
                errInfo = "error";
            }
        } catch (Exception e) {
            //logger.error(e.toString(), e);
        }
        map.put("result", errInfo);                //返回结果
        return map;
    }
    /*************************************************************************************/

}

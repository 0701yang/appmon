package com.shsnc.controller;

import com.shsnc.entity.system.Role;
import com.shsnc.entity.system.User;
import com.shsnc.service.RoleService;
import com.shsnc.service.UserService;
import com.shsnc.util.pager.Pager;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
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
        Pager<Role> roleList = roleService.findRole();    //准备数据

        mv.setViewName("system/user/add");
        mv.addObject("msg", "save");

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
    public ModelAndView save(@ModelAttribute("user") User user) throws Exception{
        ModelAndView mv = this.getModelAndView();

        user.setId(this.get32UUID());
        user.setCreatedate(new Date());
        user.setPassword(new SimpleHash("SHA-1" , user.getUsername(),user.getPassword()).toString());

        if(userService.findByName(user.getUsername()).getDatas().size() == 0 ){
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
    public ModelAndView del(@RequestParam("id") String id) throws Exception{
        ModelAndView mv = this.getModelAndView();
        try{
            //if(Jurisdiction.buttonJurisdiction(menuUrl, "del")){userService.deleteU(pd);}
            userService.del(id);
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
    public ModelAndView toEdit(@RequestParam("id") String id) throws Exception{
        ModelAndView mv = this.getModelAndView();

        Pager<Role> roleList = roleService.findRole();	//列出所有二级角色
        Pager<User> userPager =userService.findById(id);		//根据ID读取
        User user = userPager.getDatas().get(0);

        mv.setViewName("system/user/add");
        mv.addObject("msg", "edit");
        mv.addObject("user", user);
        mv.addObject("roleList", roleList);

        return mv;
    }


    /**
     * 修改
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/edit")
    public ModelAndView edit(@ModelAttribute("user") User user) throws Exception{
        ModelAndView mv = this.getModelAndView();

        if (user.getPassword()!=null && !"".equals(user.getPassword())){
            user.setPassword(new SimpleHash("SHA-1",user.getUsername(),user.getPassword()).toString());
        }
        //if(Jurisdiction.buttonJurisdiction(menuUrl, "edit")){userService.editU(pd);}
        userService.edit(user);
        mv.addObject("msg","success");
        mv.setViewName("redirect:list");
        return mv;

    }

    /**
     * 初始化密码
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/initPassword")
    public ModelAndView initPassword(@RequestParam("id") String id) throws Exception{
        ModelAndView mv = this.getModelAndView();
        Pager<User> uid = userService.findById(id);
        User user = uid.getDatas().get(0);
        user.setPassword("111");
        userService.edit(user);
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
    public Object hasU(@RequestParam("username") String username) {
        Map<String, String> map = new HashMap<String, String>();
        String errInfo = "success";
        try {
            if (userService.findByName(username).getDatas().size() != 0) {
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

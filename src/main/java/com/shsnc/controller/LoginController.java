package com.shsnc.controller;

import com.shsnc.entity.system.Role;
import com.shsnc.entity.system.User;
import com.shsnc.service.UserService;
import com.shsnc.util.Const;
import com.shsnc.util.DateUtil;
import com.shsnc.util.Tools;
import com.shsnc.util.pager.Pager;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 登入
 */
@Controller
public class LoginController extends BaseController {
    @Resource(name = "userService")
    private UserService userService;

    /**
     * 访问登录页
     *
     * @return
     */
    @RequestMapping(value = "/login_toLogin")
    public ModelAndView toLogin() throws Exception {
        ModelAndView mv = this.getModelAndView();
        mv.setViewName("system/login/login");
        return mv;
    }

    /**
     * 验证用户
     * @param username
     * @param password
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/login_login")
    @ResponseBody
    public Object login_login(@RequestParam("username")String username ,@RequestParam("password")String password ) throws Exception{
        Map<String,String> map = new HashMap<String,String>();
        User user = new User();
        String errInfo = "";
        if(username != null && password !=null){
            //shiro管理的session
            Subject currentUser = SecurityUtils.getSubject();
            Session session = currentUser.getSession();
            String p = new SimpleHash("SHA-1" , username , password).toString();//密码加密
            user.setPassword(p);
            user.setUsername(username);
            Pager<User> count = userService.findByName(username);//根据用户名查询
           //如果用户存在
            if(count.getDatas().size()==1){
                User u = userService.findByUserNameAndPassword(user);
                if(u!=null){
                    //更新最后登入时间
                    user.setLastdate(new Date());
                    userService.updateLastLogin(user);
                    session.setAttribute("sessionUser" , user);
                    //shiro加入身份验证
                    Subject subject = SecurityUtils.getSubject();
                    UsernamePasswordToken token = new UsernamePasswordToken(username ,password);
                    try {
                        subject.login(token);
                    }catch (AuthenticationException e){
                        errInfo = "身份验证失败!";
                    }
                }else {
                    errInfo="usererror!";//用户名或密码错误
                }

            }else {
                errInfo = "errornouser";//用户不存在
            }
            if(Tools.isEmpty(errInfo)){
                errInfo = "success";					//验证成功
            }
        }else {
            errInfo = "error"; //参数传递错误
        }
        map.put("result", errInfo);
        return map;
    }



}

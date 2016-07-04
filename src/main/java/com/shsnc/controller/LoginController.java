package com.shsnc.controller;

import com.shsnc.entity.system.Privilege;
import com.shsnc.entity.system.RolePrivilegeKey;
import com.shsnc.entity.system.User;
import com.shsnc.service.PrivilegeService;
import com.shsnc.service.UserService;
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
import java.util.*;

/**
 * 登入
 */
@Controller
public class LoginController extends BaseController {
    @Resource(name = "userService")
    private UserService userService;
    @Resource(name = "privilegeService")
    private PrivilegeService privilegeService;

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
     *
     * @param username
     * @param password
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/login_login")
    @ResponseBody
    public Object login_login(@RequestParam("username") String username, @RequestParam("password") String password) throws Exception {
        Map<String, String> map = new HashMap<String, String>();
        User user = new User();
        String errInfo = "";
        if (username != null && password != null) {
            //shiro管理的session
            Subject currentUser = SecurityUtils.getSubject();
            Session session = currentUser.getSession();
            String p = new SimpleHash("SHA-1", username, password).toString();//密码加密
            user.setPassword(p);
            user.setUsername(username);
            Pager<User> count = userService.findByName(username);//根据用户名查询
            //如果用户存在
            if (count.getDatas().size() == 1) {
                User u = userService.findByUserNameAndPassword(user);
                if (u != null) {
                    //更新最后登入时间
                    u.setLastdate(new Date());
                    //获取登录用户的IP
//                    HttpServletRequest request = this.getRequest();
//                    String ip = "";
//                    if (request.getHeader("x-forwarded-for") == null) {
//                        ip = request.getRemoteAddr();
//                    }else{
//                        ip = request.getHeader("x-forwarded-for");
//                    }
//                    u.setIp(ip);
                    u.setIp(session.getHost());
                    userService.edit(u);
                    session.setAttribute("sessionUser", u);
                    //shiro加入身份验证
                    Subject subject = SecurityUtils.getSubject();
                    UsernamePasswordToken token = new UsernamePasswordToken(username, password);
                    try {
                        subject.login(token);
                    } catch (AuthenticationException e) {
                        errInfo = "身份验证失败!";
                    }
                } else {
                    errInfo = "usererror!";//用户名或密码错误
                }

            } else {
                errInfo = "errornouser";//用户不存在
            }
            if (Tools.isEmpty(errInfo)) {
                errInfo = "success";                    //验证成功
            }
        } else {
            errInfo = "error"; //参数传递错误
        }
        map.put("result", errInfo);
        return map;
    }

    /**
     * 访问首页
     * @param
     * @return
     */
    @RequestMapping(value = "/main/index")
    public ModelAndView login_index(){
        ModelAndView mv = this.getModelAndView();
        try{
            //shiro管理的session
            Subject currentUser = SecurityUtils.getSubject();
            Session session = currentUser.getSession();
            User user = (User)session.getAttribute("sessionUser");
            if(null != user){
                User userr = (User)session.getAttribute("userrol");
                if(null == userr){
                    userr = userService.findById(user.getId());
                    session.setAttribute("userrol" , userr);
                }else {
                    user = userr;
                }

                List<Privilege> allprivilegeList = new ArrayList<>();
                List<Map> ll = new ArrayList<>();
                List<Privilege> privilegeList = new ArrayList<>();
                if(null == session.getAttribute("sessionRolePriList")){
                    String roleId = user.getRoleid(); //获取角色信息
                    allprivilegeList = privilegeService.findAll();//查询出所有的权限
                    List<RolePrivilegeKey> userPrivilege = privilegeService.findByRoleId(roleId);//查询出所有Role对应的Priviege的id

                    for(Privilege pp : allprivilegeList){
                        for(RolePrivilegeKey upk : userPrivilege){
                            if(pp.getId().equals(upk.getPrivilegeid())){
                                privilegeList.add(pp);
                            }
                        }
                    }
                    mv.addObject("userpriviegeList",privilegeList);
                    //避免每次拦截用户操作时查询数据库，以下将用户所属角色权限、用户权限限都存入session
                    session.setAttribute("sessionRolePriList", privilegeList); 		//将角色权限List存入session
                }else {
                    allprivilegeList = (List<Privilege>)session.getAttribute("sessionRolePriList");			//菜单权限放入session中
                    mv.addObject("userpriviegeList",allprivilegeList);
                }
                session.setAttribute("username", user.getUsername());	//放入用户名

                mv.setViewName("main/index");
                mv.addObject("user", user);
                mv.addObject("allprivilegeList", allprivilegeList);
            }else {
                mv.setViewName("system/login/login");//session失效后跳转登录页面
            }
        }catch (Exception e){
            mv.setViewName("system/login/login");
        }
        return mv;
    }

    /**
     * 用户注销
     * @return
     */
    @RequestMapping(value="/logout")
    public ModelAndView logout(){
        ModelAndView mv = this.getModelAndView();

        //shiro管理的session
        Subject currentUser = SecurityUtils.getSubject();
        Session session = currentUser.getSession();

        session.removeAttribute("sessionUser");
        session.removeAttribute("sessionRolePriList");
        session.removeAttribute("userrol");
        session.removeAttribute("sessionRolePriList");
        session.removeAttribute("userpriviegeList");

        //shiro销毁登录
        Subject subject = SecurityUtils.getSubject();
        subject.logout();

        mv.setViewName("system/login/login");
        return mv;
    }



}

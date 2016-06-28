package com.shsnc.controller;

import com.shsnc.entity.system.Role;
import com.shsnc.entity.system.User;
import com.shsnc.service.RoleService;
import com.shsnc.util.pager.Pager;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;


/**
 * 角色界面
 */
@Controller
@RequestMapping(value = "/role")
public class RoleController extends BaseController {

    @Resource(name = "roleService")
    private RoleService roleService;

    /**
     * 查询所有的角色信息
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/list")
    public ModelAndView list() throws Exception {
        ModelAndView mv = this.getModelAndView();
        Pager<Role> roleList = roleService.findRole();

        mv.setViewName("system/role/list");
        mv.addObject("roleList", roleList);
        return mv;
    }

    /**
     * 跳转到添加页面
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/add")
    public ModelAndView add() throws Exception {
        ModelAndView mv = this.getModelAndView();
        mv.setViewName("system/role/add");
        mv.addObject("msg", "save");
        return mv;
    }

    /**
     * 添加
     *
     * @param role
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/save")
    public ModelAndView save(@ModelAttribute("role") Role role) throws Exception {
        ModelAndView mv = this.getModelAndView();
        //if(Jurisdiction.buttonJurisdiction(menuUrl, "add")){
//                roleService.save(pd);
//            } //判断新增
        role.setId(this.get32UUID());
        roleService.save(role);
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
            roleService.del(id);
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

        Pager<Role> rolePager =roleService.findById(id);		//根据ID读取
        Role role = rolePager.getDatas().get(0);

        mv.setViewName("system/role/add");
        mv.addObject("msg", "edit");
        mv.addObject("role", role);

        return mv;
    }


    /**
     * 修改
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/edit")
    public ModelAndView edit(@ModelAttribute("role") Role role) throws Exception{
        ModelAndView mv = this.getModelAndView();
        //if(Jurisdiction.buttonJurisdiction(menuUrl, "edit")){userService.editU(pd);}
        roleService.edit(role);
        mv.addObject("msg","success");
        mv.setViewName("redirect:list");
        return mv;

    }




    /*************************************************************************/
    @RequestMapping(value = "/hasR")
    @ResponseBody
    public Object hasR(@RequestParam("name") String name) {
        Map<String, String> maprole = new HashMap<String, String>();
        String errInfo = "success";
        try {
            if (roleService.findByRoleName(name).getDatas().size() != 0) {
                errInfo = "error";
            }
        } catch (Exception e) {
            //logger.error(e.toString(), e);
        }
        maprole.put("result", errInfo);                //返回结果
        return maprole;
    }

}

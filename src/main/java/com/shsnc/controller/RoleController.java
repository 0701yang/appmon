package com.shsnc.controller;

import com.shsnc.entity.system.Privilege;
import com.shsnc.entity.system.Role;
import com.shsnc.entity.system.RolePrivilegeKey;
import com.shsnc.service.PrivilegeService;
import com.shsnc.service.RoleService;
import com.shsnc.util.pager.Pager;
import net.sf.json.JSONArray;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 角色界面
 */
@Controller
@RequestMapping(value = "/role")
public class RoleController extends BaseController {

    @Resource(name = "roleService")
    private RoleService roleService;
    @Resource(name = "privilegeService")
    private PrivilegeService privilegeService;


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
            privilegeService.del(id);
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

    /**
     * 设置权限页面
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/setPrivilege")
    public ModelAndView setPrivilege(@RequestParam("id") String id) throws Exception{
        ModelAndView mv = this.getModelAndView();

        List<Privilege> pp =privilegeService.findAll();//查询出所有的权限信息
        List<RolePrivilegeKey> rpk = privilegeService.findByRoleId(id);//根据role的id查询出r-p表的所有数据
        //根据role的id查询出,role-privilege表的privilege的ID集,根据p的id和所有的p表的id进行比较,相等的checked:true,
        List<Map> ll = new ArrayList<>();
        for(Privilege lp : pp){
            Map<Object,Object> map = new HashMap<>();
            map.put("id", lp.getId());
            map.put("pId", lp.getParentid());
            map.put("name" , lp.getName());
            for(RolePrivilegeKey pk : rpk){
                if(pk.getPrivilegeid().equals(lp.getId())){
                    map.put("checked" , true);
                }
            }
            ll.add(map);
        }
        JSONArray arr = JSONArray.fromObject(ll);
        String json = arr.toString();
        mv.addObject("zTreeNodes",json);
        mv.addObject("roleid",id);
        mv.setViewName("system/role/setPrivilege");
        return mv;
    }

    /**
     *  保存权限
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/saveSetPriv")
    public ModelAndView saveSetPriv(@RequestParam("roleid")String roleid , @RequestParam("menuIds")String menuIds) throws Exception{
        ModelAndView mv = this.getModelAndView();
        privilegeService.del(roleid);
        for (String s : menuIds.split(",")) {
            RolePrivilegeKey r = new RolePrivilegeKey();
            r.setRoleid(roleid);
            r.setPrivilegeid(s);
            privilegeService.save(r);
        }
        mv.setViewName("redirect:/role/list");
        return mv;
    }



    /*************************************************************************/
    @RequestMapping(value = "/hasR")
    @ResponseBody
    public Object hasR(@RequestParam("name") String name) {
        Map<String, String> maprole = new HashMap<>();
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

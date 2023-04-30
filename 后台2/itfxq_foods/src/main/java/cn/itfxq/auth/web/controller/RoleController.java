package cn.itfxq.auth.web.controller;

import cn.itfxq.auth.entity.Permission;
import cn.itfxq.auth.entity.Role;
import cn.itfxq.auth.query.RoleQuery;
import cn.itfxq.auth.serivce.IPermissionService;
import cn.itfxq.auth.serivce.IRoleService;
import cn.itfxq.common.page.PageList;
import cn.itfxq.common.result.ResultResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
public class RoleController {

    @Autowired
    private IRoleService roleService;

    @Autowired
    private IPermissionService permissionService;


    @PreAuthorize("hasRole('管理员')")
    @RequestMapping("/role/index")
    public String index(Model model){
        List<Permission> permisisons = permissionService.findAllPermisisons();
        model.addAttribute("permissions",permisisons);

        //返回角色
        return "views/role/role_list";
    }

    @RequestMapping("/role/listpage")
    @ResponseBody
    public PageList listpage(RoleQuery roleQuery){
        System.out.println("传递参数:"+roleQuery);
        return  roleService.listpage(roleQuery);
    }

    @RequestMapping("/role/addRole")
    @PreAuthorize("hasRole('管理员')")
    @ResponseBody
    public ResultResponse addRole(Role role){
        System.out.println("保存角色...."+role);
        try {
            roleService.saveRole(role);
            return new ResultResponse();
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultResponse("操作失败");
        }
    }

    @RequestMapping("/role/addRolePermission")
    @ResponseBody
    public ResultResponse addRolePermission(@RequestBody Map paramMap){
        ResultResponse ResultResponse = new ResultResponse();
        String roleId = (String)paramMap.get("roleId");
        List permissionIds = (List) paramMap.get("permissionIds");
        try {
            //添加角色对应的权限
            roleService.addRolePermission(roleId,permissionIds);
            return ResultResponse;
        }catch (Exception e){
            e.printStackTrace();
            return new ResultResponse("保存权限失败");
        }

    }

    //添加角色
    @RequestMapping("/role/deleteRole")
    @ResponseBody
    public ResultResponse deleteRole(Long id){
        System.out.println("删除角色...."+id);
        ResultResponse ajaxResult = new ResultResponse();
        try {
            roleService.deleteRole(id);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultResponse("删除失败");
        }
        return ajaxResult;
    }




}

package cn.itfxq.auth.web.controller;

import cn.itfxq.auth.entity.Role;
import cn.itfxq.auth.entity.User;
import cn.itfxq.auth.query.UserQuery;
import cn.itfxq.auth.serivce.IRoleService;
import cn.itfxq.auth.serivce.IUserService;
import cn.itfxq.auth.util.CommonUtils;
import cn.itfxq.common.page.PageList;
import cn.itfxq.common.result.ResultResponse;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @Autowired
    private IRoleService roleService;

    @RequestMapping("/list")
    @ResponseBody
    @PreAuthorize("hasRole('管理员')")
    public List<User> queryAll(){
        return userService.queryAll();
    }


    @GetMapping("/index")
    public String index(String menuid, Model model){
        model.addAttribute("menuid",menuid);
        List<Role> roles = roleService.queryAll();
        model.addAttribute("roles",roles);
        //用户首页
        return "views/user/user_list";
    }
    //个人信息
    @GetMapping("/info")
    public String info(Model model){
        User loginUser = CommonUtils.getLoginUser();
        model.addAttribute("loginUser",loginUser);
        //用户个人
        return "views/user/user_info";
    }

    //修改密码
    @GetMapping("/editpwd")
    public String editpwd(Model model){
        User loginUser = CommonUtils.getLoginUser();
        model.addAttribute("loginUser",loginUser);
        //用户个人
        return "views/user/user_editpwd";
    }

    //修改保存密码
    @PostMapping("/editsavepwd")
    @ResponseBody
    public ResultResponse editsavepwd(User user){
        try {
            userService.updatePwd(user);
            return ResultResponse.ok();
        }catch (Exception e){
            return ResultResponse.fail("修改失败");
        }

    }


    @GetMapping("/listpage")
    @ResponseBody
    public PageList listpage(UserQuery userQuery){
        return  userService.listpage(userQuery);
    }

    //添加用户
    @PostMapping("/addUser")
    @ResponseBody
    public int addUser(User user){
        userService.addUser(user);
        Integer userid = Integer.parseInt(user.getId()+"");
        //添加成功返回用户id
        return userid;
    }

    @PostMapping("/editSaveUser")
    @ResponseBody
    public ResultResponse editSaveUser(User user){
        System.out.println("修改用户...."+user);
        try {
            userService.editSaveUser(user);
            return ResultResponse.ok();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResultResponse.fail("操作失败");
    }

    //删除用户
    @GetMapping("/deleteUser")
    @ResponseBody
    public ResultResponse deleteUser(@RequestParam(required = true) Long id){
        ResultResponse ajaxResult = new ResultResponse();
        try {
            userService.deleteUser(id);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultResponse("删除失败");
        }

        return ajaxResult;
    }


    @PostMapping("/addUserRole")
    @ApiOperation("添加用户角色接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "paramMap", value = "如:{userId:1,[1,2,3,4]]}")
    })
    @ResponseBody
    public ResultResponse addUserRole(@RequestBody Map paramMap){
        ResultResponse ResultResponse = new ResultResponse();
        String userId = (String)paramMap.get("userId");
        List roleIds = (List) paramMap.get("roleIds");
        System.out.println(userId);
        System.out.println(roleIds);
        try {
            //添加用户对应的角色
            roleService.addUserRole(userId,roleIds);
            return ResultResponse;
        }catch (Exception e){
            e.printStackTrace();
            return new ResultResponse("保存角色失败");
        }

    }



    //注册用户
    @RequestMapping("/regSaveUser")
    @ResponseBody
    public Long addTeacher(User user){
        System.out.println("保存用户...."+user);
        userService.addUser(user);
        Long userId = user.getId();
        return userId;
    }

    @PostMapping(value="/user/deleteBatchUser")
    @ApiOperation("批量用户接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", value = "如:88,89,99")
    })
    @PreAuthorize("hasRole('管理员')")
    @ResponseBody
    public ResultResponse deleteBatchUser(String ids){
        String[] idsArr = ids.split(",");
        List list = new ArrayList();
        for(int i=0;i<idsArr.length;i++){
            list.add(idsArr[i]);
        }
        try{
            userService.batchRemove(list);
            return new ResultResponse();
        }catch(Exception e){
            return new ResultResponse("批量删除失败");
        }
    }



}

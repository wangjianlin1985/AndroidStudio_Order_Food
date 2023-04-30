package cn.itfxq.auth.web.controller.front;

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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/frontuser")
public class UserFrontController {

    @Autowired
    private IUserService userService;



    @RequestMapping("/login")
    @ResponseBody
    public ResultResponse frontLogin(User user){
        User userdb = userService.findUserByUserName(user.getUsername());
        if(userdb == null){
            return ResultResponse.fail("用户不存在");
        }
        //如果存在比较密码
        BCryptPasswordEncoder bcEncoder = new BCryptPasswordEncoder();

        if(!bcEncoder.matches(user.getPassword(),userdb.getPassword())){
            return ResultResponse.fail("密码错误");
        }

        return ResultResponse.ok().put("user",userdb);
    }

    @RequestMapping("/reg")
    @ResponseBody
    public ResultResponse reg(User user){
        User userdb = userService.findUserByUserName(user.getUsername());
        if(userdb != null){
            return ResultResponse.fail("用户已存在!");
        }
        try {
            userService.addUser(user);
            return ResultResponse.ok();
        }catch (Exception e){
            return ResultResponse.fail("注册失败!");
        }


    }






}

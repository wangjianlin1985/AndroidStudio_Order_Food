package cn.itfxq.auth.web.controller;

import cn.itfxq.auth.entity.Menu;
import cn.itfxq.auth.serivce.IMenuService;
import cn.itfxq.common.result.ResultResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class MenuController {

    @Autowired
    private IMenuService menuService;


    @RequestMapping("/menu/index")
    public String index(Model model){
        List<Menu> menus = menuService.queryAllMenu();
        model.addAttribute("menus",menus);
        //返回菜单页面
        return "views/menu/menu_list";
    }

    @PreAuthorize("hasRole('管理员')")
    @RequestMapping("/menu/addTopMenu")
    @ResponseBody
    public ResultResponse addTopMenu(@RequestBody Menu menu){
        ResultResponse mzResult = new ResultResponse();
        try{
            menuService.addTopMenu(menu);
            return mzResult;
        }catch(Exception e){
            e.printStackTrace();
            return new ResultResponse("保存失败");
        }
    }

    @PreAuthorize("hasRole('管理员')")
    @RequestMapping("/menu/addSubMenu")
    @ResponseBody
    public ResultResponse addSubMenu(@RequestBody Menu menu){
        ResultResponse mzResult = new ResultResponse();
        try{
            menuService.addSubMenu(menu);
            return mzResult;
        }catch(Exception e){
            e.printStackTrace();
            return new ResultResponse("保存失败");
        }
    }

    @PreAuthorize("hasRole('管理员')")
    @RequestMapping("/menu/deleteMenu")
    @ResponseBody
    public ResultResponse deleteMenu(Long id){
        ResultResponse ajaxResult = new ResultResponse();
        try{
            //删除菜单 通过id删除
            menuService.deleteMenuById(id);
            return ajaxResult;
        }catch(Exception e){
            e.printStackTrace();
            return new ResultResponse("保存失败");
        }
    }

    @PreAuthorize("hasRole('管理员')")
    @RequestMapping("/menu/editMenu")
    @ResponseBody
    public ResultResponse editMenu(@RequestBody Menu menu){
        ResultResponse mzResult = new ResultResponse();
        try{
            menuService.editMenu(menu);
            return mzResult;
        }catch(Exception e){
            e.printStackTrace();
            return new ResultResponse("保存失败");
        }
    }
}

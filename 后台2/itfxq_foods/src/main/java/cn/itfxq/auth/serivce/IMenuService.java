package cn.itfxq.auth.serivce;




import cn.itfxq.auth.entity.Menu;
import cn.itfxq.common.mapper.BaseMapper;
import cn.itfxq.common.service.IBaseService;

import java.util.List;

public interface IMenuService extends IBaseService<Menu> {
    //查询所有的用户
    List<Menu> findAll(Long loginUserId);
    //查询所有的菜单
    List<Menu> queryAllMenu();
    /**
     * 保存一级菜单
     * @param menu
     */
    void addTopMenu(Menu menu);
    /**
     * 保存子菜单
     * @param menu
     */
    void addSubMenu(Menu menu);
    /**
     * 删除菜单
     * @param id
     */
    void deleteMenuById(Long id);
    /**
     * 修改菜单
     * @param menu
     */
    void editMenu(Menu menu);
}

package cn.itfxq.auth.mapper;

import cn.itfxq.auth.entity.Menu;
import cn.itfxq.common.mapper.BaseMapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface MenuMapper extends BaseMapper<Menu> {
    /**
     * 根据登录用户 查询菜单
     * @param loginUserid
     * @return
     */
    List<Menu> findAll(Long loginUserid);

    /**
     * 查询所有的菜单
     * @return
     */
    List<Menu> queryAllMenu();

    /**
     * 保存一级菜单
     * @param menu
     */
    void addTopMenu(Menu menu);

    //保存子菜单
    void addSubMenu(Menu menu);


    @Delete("delete from t_menu where id=#{id} or pid=#{id}")
    void deleteMenuById(Long id);


    void editMenu(Menu menu);
}

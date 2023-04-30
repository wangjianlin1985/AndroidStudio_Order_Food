package cn.itfxq.auth.mapper;

import cn.itfxq.auth.entity.Menu;
import cn.itfxq.auth.entity.Permission;
import cn.itfxq.common.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;

import java.util.List;


@Mapper
public interface PermissionMapper extends BaseMapper<Permission> {

    List<Permission> listPermissionByUserId(Long userid);

    /*
     * 添加页面权限
     */
    void addMenuPermission(Permission permission);

    /**
     * 查询所有的权限
     */
    @Select("select * from t_permission")
    List<Permission> findAllPermisisons();

    @Insert("insert into t_permission (name,title,pid) values(#{name},#{title},#{pid})")
    void addBtnPermission(Permission permission);

    /**
     * 根据权限id 删除权限表数据
     * @param id
     */
    @Delete("delete from t_permission where id=#{id}")
    void deletePermisisonByPid(Long id);

    /**
     * 根据权限id 删除权限中间表数据
     * @param id
     */
    @Delete("delete from t_role_permission where permissionid=#{id}")
    void deleteRolePermisisonByPid(Long id);

    /**
     * 修改页面权限
     * insert into t_permission (name,title,pid,menuid)
     * 		values(#{name},#{title},#{pid},#{menuid})
     */
    @Update("update t_permission set name=#{name},title=#{title} where id=#{id}")
    void editBtnPermisison(Permission permission);
}

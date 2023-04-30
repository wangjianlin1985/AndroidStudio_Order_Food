package cn.itfxq.auth.mapper;

import cn.itfxq.auth.entity.Menu;
import cn.itfxq.auth.entity.Role;
import cn.itfxq.auth.query.RoleQuery;
import cn.itfxq.common.mapper.BaseMapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;


@Mapper
public interface RoleMapper extends BaseMapper<Role> {
    /**
     * 通过用户id查询角色
     * @param userid
     * @return
     */
    List<Role> listRoleByUserId(Long userid);


    @Insert("insert into t_role(name,sn,`desc`) values ('${name}','${sn}',trim('<![CDATA[${desc}]]>'))")
    void saveRole(Role role);

    @Delete("delete from t_role_permission  where roleid = #{roleid}")
    void deleteRolePermission(long parseLong);

    //添加角色对应的权限
    void addRolePermission(List rolePermissionList);

    @Delete("delete from t_role where id=#{id}")
    void deleteRole(Long id);


    //删除用户角色
    @Delete("delete from t_user_role where userid = #{userId}")
    void deleteUserRole(Long userId);

    //添加用户角色
    void addUserRole(List userRoles);
}

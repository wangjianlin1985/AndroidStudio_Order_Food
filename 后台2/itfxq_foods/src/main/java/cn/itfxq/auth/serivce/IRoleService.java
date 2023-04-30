package cn.itfxq.auth.serivce;




import cn.itfxq.auth.entity.Menu;
import cn.itfxq.auth.entity.Role;

import cn.itfxq.common.service.IBaseService;

import java.util.List;

public interface IRoleService extends IBaseService<Role> {
    //根据用户id查询角色
    List<Role> listRoleByUserId(Long userid);


    void saveRole(Role role);

    //添加角色对应的权限
    void addRolePermission(String roleId, List permissionIds);

    void deleteRole(Long id);


    //添加角色
    void addUserRole(String userId, List roleIds);
}

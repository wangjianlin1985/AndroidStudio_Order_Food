package cn.itfxq.auth.serivce.impl;


import cn.itfxq.auth.entity.Permission;
import cn.itfxq.auth.mapper.PermissionMapper;
import cn.itfxq.auth.serivce.IPermissionService;
import cn.itfxq.common.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("permissionService")
@Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
public class PermissionServiceImpl extends BaseServiceImpl<Permission> implements IPermissionService {

    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public List<Permission> listPermissionByUserId(Long userid) {
        return permissionMapper.listPermissionByUserId(userid);
    }

    //查找所有的权限
    @Override
    public List<Permission> findAllPermisisons(){
        return permissionMapper.findAllPermisisons();
    }

    @Override
    public void addBtnPermisison(Permission permission) {
        permissionMapper.addBtnPermission(permission);
    }

    /**
     *    删除权限
     */
    @Override
    @Transactional(rollbackFor=RuntimeException.class)
    public void deletePermisisonByPid(Long id) {
        try {
            //删除中间表数据
            permissionMapper.deleteRolePermisisonByPid(id);
            //删除权限表数据
            permissionMapper.deletePermisisonByPid(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void editBtnPermisison(Permission permission) {
        permissionMapper.editBtnPermisison(permission);
    }

}

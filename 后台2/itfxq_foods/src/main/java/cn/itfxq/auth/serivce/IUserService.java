package cn.itfxq.auth.serivce;

import cn.itfxq.auth.entity.User;
import cn.itfxq.common.service.IBaseService;

import java.util.List;


public interface IUserService extends IBaseService<User> {
    /**
     *  根据用户名取到用户
     */

    User findUserByUserName(String username);

    /**
     *     添加用户
     */
    void addUser(User user);
    /**
     * 更新用户头像
     */
    void updateUserHeadImg(User user);

    /**
     * 修改用户
     */
    void editSaveUser(User user);
    /**
     * 删除用户
     */
    void deleteUser(Long id);
    /**
     * 批量删除
     */
    void batchRemove(List list);
    /**
     * 学生选课
     */
    void editSaveXk(User user);

    /**
     * 修改密码
     * @param user
     */
    void updatePwd(User user);


}

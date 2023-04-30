package cn.itfxq.auth.mapper;

import cn.itfxq.auth.entity.User;
import cn.itfxq.auth.query.UserQuery;
import cn.itfxq.common.mapper.BaseMapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;


@Mapper
public interface UserMapper extends BaseMapper<User> {
    @Select("select * from t_user where username=#{username}")
    User findUserByUserName(String username);


    //条件用户
    void addUser(User user);
    //更新用户头像
    void updateUserHeadImg(User user);

    //修改保存用户
    void editSaveUser(User user);

    @Delete("delete from t_user where id=#{id}")
    void deleteUser(Long id);

    @Delete("<script>"  +
            "delete from t_user where id in " +
            "<foreach collection='list' item='id' open='(' separator=',' close=')'> " +
            "#{id}" +
            "</foreach>" +
            "</script>")
    void batchRemove(List list);


    //保存选课信息
    @Update("update t_user set courseid=#{courseid} where id =#{id}")
    void editSaveXk(User user);

    //修改密码
    @Update("update t_user set password=#{password} where id=#{id}")
    void updatePwd(User user);
}

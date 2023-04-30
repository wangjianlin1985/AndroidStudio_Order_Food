package cn.itfxq.auth.config;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Set;

//登录用户封装
public class UserSecurity extends User {
    cn.itfxq.auth.entity.User loginUser;
    public UserSecurity(cn.itfxq.auth.entity.User user, Set<GrantedAuthority> authorities) {
        super(user.getUsername(), user.getPassword(),true,true,true,true, authorities);
        this.loginUser = user;
    }
    public cn.itfxq.auth.entity.User getLoginUser() {
        return loginUser;
    }
    public void setLoginUser(cn.itfxq.auth.entity.User loginUser) {
        this.loginUser = loginUser;
    }
}

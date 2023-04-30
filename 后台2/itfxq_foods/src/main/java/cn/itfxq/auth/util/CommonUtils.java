package cn.itfxq.auth.util;

import cn.itfxq.auth.config.UserSecurity;
import cn.itfxq.auth.entity.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class CommonUtils {
    //获取当前登录用户
  public static User getLoginUser(){
      Authentication auth = SecurityContextHolder.getContext().getAuthentication();
      UserSecurity userSecurity = (auth != null) ? (UserSecurity) auth.getPrincipal() : null;
      return userSecurity.getLoginUser();
  }
}
package cn.itfxq.auth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)//开启细粒度控制
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    AuthenctiationSuccessHandler myAuthenctiationSuccessHandler;

    @Autowired
    AuthenctiationFailHandler myAuthenctiationFailHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //请求的规则
        http.authorizeRequests()
                .antMatchers(
                        "/static/upload/**",
                        "/static/upload/food/**",
                        "/static/**",//资源放行
                        "/goRegPage",//注册页面
                        "/user/regSaveUser",//注册保存用户
                        "/file/uploadFile",//文件上传,
                        "/frontfood/all",
                        "/frontuser/login",
                        "/frontuser/reg",
                        "/frontorder/add",
                        "/frontorder/queryOrderByUsername",
                        "/food/showimg/**",
                        "/toLogin")//后台登录
                .permitAll()//所有人都能访问
                .anyRequest().authenticated();
        //开启登录页面
        http.formLogin().loginPage("/toLogin") //登录页
                .usernameParameter("username").passwordParameter("password")//设置表单用户名和密码
                .loginProcessingUrl("/login")//登录表单请求
                .successHandler(myAuthenctiationSuccessHandler)//登录成功
                .failureHandler(myAuthenctiationFailHandler);//登录失败

        http.csrf().disable();//关闭csrf
        http.logout().logoutSuccessUrl("/toLogin")
                .invalidateHttpSession(true);//注销成功去首页

        //权限不够返回处理
        http.exceptionHandling().accessDeniedHandler(new AccessDeniedHandler() {
            @Override
            public void handle(HttpServletRequest req, HttpServletResponse resp,
                               AccessDeniedException e) throws IOException, ServletException {
                String type = req.getHeader("X-Requested-With");
                if("XMLHttpRequest".equals(type)){
                    resp.getWriter().print("403");
                }else{
                    req.getRequestDispatcher("/error403").forward(req,resp);
                }
            }
        });

        //开启记住我 cookie的实现
        http.rememberMe().rememberMeParameter("remember");
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        //基于数据库方式登录
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(new BCryptPasswordEncoder());

    }
}

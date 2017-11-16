package com.cheny.demo.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author ChenYu
 */
@Controller
@RequestMapping("userAuth")
public class ShiroLoginController {

    @RequestMapping("login")
    public String login(String username, String password) {
        //获取当前的Subject  
        Subject currentUser = SecurityUtils.getSubject();
        //测试当前用户是否已经被认证(即是否已经登录)  
        if (!currentUser.isAuthenticated()) {
            //将用户名与密码封装为UsernamePasswordToken对象  
            UsernamePasswordToken token = new UsernamePasswordToken(username, password);
            //记录用户
            token.setRememberMe(true);
            try {
                //调用Subject的login方法执行登录
                currentUser.login(token);
            } catch (AuthenticationException e) {//所有认证时异常的父类
                System.out.println("登录失败：" + e.getMessage());
            }
        }
        return "redirect:/list.jsp";
    }
}  
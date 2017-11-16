package com.cheny.demo.config;

import com.cheny.demo.entity.User;
import org.apache.shiro.authc.*;
import org.apache.shiro.realm.AuthenticatingRealm;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ChenYu
 */
public class ShiroRealm extends AuthenticatingRealm {

    private static Map<String, User> userMap = new HashMap<String, User>();

    static {
        //使用Map模拟数据库获取User表信息
        userMap.put("jack", new User("jack", "e97ae203e788e10b215184a0ea10ebf7", false));
        userMap.put("tom", new User("tom", "bbb321", false));
        userMap.put("jean", new User("jean", "ccc213", true));
    }

    /*
    *   下面的验证的主要核心步骤总结起来为3步：
    *   1)把AuthenticationToken转换为UsernamePasswordToken
    *   2)从UsernamePasswordToken中获取username
    *   3)预校验(账户存在性，锁定等业务校验)后，将账户信息封装至AuthenticationInfo的某种实现类，返回出去。
    *
    * */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        //1.把AuthenticationToken转换为UsernamePasswordToken
        UsernamePasswordToken userToken = (UsernamePasswordToken) authenticationToken;

        //2.从UsernamePasswordToken中获取username
        String username = userToken.getUsername();

        //3.调用数据库的方法，从数据库中查询Username对应的用户记录
        System.out.println("从数据库中获取UserName为" + username + "所对应的信息。");
        //Map模拟数据库取数据
        User u = userMap.get(username);

        //4.若用户不行存在，可以抛出UnknownAccountException
        if (u == null) {
            throw new UnknownAccountException("用户不存在");
        }

        //5.若用户被锁定，可以抛出LockedAccountException
        if (u.isLocked()) {
            throw new LockedAccountException("用户被锁定");
        }

        //6.根据用户的情况，来构建AuthenticationInfo对象,通常使用的实现类为SimpleAuthenticationInfo
        //以下信息是从数据库中获取的
        //1)principal：认证的实体信息，可以是username，也可以是数据库表对应的用户的实体对象
        Object principal = u.getUsername();
        //2)credentials：密码
        Object credentials = u.getPassword();
        //3)realmName：当前realm对象的name，调用父类的getName()方法即可
        String realmName = getName();

        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(principal, credentials, realmName);

        return info;
    }
}
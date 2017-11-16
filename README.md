Filter配置
--
```
<bean id="shiroFilter" class="org.apache.shiro.spring.web.ShiroFilterFactoryBean">  
     <property name="securityManager" ref="securityManager"/>  
     <property name="loginUrl" value="/login.jsp"/>  
     <property name="successUrl" value="/list.jsp"/>  
     <property name="unauthorizedUrl" value="/unauthorized.jsp"/>  
     <!--    
        配置哪些页面需要受保护.   
        以及访问这些页面需要的权限.   
        1). anon 可以被匿名访问  
        2). authc 必须认证(即登录)后才可能访问的页面.   
     -->  
     <property name="filterChainDefinitions">  
         <value>  
             /login.jsp = anon  
             # everything else requires authentication:  
             /** = authc  
         </value>  
     </property>  
</bean>  
```

```
(1)url部分的配置
    在配置拦截参数时，格式是“url=拦截器[参数]，拦截器[参数]”，如/login.jsp=“”。这样如果当前请求的url匹配[urls]部分的某个url模式，将会执行其配置的拦截器。
    常用的拦截器配置：
    anon(anonymous)拦截器表示匿名访问(既不需要登录即可访问)。
    authc(authentication)拦截器表示需要身份认证过后才能访问。

(2)url模式匹配
    配置的url模式使用Ant风格模式。Ant路径通过通配符支持“?”、“*”、“**”，其中通配符匹配不包括目录分隔符“/”。
    对于“?”，其匹配一个字符串。如“/admin?”将匹配“admin1”，但不匹配“/admin”或“/admin/”。
    对于“*”，其匹配零个或多个字符串。如“/admin*”将匹配“/admin”、“/admin123”，但不匹配“/admin1”。
    对于“**”，其匹配路径中的零个或多个路径。如“/admin/**”将匹配“/admin/a”或“/admin/a/b”。

(3)url匹配顺序
    url权限采取第一次匹配优先的方式，即从头开始使用第一个匹配的url模式对应的拦截器链。
    如下面的配置：
    /bb/**=filter1
    /bb/aa=filter2
    /**=filter3
```
![shiro工作原理](http://img.blog.csdn.net/20171022185933365?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvYWNtbWFu/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/Center)
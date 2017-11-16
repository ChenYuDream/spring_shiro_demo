package com.cheny.demo.utils;

import org.apache.shiro.crypto.hash.SimpleHash;

/**
 * Md5工具类
 *
 * @author yu_chen
 * @create 2017-11-16 18:36
 **/
public class Md5Util {

    public static void main(String[] args) {
        //加密方式
        String hashAlgorithmName = "MD5";
        //密码原值
        Object crdentials = "aaa123";
        //盐值
        Object salt = null;
        //加密1024次
        int hashIterations = 1024;
        Object result = new SimpleHash(hashAlgorithmName,crdentials,salt,hashIterations);
        System.out.println(result);
    }
}

package com.cheny.demo.entity;

import lombok.Data;

/**
 * @author ChenYu
 */
@Data
public class User {


    private String username;
    private String password;
    private boolean locked;

    public User(String username, String password, boolean locked) {
        super();
        this.username = username;
        this.password = password;
        this.locked = locked;
    }

}
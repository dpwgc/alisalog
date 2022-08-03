package com.dpwgc.console.model;

import com.baomidou.mybatisplus.annotation.TableName;

/**
 * 控制台用户
 */
@TableName("user")
public class User {
    private String username;
    private String password;
    private Integer authLevel;
    private Integer status;
    private String createTime;
    private String updateTime;
}

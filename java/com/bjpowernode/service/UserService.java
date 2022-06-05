package com.bjpowernode.service;

import com.bjpowernode.pojo.Users;

import java.util.List;

public interface UserService {
    //显示全部用户
    List<Users> getAll();

    //增加用户
    int save(Users users);

    //单个用户删除
    int delete(int uid);

    //完成注册
    Users regist(Users users);

    //完成登录判断
    Users login(String name, String pwd);

    //获取用户的信息
    Users information(int uid);

    //用户信息更改
    int update(Users users);
}

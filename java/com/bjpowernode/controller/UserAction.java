package com.bjpowernode.controller;


import com.bjpowernode.pojo.Users;
import com.bjpowernode.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/users")
public class UserAction {
    //每页显示的记录数
    public static final int PAGE_SIZE = 5;

    @Autowired
    UserService userService;

    Users users;

    //显示全部用户
    @RequestMapping("/getAll")
    public String getAll(HttpServletRequest request) {
        List<Users> usersList = userService.getAll();
        request.setAttribute("usersList", usersList);
        return "users";
    }

    @RequestMapping("/information")
    public String information(int uid,HttpServletRequest request){
        users = userService.information(uid);
        if(users != null){
            request.setAttribute("usersinformation",users);
        }
        return "usersindividual";
    }

    @RequestMapping("/save")
    public String save(Users users, HttpServletRequest request) {
        int num = -1;
        try {
            num = userService.save(users);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (num > 0) {
            request.setAttribute("msg", "增加成功!");
        } else {
            request.setAttribute("msg", "增加失败!");
        }
        //增加成功后应该重新访问数据库,所以跳转到分页显示的action上
        return "forward:/users/getAll.action";
    }


    @RequestMapping("/delete")
    public String delete(int uid,HttpServletRequest request) {
        int num = -1;

        try {
            num = userService.delete(uid);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (num > 0) {
            request.setAttribute("msg", "删除成功!");
        } else {
            request.setAttribute("msg", "删除失败!");
        }

        //删除结束后跳到分页显示
        return "forward:/users/getAll.action";
    }

    //实现注册，并进行相应的跳转
    @RequestMapping("/regist")
    public String regist(String name,String pwd,String gender,int age,String email,HttpServletRequest request){
        users.setUname(name);
        users.setUpass(pwd);
        users.setGender(gender);
        users.setAge(age);
        users.setEmail(email);

        Users regist = userService.regist(users);
        if(regist!=null){
            /*request.setAttribute("regist",regist);*/
            //注册成功
            return "login";
        }
        else {
            //注册失败
            request.setAttribute("errmsg","用户已存在，请重新输入!");
            return "regist";
        }
    }


    //用户信息更新
    @RequestMapping("/update")
    public String  update(String uname,String upass,String gender,int age,String email,HttpServletRequest request){
        users.setUname(uname);
        users.setUpass(upass);
        users.setGender(gender);
        users.setAge(age);
        users.setEmail(email);

        int i = userService.update(users);
        if (i>0){
            request.setAttribute("msg","修改成功");
        }
        else {
            request.setAttribute("msg","修改失败");
        }
        return "forward:/users/information.action?uid="+users.getUid();
    }
}

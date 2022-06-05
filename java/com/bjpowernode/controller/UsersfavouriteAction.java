package com.bjpowernode.controller;

import com.bjpowernode.service.UsersfavouriteService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/favourite")
public class UsersfavouriteAction {
    //每页显示的记录数
    public static final int PAGE_SIZE = 5;

    public int id;

    @Autowired
    UsersfavouriteService usersfavouriteService;

    //显示第1页的5条记录
    @RequestMapping("/split")
    public String split(int ufid,HttpServletRequest request) {
        PageInfo info = null;
        id=ufid;
        //得到第1页的数据
        info = usersfavouriteService.splitPage(id,1, PAGE_SIZE);
        request.setAttribute("info", info);
        return "usersfavourite";
    }

    //ajax分页翻页处理
    @ResponseBody
    @RequestMapping("/ajaxSplit")
    public void ajaxSplit(int page, HttpSession session) {
        //取得当前page参数的页面的数据
        PageInfo info = usersfavouriteService.splitPageVo(id,page,PAGE_SIZE);
        session.setAttribute("info", info);
    }

    @RequestMapping("/save")
    public String save(int ufpid,HttpServletRequest request) {
        int num = -1;
        try {
            num = usersfavouriteService.save(id,ufpid);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (num > 0) {
            request.setAttribute("msg", "增加成功!");
        }else if(num==0){
            request.setAttribute("msg", "您已经收藏过了~");
        }
        else {
            request.setAttribute("msg", "增加失败!");
        }
        //增加成功后应该重新访问数据库,所以跳转到分页显示的action上
        return "buyproduct";
    }

    @RequestMapping("/outfavourite")
    public String outfavourite(int ufpid,HttpServletRequest request) {
        int num = -1;

        try {
            num = usersfavouriteService.outFavourite(ufpid);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (num > 0) {
            request.setAttribute("msg", "删除成功!");
        } else {
            request.setAttribute("msg", "删除失败!");
        }

        //删除结束后跳到分页显示
        return "forward:/favourite/deleteAjaxSplit.action";
    }

    @ResponseBody
    @RequestMapping(value = "/deleteAjaxSplit", produces = "text/html;charset=UTF-8")
    public String deleteAjaxSplit(HttpServletRequest request) {
        //取得第1页的数据
        PageInfo info = null;
        info = usersfavouriteService.splitPage(id,1, PAGE_SIZE);
        request.getSession().setAttribute("info",info);
        request.getAttribute("msg");
        return "forward:/favourite/split.action";
    }
}

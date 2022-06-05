package com.bjpowernode.controller;

import com.bjpowernode.pojo.Announcement;
import com.bjpowernode.pojo.vo.ProductInfoVo;
import com.bjpowernode.service.AnnouncementService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/anno")
public class AnnouncementAction {

    @Autowired
    AnnouncementService announcementService;

    //每页显示的记录数
    public static final int PAGE_SIZE = 5;

    //显示全部公告不分页
    @RequestMapping("/getAll")
    public String getAll(HttpServletRequest request) {
        List<Announcement> list = announcementService.getAll();
        request.setAttribute("list", list);
        return "announcement";
    }

    //显示第1页的5条记录
    @RequestMapping("/split")
    public String split(HttpServletRequest request) {
        PageInfo info = null;
        //得到第1页的数据
        info = announcementService.splitPage(1, PAGE_SIZE);
        request.setAttribute("info", info);
        return "announcement";
    }

    //用户端公告展示
    @RequestMapping("/usersannosplit")
    public String usersannosplit(HttpServletRequest request) {
        PageInfo info = null;
        //得到第1页的数据
        info = announcementService.splitPage(1, PAGE_SIZE);
        request.setAttribute("info", info);
        return "usersannouncement";
    }


    @RequestMapping("/save")
    public String save(Announcement announcement, HttpServletRequest request) {
        int num = -1;
        try {
            num = announcementService.save(announcement);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (num > 0) {
            request.setAttribute("msg", "增加成功!");
        } else {
            request.setAttribute("msg", "增加失败!");
        }
        //增加成功后应该重新访问数据库,所以跳转到分页显示的action上
        return "forward:/anno/split.action";
    }


    @RequestMapping("/delete")
    public String delete(int id,HttpServletRequest request) {
        int num = -1;

        try {
            num = announcementService.delete(id);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (num > 0) {
            request.setAttribute("msg", "删除成功!");
        } else {
            request.setAttribute("msg", "删除失败!");
        }

        //删除结束后跳到分页显示
        return "forward:/prod/deleteAjaxSplit.action";
    }

    //ajax分页翻页处理
    @ResponseBody
    @RequestMapping("/ajaxSplit")
    public void ajaxSplit(ProductInfoVo vo, HttpSession session) {
        //取得当前page参数的页面的数据
        PageInfo info = announcementService.splitPageVo(vo,PAGE_SIZE);
        session.setAttribute("info", info);
    }

    @ResponseBody
    @RequestMapping(value = "/deleteAjaxSplit", produces = "text/html;charset=UTF-8")
    public Object deleteAjaxSplit(HttpServletRequest request) {
        //取得第1页的数据
        PageInfo info = null;
        info = announcementService.splitPage(1, PAGE_SIZE);
        request.getSession().setAttribute("info",info);
        return request.getAttribute("msg");
    }

    //批量删除商品
    @RequestMapping("/deleteBatch")
    //ids="1,4,5"  ps[1,4,5]
    public String deleteBatch(String ids,HttpServletRequest request){
        //将上传上来的字符串截开,形成商品id的字符数组
        String []ps = ids.split(",");

        try {
            int num = announcementService.deleteBatch(ps);
            if(num > 0 ){
                request.setAttribute("msg","批量删除成功!");
            }else{
                request.setAttribute("msg","批量删除失败!");
            }
        } catch (Exception e) {
            request.setAttribute("msg","商品不可删除!");
        }
        return "forward:/anno/deleteAjaxSplit.action";
    }

}

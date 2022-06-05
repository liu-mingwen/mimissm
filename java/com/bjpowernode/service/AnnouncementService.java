package com.bjpowernode.service;

import com.bjpowernode.pojo.Announcement;
import com.bjpowernode.pojo.vo.ProductInfoVo;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface AnnouncementService {
    //通知公告的查找
    List<Announcement> getAll();

    //分页功能实现
    PageInfo splitPage(int pageNum, int pageSize);

    //通知公告的添加
    int save(Announcement announcement);

    //单个公告删除
    int delete(int id);

    //批量删除公告
    int deleteBatch(String []ids);

    //分页
    PageInfo splitPageVo(ProductInfoVo vo, int pageSize);
}

package com.bjpowernode.service;

import com.bjpowernode.pojo.ProductInfo;
import com.github.pagehelper.PageInfo;

public interface UsersfavouriteService {
    //加入收藏
    int save(int ufid,int ufpid);

    //取消收藏
    int outFavourite(int ufpid);

    //分页功能实现
    PageInfo splitPage(int ufid,int pageNum, int pageSize);

    //按主键id查询商品
    ProductInfo getByID(int ufpid);

    //分页
    PageInfo splitPageVo(int ufid,int page, int pageSize);
}

package com.bjpowernode.service.impl;

import com.bjpowernode.mapper.ProductInfoMapper;
import com.bjpowernode.mapper.UsersfavouriteMapper;
import com.bjpowernode.pojo.ProductInfo;
import com.bjpowernode.pojo.Usersfavourite;
import com.bjpowernode.pojo.UsersfavouriteExample;
import com.bjpowernode.service.UsersfavouriteService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersfavouriteServiceImpl implements UsersfavouriteService {

    @Autowired
    UsersfavouriteMapper usersfavouriteMapper;

    @Autowired
    ProductInfoMapper productInfoMapper;



    @Override
    public int save(int ufid, int ufpid) {
        int i;
        //如果已经添加过
        UsersfavouriteExample example = new UsersfavouriteExample();
        example.createCriteria().andUfidEqualTo(ufid);
        example.createCriteria().andUfidEqualTo(ufpid);
        List<Usersfavourite> list = usersfavouriteMapper.selectByExample(example);
        if(list.size()>0){
            i=0;
            return i;
        }

        Usersfavourite usersfavourite = new Usersfavourite();
        usersfavourite.setUfid(ufid);
        usersfavourite.setUfpid(ufpid);
        ProductInfo productInfo = productInfoMapper.selectByPrimaryKey(ufpid);
        usersfavourite.setpName(productInfo.getpName());
        usersfavourite.setpContent(productInfo.getpContent());
        usersfavourite.setpImage(productInfo.getpImage());
        usersfavourite.setpPrice(productInfo.getpPrice());
        usersfavourite.setpDate(productInfo.getpDate());
        i = usersfavouriteMapper.insert(usersfavourite);
        return i;
    }

    @Override
    public int  outFavourite(int ufpid) {
        int i = usersfavouriteMapper.deleteByPrimaryKey(ufpid);
        return i;
    }

    @Override
    public PageInfo splitPage(int ufid, int pageNum, int pageSize) {
        //分页插件使用PageHelper工具类完成分页设置
        PageHelper.startPage(pageNum,pageSize);
        UsersfavouriteExample usersfavouriteExample = new UsersfavouriteExample();
        usersfavouriteExample.createCriteria().andUfidEqualTo(ufid);
        List<Usersfavourite> list = usersfavouriteMapper.selectByExample(usersfavouriteExample);
        //将查询到的集合封装进PageInfo对象中
        PageInfo<Usersfavourite> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    @Override
    public ProductInfo getByID(int ufpid) {
        return productInfoMapper.selectByPrimaryKey(ufpid);
    }

    @Override
    public PageInfo<Usersfavourite> splitPageVo(int ufid,int page, int pageSize) {
        //取出集合之前,先要设置PageHelper.startPage()属性
        PageHelper.startPage(page,pageSize);
        UsersfavouriteExample usersfavouriteExample = new UsersfavouriteExample();
        usersfavouriteExample.createCriteria().andUfidEqualTo(ufid);
        List<Usersfavourite> list = usersfavouriteMapper.selectByExample(usersfavouriteExample);
        return new PageInfo<>(list);
    }
}

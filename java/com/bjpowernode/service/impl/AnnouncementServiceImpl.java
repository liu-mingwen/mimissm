package com.bjpowernode.service.impl;

import com.bjpowernode.mapper.AnnouncementMapper;
import com.bjpowernode.pojo.Announcement;
import com.bjpowernode.pojo.AnnouncementExample;
import com.bjpowernode.pojo.vo.ProductInfoVo;
import com.bjpowernode.service.AnnouncementService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnnouncementServiceImpl implements AnnouncementService {

    @Autowired
    AnnouncementMapper announcementMapper;

    @Override
    public List<Announcement> getAll() {
        return announcementMapper.selectByExample(new AnnouncementExample());
    }

    @Override
    public PageInfo splitPage(int pageNum, int pageSize) {
        //分页插件使用PageHelper工具类完成分页设置
        PageHelper.startPage(pageNum,pageSize);

        //进行PageInfo的数据封装
        //进行有条件的查询操作,必须要创建ProductInfoExample对象
        AnnouncementExample example = new AnnouncementExample();
        //设置排序,按主键降序排序.
        //select * from product_info order by p_id desc
        example.setOrderByClause("id");
        //设置完排序后,取集合,切记切记:一定在取集合之前,设置PageHelper.startPage(pageNum,pageSize);
        List<Announcement> list = announcementMapper.selectByExample(example);
        //将查询到的集合封装进PageInfo对象中
        PageInfo<Announcement> pageInfo = new PageInfo<Announcement>(list);
        return pageInfo;
    }

    @Override
    public int save(Announcement announcement) {
        return announcementMapper.insert(announcement);
    }

    @Override
    public int delete(int id) {
        return announcementMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int deleteBatch(String[] ids) {
        return announcementMapper.deleteBatch(ids);
    }

    @Override
    public PageInfo<Announcement> splitPageVo(ProductInfoVo vo, int pageSize) {
        //取出集合之前,先要设置PageHelper.startPage()属性
        PageHelper.startPage(vo.getPage(),pageSize);
        List<Announcement> list = announcementMapper.selectCondition(vo);
        return new PageInfo<>(list);
    }
}

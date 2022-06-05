package com.bjpowernode.mapper;

import com.bjpowernode.pojo.Announcement;
import com.bjpowernode.pojo.AnnouncementExample;
import com.bjpowernode.pojo.vo.ProductInfoVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AnnouncementMapper {
    int countByExample(AnnouncementExample example);

    int deleteByExample(AnnouncementExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Announcement record);

    int insertSelective(Announcement record);

    List<Announcement> selectByExample(AnnouncementExample example);

    Announcement selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Announcement record, @Param("example") AnnouncementExample example);

    int updateByExample(@Param("record") Announcement record, @Param("example") AnnouncementExample example);

    int updateByPrimaryKeySelective(Announcement record);

    int updateByPrimaryKey(Announcement record);

    //批量删除公告的功能
    int deleteBatch(String []ids);

    //分页
    List<Announcement> selectCondition(ProductInfoVo vo);
}
package com.bjpowernode.mapper;

import com.bjpowernode.pojo.Usersfavourite;
import com.bjpowernode.pojo.UsersfavouriteExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UsersfavouriteMapper {
    int countByExample(UsersfavouriteExample example);

    int deleteByExample(UsersfavouriteExample example);

    int deleteByPrimaryKey(Integer typeId);

    int insert(Usersfavourite record);

    int insertSelective(Usersfavourite record);

    List<Usersfavourite> selectByExample(UsersfavouriteExample example);

    int updateByExampleSelective(@Param("record") Usersfavourite record, @Param("example") UsersfavouriteExample example);

    int updateByExample(@Param("record") Usersfavourite record, @Param("example") UsersfavouriteExample example);
}
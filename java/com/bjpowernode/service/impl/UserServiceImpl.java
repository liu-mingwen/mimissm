package com.bjpowernode.service.impl;

import com.bjpowernode.mapper.UsersMapper;
import com.bjpowernode.pojo.Users;
import com.bjpowernode.pojo.UsersExample;
import com.bjpowernode.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {


    //切记:业务逻辑层中一定有数据访问层的对象
    @Autowired
    UsersMapper usersMapper;

    @Override
    public List<Users> getAll() {
        return usersMapper.selectByExample(new UsersExample());
    }

    /*@Override
    public PageInfo<Users> splitPage(int pageNum, int pageSize) {
        //分页插件使用PageHelper工具类完成分页设置
        PageHelper.startPage(pageNum,pageSize);

        //进行PageInfo的数据封装
        UsersExample example = new UsersExample();
        //设置排序,按主键降序排序.
        //select * from product_info order by uid desc
        example.setOrderByClause("uid desc");
        List<Users> list = usersMapper.selectByExample(example);
        //将查询到的集合封装进PageInfo对象中
        PageInfo<Users> pageInfo = new PageInfo<Users>(list);
        return pageInfo;
    }*/

    @Override
    public int save(Users users) {
        return usersMapper.insert(users);
    }

    @Override
    public int delete(int uid) {
        return usersMapper.deleteByPrimaryKey(uid);
    }

    @Override
    public Users regist(Users users) {
        //根据传入的用户或到DB中查询相应用户对象
        //如果有条件 ,则一定要创建AdminExample的对象,用来封装条件
        UsersExample example = new UsersExample();
        /**如何添加条件
         * select * from admin where uname ='xxx'
         */
        //添加用户名a_name条件
        example.createCriteria().andUnameEqualTo(users.getUname());

        List<Users> list = usersMapper.selectByExample(example);
        if(list.size() > 0 ){
            return null;
        }
        else{
            usersMapper.insert(users);
            return users;
        }
    }

    @Override
    public Users login(String name, String pwd) {
        //根据传入的用户或到DB中查询相应用户对象
        //如果有条件 ,则一定要创建AdminExample的对象,用来封装条件
        UsersExample example = new UsersExample();
        //添加用户名uname条件
        example.createCriteria().andUnameEqualTo(name);

        List<Users> list = usersMapper.selectByExample(example);
        if(list.size() > 0 ){
            Users users = list.get(0);
            if(pwd.equals(users.getUpass())){
                return users;
            }
        }
        return null;
    }

    @Override
    public Users information(int uid) {
        return usersMapper.selectByPrimaryKey(uid);
    }

    @Override
    public int update(Users users) {
        return usersMapper.updateByPrimaryKey(users);
    }

}

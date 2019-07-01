package com.kgc.house.service;

import com.github.pagehelper.PageInfo;
import com.kgc.house.entity.Users;
import com.kgc.house.util.UserCondition;

public interface UserService {
    PageInfo<Users> getUserByPage(UserCondition condition);
    int insertSelective(Users record);
    int checkName(String name);//验证用户名是否存在
    int addUser(Users users);
    Users login(String name,String password);
}

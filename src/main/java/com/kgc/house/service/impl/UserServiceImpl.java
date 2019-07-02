package com.kgc.house.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kgc.house.entity.Users;
import com.kgc.house.entity.UsersExample;
import com.kgc.house.mapper.UsersMapper;
import com.kgc.house.service.UserService;
import com.kgc.house.util.MD5Utils;
import com.kgc.house.util.UserCondition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserServiceImpl  implements UserService {
    @Autowired
    private UsersMapper usersMapper;

    @Override
    public PageInfo<Users> getUserByPage(UserCondition condition) {
        PageHelper.startPage(condition.getPage(),condition.getRows());
        UsersExample usersExample=new UsersExample();
        UsersExample.Criteria criteria=usersExample.createCriteria();
        criteria.andIsadminEqualTo(new Integer(0));
        if(condition.getTelephone()!=null){
            criteria.andTelephoneLike("%"+condition.getTelephone()+"%");
        }
        if(condition.getStartAge()!=null){
            criteria.andAgeGreaterThan(condition.getStartAge());
        }
        if(condition.getEndAge()!=null){
            criteria.andAgeLessThan(condition.getEndAge());
        }
        List<Users> list=usersMapper.selectByExample(usersExample);
        return new PageInfo<>(list);
    }

    @Override
    public int insertSelective(Users record) {
        return usersMapper.insertSelective(record);
    }

    @Override
    public int checkName(String name) {
        UsersExample usersExample=new UsersExample();
        UsersExample.Criteria criteria = usersExample.createCriteria();
        criteria.andNameEqualTo(name);
        List<Users> list = usersMapper.selectByExample(usersExample);
        return list.size();
    }

    @Override
    public int addUser(Users users) {
        users.setIsadmin(0);
        users.setPassword(MD5Utils.md5Encrypt(users.getPassword()));
        System.out.println(MD5Utils.md5Encrypt(users.getPassword()));
        return usersMapper.insertSelective(users);
    }

    public Users login(String name,String password){
        UsersExample usersExample=new UsersExample();
        UsersExample.Criteria criteria = usersExample.createCriteria();
        criteria.andNameEqualTo(name);
        criteria.andPasswordEqualTo(MD5Utils.md5Encrypt(password));
        List<Users> users = usersMapper.selectByExample(usersExample);
        if(users.size()>0){
            return users.get(0);
        }else
            return null;
    }
}

package com.kgc.house.controller;

import com.github.pagehelper.PageInfo;
import com.kgc.house.entity.Users;
import com.kgc.house.service.UserService;
import com.kgc.house.util.UserCondition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;
@Controller
@RequestMapping("/user/")
public class UsersController {
     @Autowired
    private UserService userService;
    @RequestMapping("getUsers")
    @ResponseBody
    public Map<String,Object> getUsers(UserCondition condition){
        PageInfo<Users> pageInfo=userService.getUserByPage(condition);
        Map<String,Object> map=new HashMap<>();
        map.put("total",pageInfo.getTotal());
        map.put("rows",pageInfo.getList());
        return map;
    }
    @RequestMapping("insertUser")
    @ResponseBody
    public String insertUser(Users users){
        int i = userService.insertSelective(users);
        return "{\"result\":"+i+"}";
    }
}

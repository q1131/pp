package com.kgc.house.service;

import com.github.pagehelper.PageInfo;
import com.kgc.house.entity.House;
import com.kgc.house.entity.HouseCondition;

public interface HouseService {
    int insertHouse(House house);
    PageInfo<House> getUserHouseByPage(Integer page, Integer rows, Integer uid);
    House getHouse(String id);
    int updateHouse(House house);
    int delHouse(String id);
    PageInfo<House> getHouseByState(Integer page,Integer rows,Integer ispass);
    int passHouse(String id);
    PageInfo<House> getHouseByBrowser(HouseCondition condition);//浏览器获取条件查询
}

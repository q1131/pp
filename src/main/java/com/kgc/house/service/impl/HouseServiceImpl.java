package com.kgc.house.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kgc.house.entity.House;
import com.kgc.house.entity.HouseCondition;
import com.kgc.house.mapper.HouseMapper;
import com.kgc.house.service.HouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HouseServiceImpl implements HouseService {
    @Autowired
    private HouseMapper houseMapper;

    @Override
    public int insertHouse(House house) {
        return houseMapper.insertSelective(house);
    }

    @Override
    public PageInfo<House> getUserHouseByPage(Integer page, Integer rows, Integer uid) {
        PageHelper.startPage(page,rows);
        List<House> list=houseMapper.selectHouseByUserId(uid);
        return new PageInfo<House>(list);
    }

    @Override
    public House getHouse(String id) {
        return houseMapper.getHouse(id);
    }
    @Override
    public int updateHouse(House house) {
        return houseMapper.updateByPrimaryKeySelective(house);
    }

    @Override
    public int delHouse(String id) {
        House house=new House();
        house.setId(id);
        house.setIsdel(1);
        return houseMapper.updateByPrimaryKeySelective(house);
    }

    @Override
    public PageInfo<House> getHouseByState(Integer page, Integer rows,Integer ispass) {
        PageHelper.startPage(page,rows);
        List<House> houseList = houseMapper.getHouseByState(ispass);
        PageInfo pageInfo=new PageInfo(houseList);
        return pageInfo;
    }

    @Override
    public int passHouse(String id) {
        House house=new House();
        house.setId(id);
        house.setIspass(1);
        return houseMapper.updateByPrimaryKeySelective(house);
    }

    @Override
    public PageInfo<House> getHouseByBrowser(HouseCondition condition) {
        PageHelper.startPage(condition.getPage(),condition.getPageSize());
        if(condition.getTitle()!=null) {
            condition.setTitle("%" + condition.getTitle() + "%");
        }
        List<House> list=houseMapper.getHouseByBrowser(condition);
        PageInfo<House> pageInfo=new PageInfo<>(list);
        return pageInfo;
    }
}

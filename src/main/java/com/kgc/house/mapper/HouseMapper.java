package com.kgc.house.mapper;

import com.kgc.house.entity.House;
import com.kgc.house.entity.HouseCondition;
import com.kgc.house.entity.HouseExample;

import java.util.List;

public interface HouseMapper {
    int deleteByPrimaryKey(String id);

    int insert(House record);

    int insertSelective(House record);

    List<House> selectByExample(HouseExample example);

    House selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(House record);

    int updateByPrimaryKey(House record);

    List<House> selectHouseByUserId(Integer uid);

    House getHouse(String id);

    List<House> getHouseByState(Integer state);

    List<House> getHouseByBrowser(HouseCondition condition);
}
package com.kgc.house.service;

import com.github.pagehelper.PageInfo;
import com.kgc.house.entity.Street;

import java.util.List;

public interface StreetService {
    PageInfo<Street> selectByExample(Integer page,Integer rows);
    int insertSelective(Street record);
    int deleltStreetByDid(Integer id);
    int deleteByPrimaryKey(Integer id);
    int delMore(Integer[] ids);
    PageInfo<Street> selectByDid(Integer did,Integer page,Integer rows);
    public List<Street> selectById(Integer id);
}

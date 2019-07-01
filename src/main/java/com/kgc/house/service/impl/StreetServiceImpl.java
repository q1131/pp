package com.kgc.house.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kgc.house.entity.Street;
import com.kgc.house.entity.StreetExample;
import com.kgc.house.mapper.StreetMapper;
import com.kgc.house.service.StreetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StreetServiceImpl implements StreetService {
    @Autowired
    private StreetMapper streetMapper;
    @Override
    public PageInfo<Street> selectByExample(Integer page,Integer rows) {
        PageHelper.startPage(page,rows);
        StreetExample example=new StreetExample();
        return new PageInfo<>(streetMapper.selectByExample(example));
    }

    @Override
    public int insertSelective(Street record) {
        return streetMapper.insertSelective(record);
    }

    @Override
    public int deleltStreetByDid(Integer id) {
        return streetMapper.deleltStreetByDid(id);
    }

    public int deleteByPrimaryKey(Integer id){
        return streetMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int delMore(Integer[] ids) {
        return streetMapper.deleteMoreType(ids);
    }

    @Override
    public PageInfo<Street> selectByDid(Integer did,Integer page,Integer rows) {
        PageHelper.startPage(page,rows);
        List<Street> list = streetMapper.selectByDid(did);
        return new PageInfo<>(list);
    }

    @Override
    public List<Street> selectById(Integer id) {
        return streetMapper.selectByDid(id);
    }
}

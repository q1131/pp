package com.kgc.house.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kgc.house.entity.Type;
import com.kgc.house.entity.TypeExample;
import com.kgc.house.mapper.TypeMapper;
import com.kgc.house.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TypeServiceImpl implements TypeService {
    @Autowired
    private TypeMapper typeMapper;
    @Override
    public PageInfo<Type> getTypeByPage(Integer page, Integer pageSize) {
        PageHelper.startPage(page,pageSize);
        TypeExample example=new TypeExample();
        List<Type> list=typeMapper.selectByExample(example);
        return new PageInfo<>(list);
    }

    @Override
    public int addType(Type type) {
        return typeMapper.insertSelective(type);
    }

    @Override
    public int updateType(Type type) {
        return typeMapper.updateByPrimaryKeySelective(type);
    }

    @Transactional
    public int deleteType(Integer id) {
        return typeMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int deleteMoreType(Integer[] ids) {
        return typeMapper.deleteMoreType(ids);
    }

    @Override
    public List<Type> getAllType(TypeExample typeExample) {
        return typeMapper.selectByExample(typeExample);
    }

    @Override
    public List<Type> getAllType() {
        return typeMapper.selectByExample(new TypeExample());
    }
}

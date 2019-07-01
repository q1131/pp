package com.kgc.house.service;

import com.github.pagehelper.PageInfo;
import com.kgc.house.entity.Type;
import com.kgc.house.entity.TypeExample;

import java.util.List;

public interface TypeService {

    PageInfo<Type> getTypeByPage(Integer page, Integer pageSize);

    public int addType(Type type);

    public int updateType(Type type);

    public int deleteType(Integer id);

    int deleteMoreType(Integer[] ids);

    List<Type> getAllType(TypeExample typeExample);

    List<Type> getAllType();
}

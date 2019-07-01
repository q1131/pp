package com.kgc.house.service;

import com.github.pagehelper.PageInfo;
import com.kgc.house.entity.District;
import com.kgc.house.entity.DistrictExample;

import java.util.List;

public interface DistrictService {


    PageInfo<District> getDistrictByPage(Integer page, Integer pageSize);


    public int addDistrict(District district);



    public int updateDistrict(District district);



    public int deleteDistrict(Integer id);

    int deleteMoreDistrict(Integer[] ids);

    List<District> getAllDistrict(DistrictExample districtExample);
}

package com.kgc.house.controller;
import com.github.pagehelper.PageInfo;
import com.kgc.house.entity.Street;
import com.kgc.house.service.StreetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/street/")
public class StreetController {
    @Autowired
    private StreetService streetService;
    @RequestMapping("selectByExample")
    @ResponseBody
    public Map<String,Object> selectByExample(Integer page,Integer rows){
        System.out.println("查询全部");
        PageInfo<Street> pageInfo = streetService.selectByExample(page, rows);
        Map<String,Object> map=new HashMap<>();
        map.put("total",pageInfo.getTotal());
        map.put("rows",pageInfo.getList());
        return map;
    }
    @RequestMapping("insertStreet")
    @ResponseBody
    public String insertStreet(Street street){
        System.out.println(street.getDistrictId());
        System.out.println(street.getName());
        int i = streetService.insertSelective(street);
        return "{\"result\":"+i+"}";
    }
    @RequestMapping("deleteStreet")
    @ResponseBody
    public String deleteStreet(Integer id){
        int i = streetService.deleteByPrimaryKey(id);
        return "{\"result\":"+i+"}";
    }
    @RequestMapping("delMore")
    @ResponseBody
    public String delMore(String ids){
        String[] arrays=ids.split(",");
        Integer[] id=new Integer[arrays.length];
        for(int i=0;i<arrays.length;i++){
            id[i]=Integer.valueOf(arrays[i]);
        }
        int i = streetService.delMore(id);
        return "{\"result\":"+i+"}";
    }
    @RequestMapping("selectByDid")
    @ResponseBody
    public Map<String, Object> selectByDid(Integer did, Integer page, Integer rows){
        PageInfo<Street> pageInfo = streetService.selectByDid(did, page, rows);
        Map<String,Object> map=new HashMap<>();
        map.put("total",pageInfo.getTotal());
        map.put("rows",pageInfo.getList());
        return map;
    }
}

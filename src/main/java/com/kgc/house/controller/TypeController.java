package com.kgc.house.controller;

import com.github.pagehelper.PageInfo;
import com.kgc.house.entity.Type;
import com.kgc.house.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/type/")
public class TypeController {

    @Autowired
    private TypeService typeService;

     @RequestMapping("getType")
     @ResponseBody
     public Map<String,Object> getType(Integer page,Integer rows){
        PageInfo<Type> pageInfo=typeService.getTypeByPage(page,rows);
        Map<String,Object> map=new HashMap<>();
        map.put("total",pageInfo.getTotal());
        map.put("rows",pageInfo.getList());
        return map;
     }
    @RequestMapping("addType")
    @ResponseBody
    public String addType(Type type){
        int temp=typeService.addType(type);
        return "{\"result\":"+temp+"}";
    }

    @RequestMapping("upType")
    @ResponseBody
    public String upType(Type type){
        int temp=typeService.updateType(type);
        return "{\"result\":"+temp+"}";
    }


    @RequestMapping("delType")
    @ResponseBody
    public String delType(Integer id){
        int temp=typeService.deleteType(id);
        return "{\"result\":"+temp+"}";
    }
    @RequestMapping("delMoreType")
    @ResponseBody
    public String delType(String ids){

        String [] arys=ids.split(",");
        Integer [] id=new Integer[arys.length];
        for (int i=0;i<arys.length;i++){
            id[i]=Integer.parseInt(arys[i]);
        }
        int temp=typeService.deleteMoreType(id);
        return "{\"result\":"+temp+"}";
    }
}

package com.kgc.house.protal.controller;

import com.kgc.house.entity.Type;
import com.kgc.house.entity.TypeExample;
import com.kgc.house.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/protalType/")
public class ProtalTypeController {
    @Autowired
    private TypeService typeService;
    @RequestMapping("getType")
    @ResponseBody
    public List<Type> getAllType(){
        return typeService.getAllType(new TypeExample());
    }
}

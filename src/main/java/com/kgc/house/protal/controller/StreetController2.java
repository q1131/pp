package com.kgc.house.protal.controller;

import com.kgc.house.entity.Street;
import com.kgc.house.service.StreetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/stre/")
public class StreetController2 {
    @Autowired
    private StreetService streetService;

    @RequestMapping("qq")
    @ResponseBody
    public List<Street> selectStreetById(Integer did) {
        return streetService.selectById(did);
    }
}
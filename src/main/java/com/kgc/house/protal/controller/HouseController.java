package com.kgc.house.protal.controller;

import com.github.pagehelper.PageInfo;
import com.kgc.house.entity.*;
import com.kgc.house.service.DistrictService;
import com.kgc.house.service.HouseService;
import com.kgc.house.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/house/")
public class HouseController {
    @Autowired
    private TypeService typeService;
    @Autowired
    private DistrictService districtService;
    @Autowired
    private HouseService houseService;
    @RequestMapping("gofabu")
    public String goFaBu(Model model){
        List<Type> typeList = typeService.getAllType(new TypeExample());
        List<District> districtList = districtService.getAllDistrict(new DistrictExample());
        model.addAttribute("typeList",typeList);
        model.addAttribute("districtList",districtList);
        return "fabu";
    }
    @RequestMapping("insertHouse")
    public String insertHouse(@RequestParam(value = "pfile",required = false) CommonsMultipartFile pfile, House house, HttpSession session) throws IOException {
        String fname=pfile.getOriginalFilename();
        System.out.println("fname = " + fname);
        String expName=fname.substring(fname.lastIndexOf("."));
        String saveName=System.currentTimeMillis()+expName;
        File file=new File("D:\\images\\"+saveName);
        pfile.transferTo(file);
        house.setId(System.currentTimeMillis()+"");
        Users user=(Users)session.getAttribute("user");
        house.setUserId(user.getId());
        house.setIspass(0);
        house.setIsdel(0);
        house.setPath(saveName);
        if(houseService.insertHouse(house)>0){ //保存数据
            //调用业务
            //houseService.addHouse(house); //添加信息到数据库
            return "redirect:getUserHouse";  //跳转页面
        }
        else{
            //成功上传的图片删除
            file.delete();
            return "redirect:gofabu";  //跳转页面
        }
    }
    @RequestMapping("getUserHouse")
    public String getUserHouse(Integer page,HttpSession session,Model model) throws  Exception {
        Users user=(Users)session.getAttribute("user");
        PageInfo<House> pageInfo=houseService.getUserHouseByPage(page==null?1:page,1,user.getId());
        model.addAttribute("pageInfo",pageInfo);
        return "guanli";
    }
    @RequestMapping("getHouse")
    public String getHouse(String id,Model model){
        List<Type> typeList=typeService.getAllType(new TypeExample());
        List<District> districtList=districtService.getAllDistrict(new DistrictExample());
        House house=houseService.getHouse(id);
        model.addAttribute("typeList",typeList);
        model.addAttribute("districtList",districtList);
        model.addAttribute("house",house);
        return "upfabu";
    }
    @RequestMapping("upHouse")
    public String upHouse(String oldPic,House house, @RequestParam(value = "pfile",required = false) CommonsMultipartFile pfile, HttpSession session) throws IOException {
        System.out.println(oldPic);
        File file=null;
        if(pfile.getOriginalFilename().equals("")){
            System.out.println("不修改图片qqqqqqqqqq");
        }else{
            System.out.println("修改图片");
            file=new File("D:\\images\\"+oldPic);
            pfile.transferTo(file);
            house.setPath(oldPic);
        }
        if(houseService.updateHouse(house)<=0){
            if(file!=null) file.delete();
        }
        return "redirect:getUserHouse";
    }
    @RequestMapping("delHouse")
    public String delHouse(String id){
        houseService.delHouse(id);
        return "guanli";
    }
    @RequestMapping("goList")
    public String goList(HouseCondition condition,Model model){
        PageInfo<House> pageInfo = houseService.getHouseByBrowser(condition);
        model.addAttribute("pageInfo",pageInfo);
        if(condition.getTitle()!=null)
            condition.setTitle(condition.getTitle().replaceAll("%",""));
        model.addAttribute("condition",condition);
        return "list";
    }
}

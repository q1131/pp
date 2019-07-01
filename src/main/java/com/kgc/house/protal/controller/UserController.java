package com.kgc.house.protal.controller;

import com.kgc.house.entity.Users;
import com.kgc.house.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/page/")
public class UserController {
    @Autowired
    private UserService userService;
    @RequestMapping("checkName")
    @ResponseBody
    public String checkName(String name){
        int i = userService.checkName(name);
        return "{\"result\":"+i+"}";
    }
    @RequestMapping("regUser")
    public String regUser(Users users){
        int i = userService.addUser(users);
        if(i>0)
            return "login";
        else
            return "error";
    }
    @RequestMapping("loginUser")
    public String login(String name , String password, Model model, HttpSession session){

        Users login = userService.login(name, password);
        if(login==null){
            model.addAttribute("info","用户名密码错误!");
            return "gofabu";
        }
        else {
            session.setAttribute("user",login);
            session.setMaxInactiveInterval(666666666);
            return "guanli";
        }
    }
}

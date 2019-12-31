package com.zhl.basic.ctrl;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Lenovo
 * @title: UserCtrl
 * @projectName basic
 * @description: TODO
 * @date 2019/11/27 17:01
 */
@Controller
public class UserCtrl {

    @RequestMapping("")
    public String home(){
        return "login";
    }

    @RequestMapping("login")
    public String login(Model model){
        model.addAttribute("username","张雷");
        model.addAttribute("password","IBM");
        return "index";
    }

}

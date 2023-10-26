package com.osguri.vantage.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserResource {

    @GetMapping
    public String renderHome(){
        return "/user/dashboard/index";
    }
}

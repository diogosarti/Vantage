package com.osguri.vantage.resources;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeResoure {

    @RequestMapping
    public String home(){
        return "index";
    }

}

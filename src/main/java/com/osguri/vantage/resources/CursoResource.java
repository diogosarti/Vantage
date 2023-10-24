package com.osguri.vantage.resources;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value ="/cursos")
public class CursoResource {

    @GetMapping
    public String listCursos(){
        return "cursos";
    }

}

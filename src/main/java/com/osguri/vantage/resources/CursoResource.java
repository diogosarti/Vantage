package com.osguri.vantage.resources;

import com.osguri.vantage.services.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value ="/cursos")
public class CursoResource {

    @Autowired
    private CursoService cursoService;

    @GetMapping
    public String listCursos(){
        return "cursos";
    }


}

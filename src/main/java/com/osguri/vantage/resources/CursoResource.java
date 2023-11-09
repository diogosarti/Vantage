package com.osguri.vantage.resources;

import com.osguri.vantage.entities.Curso;
import com.osguri.vantage.services.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(value ="/cursos")
public class CursoResource {

    @Autowired
    private CursoService cursoService;

    @GetMapping
    public String listCursos(Model model){
        List<Curso> cursos = cursoService.findAll();
        model.addAttribute("cursos", cursos);
        return "cursos";
    }

    @GetMapping(value = "/{id}")
    public String getCurso(@PathVariable Long id, Model model) {
        Optional<Curso> cursoOptional = cursoService.findById(id);

        if (cursoOptional.isPresent()) {
            Curso curso = cursoOptional.get();
            model.addAttribute("curso", curso);
        } else {
            return "error";
        }

        return "cursodetails";
    }


}

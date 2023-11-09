package com.osguri.vantage.resources;

import aj.org.objectweb.asm.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.osguri.vantage.entities.Aula;
import com.osguri.vantage.entities.Curso;
import com.osguri.vantage.entities.User;
import com.osguri.vantage.services.AulaService;
import com.osguri.vantage.services.CursoService;
import com.osguri.vantage.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin")
public class AdminResource {

    @Autowired
    private AulaService aulaService;

    @Autowired
    private CursoService cursoService;

    @Autowired
    private UserService userService;

    @GetMapping
    public String getHome(){
        return "/admin/dashboard/index";
    }

    @GetMapping(value = "/cursos")
    public String getCursos(Model model, Principal principal){
        User user = userService.findByUsername(principal.getName());
        List<Curso> cursos = cursoService.getAdminCursos(user);
        model.addAttribute("cursos", cursos);
        return "admin/dashboard/meuscursos";
    }

    @GetMapping(value = "/cursos/add")
    public String addCurso(){
        return "admin/dashboard/adicionarcurso";
    }
    @PostMapping("/cursos/add")
    public String adicionarCurso(
            @RequestParam("image-banner")MultipartFile banner,
            @RequestParam("courseName") String courseName,
            @RequestParam("courseAbout") String courseAbout,
            @RequestParam("aulas") String aulasJson,
            Principal principal) {
        if (banner.isEmpty()) {
            return "Erro ao fazer upload do arquivo.";
        }

        String imagePath;
        try {
            String uploadDirectory = System.getProperty("user.dir") + "/src/main/resources/static/uploads/";

            String fileName = System.currentTimeMillis() + banner.getOriginalFilename();
            File destinationFile = new File(uploadDirectory, fileName);
            banner.transferTo(destinationFile);

            // Salvar o caminho do arquivo no banco de dados
            imagePath = fileName;
        } catch (IOException e) {
            e.printStackTrace();
            return "Erro ao fazer upload do arquivo.";
        }

        User user = userService.findByUsername(principal.getName());
        Curso curso = new Curso(courseName, courseAbout, user);
        curso.setBanner(imagePath);
        cursoService.saveCurso(curso);

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JavaType type = objectMapper.getTypeFactory().constructCollectionType(List.class, Aula.class);
            List<Aula> aulas = objectMapper.readValue(aulasJson, type);
            Curso cursosaved = cursoService.findByNome(curso.getNome());
            aulaService.adicionarTodasAulas(aulas, cursosaved);
        } catch (IOException e) {
            e.printStackTrace();
        }


        cursoService.saveCurso(curso);
        return "redirect:/admin/cursos";
    }


    @GetMapping("/cursos/{id}/edit")
    public String editarCurso(@PathVariable Long id, Model model) {
        Optional<Curso> curso = cursoService.findById(id);
        model.addAttribute("curso", curso);
        return "editar-curso";
    }

    @GetMapping("/cursos/{id}/delete")
    public String deleteCurso(@PathVariable Long id){
        cursoService.deleteCurso(id);
        return "redirect:/admin/cursos";
    }
}

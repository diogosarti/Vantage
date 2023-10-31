package com.osguri.vantage.services;

import com.osguri.vantage.entities.Aula;
import com.osguri.vantage.entities.Curso;
import com.osguri.vantage.repositories.AulaRepository;
import com.osguri.vantage.repositories.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class AulaService {
    @Autowired
    private AulaRepository aulaRepository;

    @Autowired
    private CursoRepository cursoRepository;

    public void adicionarAula(Curso curso, Aula aula) {
        aula.setCurso(curso); // Associa a aula ao curso
        aulaRepository.save(aula);
    }

    public void adicionarTodasAulas(List<Aula> aulas, Curso curso){
        for (Aula aula: aulas){
            aula.setCurso(curso);
        }
        aulaRepository.saveAll(aulas);
    }
}

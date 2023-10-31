package com.osguri.vantage.services;

import com.osguri.vantage.entities.Curso;
import com.osguri.vantage.entities.User;
import com.osguri.vantage.repositories.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CursoService{
    @Autowired
    private CursoRepository repository;

    public List<Curso> getAdminCursos(User user){
        return repository.findByCreatedBy(user);
    }

    public void deleteCurso(Long id){
        repository.deleteById(id);
    }

    public Optional<Curso> findById(Long id){
        return repository.findById(id);
    }

    public Curso findByNome(String nome){
        return repository.findByNome(nome);
    }

    public Curso saveCurso(Curso curso){
        return repository.save(curso);
    }

}

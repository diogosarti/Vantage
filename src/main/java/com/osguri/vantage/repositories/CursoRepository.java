package com.osguri.vantage.repositories;

import com.osguri.vantage.entities.Curso;
import com.osguri.vantage.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Long> {
    Curso findByNome(String nome);
    List<Curso> findByCreatedBy(User user);
}

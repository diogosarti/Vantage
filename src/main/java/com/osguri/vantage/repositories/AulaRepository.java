package com.osguri.vantage.repositories;

import com.osguri.vantage.entities.Aula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AulaRepository extends JpaRepository<Aula, UUID> {
    Aula findByUrl(String url);
}

package com.osguri.vantage.services;

import com.osguri.vantage.repositories.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CursoService{
    @Autowired
    private CursoRepository repository;
}

package com.api.rest.bidireccional.biblioteca.repositorio;

import com.api.rest.bidireccional.biblioteca.entidad.Biblioteca;
import com.api.rest.bidireccional.biblioteca.entidad.Libro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LibroRepositorio extends JpaRepository<Libro, Integer> {


    }

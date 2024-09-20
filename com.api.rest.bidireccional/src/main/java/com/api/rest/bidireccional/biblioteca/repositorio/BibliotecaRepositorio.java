package com.api.rest.bidireccional.biblioteca.repositorio;
import com.api.rest.bidireccional.biblioteca.entidad.Biblioteca;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BibliotecaRepositorio extends JpaRepository<Biblioteca, Integer> {


}

package com.api.rest.bidireccional.biblioteca.entidad;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "biblioteca")
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
public class Biblioteca {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ide_biblioteca;
    @NotNull
    private String detalle_biblioteca;

    @OneToMany(mappedBy = "biblioteca", cascade = CascadeType.ALL)
    private Set<Libro> libros= new HashSet<>();





    public int getIde_biblioteca() {
        return ide_biblioteca;
    }

    public void setIde_biblioteca(int ide_biblioteca) {
        this.ide_biblioteca = ide_biblioteca;
    }

    public @NotNull String getDetalle_biblioteca() {
        return detalle_biblioteca;
    }

    public void setDetalle_biblioteca(@NotNull String detalle_biblioteca) {
        this.detalle_biblioteca = detalle_biblioteca;
    }

    public Set<Libro> getLibros() {
        return libros;
    }

    public void setLibros(Set<Libro> libros) {
        this.libros = libros;
    }
/* public void setLibros(Set<Libro> libros) {
        this.libros = libros;
        for (Libro libro : libros){
            libro.setBiblioteca(this.ide_biblioteca);
        }
    }*/
}
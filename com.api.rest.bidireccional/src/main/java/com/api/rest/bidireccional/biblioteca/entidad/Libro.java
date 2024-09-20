package com.api.rest.bidireccional.biblioteca.entidad;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@Table(name = "libros", uniqueConstraints = {@UniqueConstraint(columnNames = {"detalle_libro"})})
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ide_libro;
    @NotNull
    private String detalle_libro;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "biblioteca_id")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Biblioteca biblioteca;

    public int getIde_libro() {
        return ide_libro;
    }

    public void setIde_libro(int ide_libro) {
        this.ide_libro = ide_libro;
    }

    public @NotNull String getDetalle_libro() {
        return detalle_libro;
    }

    public void setDetalle_libro(@NotNull String detalle_libro) {
        this.detalle_libro = detalle_libro;
    }

    public Biblioteca getBiblioteca() {
        return biblioteca;
    }

    public void setBiblioteca(Biblioteca biblioteca) {
        this.biblioteca = biblioteca;
    }
}

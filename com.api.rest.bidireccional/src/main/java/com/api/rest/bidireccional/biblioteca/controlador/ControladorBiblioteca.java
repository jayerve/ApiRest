package com.api.rest.bidireccional.biblioteca.controlador;

import com.api.rest.bidireccional.biblioteca.entidad.Biblioteca;
import com.api.rest.bidireccional.biblioteca.repositorio.BibliotecaRepositorio;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/api/biblioteca")
public class ControladorBiblioteca {

    @Autowired
    private BibliotecaRepositorio bibliotecaRepositorio;

    @GetMapping
    public  ResponseEntity<Page<Biblioteca>> listarBibliotecas(Pageable pageable){
        try {


            return ResponseEntity.ok(bibliotecaRepositorio.findAll(pageable));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    @PostMapping
    public ResponseEntity<Biblioteca>  guardarBiblioteca(@Valid @RequestBody Biblioteca biblioteca) {
        Biblioteca bibliotecaGuardar= bibliotecaRepositorio.save(biblioteca);
        URI ubicacion = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").
                buildAndExpand(bibliotecaGuardar.getIde_biblioteca()).toUri();
                return  ResponseEntity.created(ubicacion).body(bibliotecaGuardar);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Biblioteca> actualizarBiblioteca(@PathVariable Integer id,@Valid @RequestBody Biblioteca biblioteca) {
        Optional<Biblioteca> bibliotecaOptional = bibliotecaRepositorio.findById(id);
        if (!bibliotecaOptional.isPresent()){
            return ResponseEntity.unprocessableEntity().build();
        }

        biblioteca.setIde_biblioteca(bibliotecaOptional.get().getIde_biblioteca());
        bibliotecaRepositorio.save(biblioteca);
        return ResponseEntity.noContent().build() ;
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Biblioteca> eliminarBiblioteca(@PathVariable Integer id){
        Optional<Biblioteca> bibliotecaOptional = bibliotecaRepositorio.findById(id);
        if (!bibliotecaOptional.isPresent()){
            return ResponseEntity.unprocessableEntity().build();
        }
        bibliotecaRepositorio.delete(bibliotecaOptional.get());
        return ResponseEntity.noContent().build();

    }

    @GetMapping("/{id}")
    public ResponseEntity<Biblioteca> obtenerBibliotecaPorId(@PathVariable Integer id){
        Optional<Biblioteca> bibliotecaOptional = bibliotecaRepositorio.findById(id);

        if (!bibliotecaOptional.isPresent()){
            return ResponseEntity.unprocessableEntity().build();
        }
        return ResponseEntity.ok(bibliotecaOptional.get());

    }

}

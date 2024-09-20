package com.api.rest.bidireccional.biblioteca.controlador;
import com.api.rest.bidireccional.biblioteca.entidad.Libro;
import com.api.rest.bidireccional.biblioteca.repositorio.LibroRepositorio;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/api/libros")
public class ControladorLibro {

    @Autowired
    private LibroRepositorio libroRepositorio;

    @GetMapping
    public ResponseEntity<Page<Libro>> listarLibros(Pageable pageable){
        try {


            return ResponseEntity.ok(libroRepositorio.findAll(pageable));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    @PostMapping
    public ResponseEntity<Libro>  guardarLibro(@Valid @RequestBody Libro libro) {
        Libro libroGuardar= libroRepositorio.save(libro);
        URI ubicacion = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").
                buildAndExpand(libroGuardar.getIde_libro()).toUri();
        return  ResponseEntity.created(ubicacion).body(libroGuardar);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Libro> actualizarLibro(@PathVariable Integer id,@Valid @RequestBody Libro libro) {
        Optional<Libro> libroOptional = libroRepositorio.findById(id);
        if (!libroOptional.isPresent()){
            return ResponseEntity.unprocessableEntity().build();
        }

        libro.setIde_libro(libroOptional.get().getIde_libro());
        libroRepositorio.save(libro);
        return ResponseEntity.noContent().build() ;
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Libro> eliminarLibro(@PathVariable Integer id){
        Optional<Libro> libroOptional = libroRepositorio.findById(id);
        if (!libroOptional.isPresent()){
            return ResponseEntity.unprocessableEntity().build();
        }
        libroRepositorio.delete(libroOptional.get());
        return ResponseEntity.noContent().build();

    }

    @GetMapping("/{id}")
    public ResponseEntity<Libro> obtenerLibroPorId(@PathVariable Integer id){
        Optional<Libro> bibliotecaOptional = libroRepositorio.findById(id);

        if (!bibliotecaOptional.isPresent()){
            return ResponseEntity.unprocessableEntity().build();
        }
        return ResponseEntity.ok(bibliotecaOptional.get());

    }



}

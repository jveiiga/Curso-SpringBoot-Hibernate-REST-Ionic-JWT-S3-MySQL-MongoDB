package com.example.cursomc.resources;

import com.example.cursomc.domain.Categoria;
import com.example.cursomc.services.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

// EndPoint
@RestController
@RequestMapping(value="/categorias")
public class CategoriaResource {

    @Autowired
    private CategoriaService categoriaService;

    //GET
    @RequestMapping(value="/{id}", method=RequestMethod.GET)
    public ResponseEntity<Categoria> find(@PathVariable Integer id) {

        Categoria obj = categoriaService.find(id);

        return ResponseEntity.ok().body(obj);
    }

    //POST
    @RequestMapping(method=RequestMethod.POST)
    public ResponseEntity<Categoria> insert(@RequestBody Categoria obj) {

        obj = categoriaService.insert(obj);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(obj.getId())
                    .toUri();

        return ResponseEntity.created(uri).build();
    }

    //PUT
    @RequestMapping(value="/{id}", method=RequestMethod.PUT)
    public ResponseEntity<Categoria> update(@RequestBody Categoria obj, @PathVariable Integer id) {
        
        obj.setId(id);
        obj = categoriaService.update(obj);

        return ResponseEntity.noContent().build();
    }
}

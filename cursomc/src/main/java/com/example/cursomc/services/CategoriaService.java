package com.example.cursomc.services;

import com.example.cursomc.domain.Categoria;
import com.example.cursomc.repositories.CategoriaRepository;
import com.example.cursomc.services.exceptions.ObjectNotFoundExpection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

// Camada de serviço de acesse aos dados
@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository repo;

    public Categoria buscar(Integer id) {

        Optional<Categoria> obj = repo.findById(id);

        if (obj.isEmpty()) {
            throw new ObjectNotFoundExpection("Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName());
        }
        return  obj.get();
    }
}

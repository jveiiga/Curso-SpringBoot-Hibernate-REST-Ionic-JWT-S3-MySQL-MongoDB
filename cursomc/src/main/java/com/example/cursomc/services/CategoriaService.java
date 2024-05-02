package com.example.cursomc.services;

import com.example.cursomc.domain.Categoria;
import com.example.cursomc.repositories.CategoriaRepository;
import com.example.cursomc.services.exceptions.DataIntegrityException;
import com.example.cursomc.services.exceptions.ObjectNotFoundExpection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Optional;

// Camada de serviço de acesse aos dados
@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    //Busca categoria por id
    public Categoria find(Integer id) {

        Optional<Categoria> obj = categoriaRepository.findById(id);

        if (obj.isEmpty()) {
            throw new ObjectNotFoundExpection("Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName());
        }
        return  obj.get();
    }

    //Adciona uma nova categoria
    public Categoria insert(Categoria obj) {

        obj.setId(null);

        return categoriaRepository.save(obj);
    }

    //Edita uma categoria
    public Categoria update(Categoria obj) {
        find(obj.getId());
        return categoriaRepository.save(obj);
    }

    //Deleta uma categoria
    public void delete(Integer id) {
        find(id);
        try{
            categoriaRepository.deleteById(id);
        } 
        catch (DataIntegrityViolationException e) {

            throw new  DataIntegrityException("Não é possível excluir uma categoria que possuí produtos!");
        }
    }
}

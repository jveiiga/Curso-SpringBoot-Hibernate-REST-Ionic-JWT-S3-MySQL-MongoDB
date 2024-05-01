package com.example.cursomc.services;

import com.example.cursomc.domain.Cliente;
import com.example.cursomc.repositories.ClienteRepository;
import com.example.cursomc.services.exceptions.ObjectNotFoundExpection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public Cliente find(Integer id) {

        Optional<Cliente> obj = clienteRepository.findById(id);

        if (obj.isEmpty()) {
            throw new ObjectNotFoundExpection("Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName());
        }
        return obj.get();
    }
}

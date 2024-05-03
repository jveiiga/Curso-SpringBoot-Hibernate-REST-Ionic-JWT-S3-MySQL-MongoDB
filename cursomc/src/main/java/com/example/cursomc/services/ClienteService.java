package com.example.cursomc.services;

import com.example.cursomc.domain.Cliente;
import com.example.cursomc.dto.ClienteDTO;
import com.example.cursomc.repositories.ClienteRepository;
import com.example.cursomc.services.exceptions.DataIntegrityException;
import com.example.cursomc.services.exceptions.ObjectNotFoundExpection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    // Busca um cliente pelo ID
    public Cliente find(Integer id) {

        Optional<Cliente> obj = clienteRepository.findById(id);

        if (obj.isEmpty()) {
            throw new ObjectNotFoundExpection("Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName());
        }
        return obj.get();
    }

    // Adciona um novo cliente
    // public Cliente insert(Cliente obj) {

    //     obj.setId(null);

    //     return clienteRepository.save(obj);
    // }

    // Edita um cliente
    public Cliente update(Cliente obj) {
        Cliente newObj = find(obj.getId());
        updateData(newObj, obj);
        return clienteRepository.save(newObj);
    }

    // Deleta um cliente
    public void delete(Integer id) {
        find(id);
        try{
            clienteRepository.deleteById(id);
        } 
        catch (DataIntegrityViolationException e) {

            throw new  DataIntegrityException("Não é possível excluir por que há entidades relacionadas!");
        }
    }

    // Lista todos os clientes
    public List<Cliente> findall() {

        return clienteRepository.findAll();
    }

    // Lista todos os clientes por página
    public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
        

        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.by(Sort.Direction.fromString(direction), orderBy));

        return clienteRepository.findAll(pageRequest);
    } 

    public Cliente fromDTO(ClienteDTO objDto) {

        return new Cliente(objDto.getId(), objDto.getNome(), objDto.getEmail(), null, null);
    }

    private void updateData(Cliente newObj, Cliente obj) {
        newObj.setNome(obj.getNome());
        newObj.setEmail(obj.getEmail());
    }
}

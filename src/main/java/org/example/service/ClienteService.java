package org.example.service;

import org.example.model.entity.Cliente;
import org.example.repository.ClienteRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ClienteService {

    private final ClienteRepository repository;

    public ClienteService(ClienteRepository repository) {
        this.repository = repository;
    }

    public List<Cliente> getClientes(){
        return repository.findAll();
    }

    public Cliente salvar(Cliente cliente){
        return repository.save(cliente);
    }

    public Cliente acharPorId(Integer id){
        return repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));
    }

    public void deletar(Integer id){
        repository.findById(id).map(cliente -> {  repository.delete(cliente); return Void.TYPE;}).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public void atualizar(Integer id, Cliente clienteAtualizado){
        repository.findById(id).map(cliente -> {  clienteAtualizado.setId(cliente.getId()); return repository.save(clienteAtualizado);}).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
}

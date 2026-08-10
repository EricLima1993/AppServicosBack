package org.example.rest;

import org.example.model.entity.Cliente;
import org.example.model.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    private final ClienteRepository repository;

    @Autowired
    public ClienteController(ClienteRepository repository){
        this.repository = repository;
    }

    @GetMapping("buscar")
    public List<Cliente> getClientes(){
        return repository.findAll();
    }

    @PostMapping("criar")
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente salvar(@RequestBody @Valid Cliente cliente){
        return repository.save(cliente);
    }

    @GetMapping("buscar/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Cliente acharPorId(@PathVariable Integer id){
        return repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));
    }

    @DeleteMapping("deletar/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Integer id){
        repository.findById(id).map(cliente -> {  repository.delete(cliente); return Void.TYPE;}).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
    @PutMapping("atualizar/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizar(@PathVariable Integer id,@RequestBody @Valid Cliente clienteAtualizado){
        repository.findById(id).map(cliente -> {  clienteAtualizado.setId(cliente.getId()); return repository.save(clienteAtualizado);}).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
}

package org.example.controller;

import jakarta.validation.Valid;
import org.example.model.Cliente;
import org.example.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    @Autowired
    public ClienteController(ClienteService clienteService){
        this.clienteService = clienteService;
    }

    @GetMapping("buscar")
    public List<Cliente> getClientes(){
        return clienteService.getClientes();
    }

    @PostMapping("criar")
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente salvar(@RequestBody @Valid Cliente cliente){
        return clienteService.salvar(cliente);
    }

    @GetMapping("buscar/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Cliente acharPorId(@PathVariable Integer id){
        return clienteService.acharPorId(id);
    }

    @DeleteMapping("deletar/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Integer id){
        clienteService.deletar(id);
    }
    @PutMapping("atualizar/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizar(@PathVariable Integer id,@RequestBody @Valid Cliente clienteAtualizado){
        clienteService.atualizar(id, clienteAtualizado);
    }
}

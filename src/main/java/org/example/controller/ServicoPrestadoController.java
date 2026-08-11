package org.example.controller;

import jakarta.validation.Valid;
import org.example.model.ServicoPrestado;
import org.example.controller.dto.ServicoPrestadoDTO;
import org.example.service.ServicoPrestadoService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/servico-prestados")
public class ServicoPrestadoController {

    private final ServicoPrestadoService servicoPrestadoService;

    public ServicoPrestadoController(ServicoPrestadoService servicoPrestadoService) {
        this.servicoPrestadoService = servicoPrestadoService;
    }

    @PostMapping("criar")
    @ResponseStatus(HttpStatus.CREATED)
    public ServicoPrestado salvar(@RequestBody @Valid ServicoPrestadoDTO dto){
        return servicoPrestadoService.salvar(dto);
    }

    @GetMapping("buscar")
    public List<ServicoPrestado> pesquisar(@RequestParam(value = "nome", required = false, defaultValue = "") String nome, @RequestParam(value = "mes",required = false) Integer mes){
        return servicoPrestadoService.pesquisar(nome, mes);
    }
}

package org.example.rest;

import org.example.model.entity.Cliente;
import org.example.model.entity.ServicoPrestado;
import org.example.model.repository.ClienteRepository;
import org.example.model.repository.ServicoPrestadoRepository;
import org.example.rest.dto.ServicoPrestadoDTO;
import org.example.util.BigDecimalConverter;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/api/servico-prestados")
@CrossOrigin("http://localhost:4200")
public class ServicoPrestadoController {

    private final ClienteRepository clienteRepository;
    private final ServicoPrestadoRepository servicoPrestadoRepository;
    private final BigDecimalConverter bigDecimalConverter;

    public ServicoPrestadoController(ClienteRepository clienteRepository, ServicoPrestadoRepository servicoPrestadoRepository, BigDecimalConverter bigDecimalConverter){
        this.clienteRepository = clienteRepository;
        this.servicoPrestadoRepository = servicoPrestadoRepository;
        this.bigDecimalConverter = bigDecimalConverter;
    }

    @PostMapping("criar")
    @ResponseStatus(HttpStatus.CREATED)
    public ServicoPrestado salvar(@RequestBody ServicoPrestadoDTO dto){
        LocalDate data = LocalDate.parse(dto.getData(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        Cliente cliente = clienteRepository.findById(dto.getIdCliente()).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cliente inexistente."));

        ServicoPrestado servicoPrestado = new ServicoPrestado();
        servicoPrestado.setDescricao(dto.getDescricao());
        servicoPrestado.setData(data);
        servicoPrestado.setCliente(cliente);
        servicoPrestado.setValor(bigDecimalConverter.converter(dto.getPreco()));

        return servicoPrestadoRepository.save(servicoPrestado);

    }

    @GetMapping("buscar")
    public List<ServicoPrestado> pesquisar(@RequestParam(value = "nome", required = false, defaultValue = "") String nome, @RequestParam(value = "mes",required = false) Integer mes){
        return servicoPrestadoRepository.findByNomeClienteAndMes("%"+nome+"%",mes);
    }
}

package org.example.service;

import org.example.controller.dto.ServicoPrestadoDTO;
import org.example.model.entity.Cliente;
import org.example.model.entity.ServicoPrestado;
import org.example.repository.ClienteRepository;
import org.example.repository.ServicoPrestadoRepository;
import org.example.util.BigDecimalConverter;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class ServicoPrestadoService {

    private final ClienteRepository clienteRepository;
    private final ServicoPrestadoRepository servicoPrestadoRepository;
    private final BigDecimalConverter bigDecimalConverter;

    public ServicoPrestadoService(ClienteRepository clienteRepository, ServicoPrestadoRepository servicoPrestadoRepository, BigDecimalConverter bigDecimalConverter){
        this.clienteRepository = clienteRepository;
        this.servicoPrestadoRepository = servicoPrestadoRepository;
        this.bigDecimalConverter = bigDecimalConverter;
    }

    public ServicoPrestado salvar(ServicoPrestadoDTO dto){
        LocalDate data = LocalDate.parse(dto.getData(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        Cliente cliente = clienteRepository.findById(dto.getIdCliente()).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cliente inexistente."));

        ServicoPrestado servicoPrestado = new ServicoPrestado();
        servicoPrestado.setDescricao(dto.getDescricao());
        servicoPrestado.setData(data);
        servicoPrestado.setCliente(cliente);
        servicoPrestado.setValor(bigDecimalConverter.converter(dto.getPreco()));

        return servicoPrestadoRepository.save(servicoPrestado);
    }

    public List<ServicoPrestado> pesquisar(String nome, Integer mes){
        if(mes == null){
            return servicoPrestadoRepository.findByNomeCliente("%"+nome+"%");
        }else{
            return servicoPrestadoRepository.findByNomeClienteAndMes("%"+nome+"%",mes);
        }
    }
}

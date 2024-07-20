package org.example;

import org.example.model.entity.Cliente;
import org.example.model.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class VendasApplication {

   /* @Bean
    public CommandLineRunner run(@Autowired ClienteRepository repository){
        return args -> {
            Cliente c = Cliente.builder().cpf("00000000000").nome("Fulano").build();
            repository.save(c);
        };
    }*/
    public static void main(String[] args) {
        SpringApplication.run(VendasApplication.class, args);
    }
}

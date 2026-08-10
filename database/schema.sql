-- Tabela: cliente
CREATE TABLE cliente (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(150) NOT NULL,
    cpf VARCHAR(150) NOT NULL,
    data_cadastro   DATE
);

-- Tabela: servico_prestado
CREATE TABLE servico_prestado (
    id INT AUTO_INCREMENT PRIMARY KEY,
    descricao VARCHAR(150) NOT NULL,
    id_cliente INT,
    valor DECIMAL(19,2),
    data DATE,
    CONSTRAINT fk_servico_prestado_cliente
        FOREIGN KEY (id_cliente) REFERENCES cliente(id)
);
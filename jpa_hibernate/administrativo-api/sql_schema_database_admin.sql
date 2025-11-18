-- Tabela de sequência usada pelo Hibernate
CREATE TABLE hibernate_sequence (
    next_val BIGINT
);

-- Tabela pessoa
CREATE TABLE pessoa (
    id BIGINT NOT NULL,
    cpf VARCHAR(14),
    data_nascimento DATE,
    email VARCHAR(150),
    idade INTEGER,
    nome VARCHAR(120) NOT NULL,
    PRIMARY KEY (id),
    UNIQUE (cpf)
);

-- Tabela produto
CREATE TABLE produto (
    id BIGINT NOT NULL,
    nome VARCHAR(120) NOT NULL,
    preco DOUBLE,
    quantidade INTEGER,
    status BOOLEAN,
    PRIMARY KEY (id)
);

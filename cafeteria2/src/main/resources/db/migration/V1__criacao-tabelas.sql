-- =====================================================================
-- V1 - Criacao das tabelas base do dominio da Cafeteria
-- =====================================================================

CREATE TABLE fornecedor (
    id    BIGINT       NOT NULL AUTO_INCREMENT,
    nome  VARCHAR(255) NOT NULL,
    cnpj  VARCHAR(255) NOT NULL,
    CONSTRAINT pk_fornecedor PRIMARY KEY (id),
    CONSTRAINT uk_fornecedor_cnpj UNIQUE (cnpj)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4;

CREATE TABLE produto (
    id            BIGINT         NOT NULL AUTO_INCREMENT,
    nome          VARCHAR(255)   NOT NULL,
    preco         DECIMAL(10, 2) NOT NULL,
    quantidade    INT            NOT NULL,
    categoria     VARCHAR(255)   NULL,
    fornecedor_id BIGINT         NULL,
    CONSTRAINT pk_produto PRIMARY KEY (id),
    CONSTRAINT fk_produto_fornecedor FOREIGN KEY (fornecedor_id) REFERENCES fornecedor (id)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4;

CREATE INDEX idx_produto_categoria ON produto (categoria);

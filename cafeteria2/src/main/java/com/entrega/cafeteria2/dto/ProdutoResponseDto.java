package com.entrega.cafeteria2.dto;

import com.entrega.cafeteria2.entity.Categoria;

import java.math.BigDecimal;

public record ProdutoResponseDto (
        Long id,
        String nome,
        BigDecimal preco,
        int quantidade,
        Categoria categoria,
        String nomeFornecedor
){
}

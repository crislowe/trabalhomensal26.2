package com.cafeteria.cafeteria.dto;

import com.cafeteria.cafeteria.model.Categoria;

import java.math.BigDecimal;

public record ProdutoResponseDto(Long id, String nome, String descricao, BigDecimal preco, Boolean disponivel, Categoria categoria) {
}

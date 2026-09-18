package com.entrega.cafeteria2.dto;

import com.entrega.cafeteria2.entity.Categoria;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record ProdutoRequestDto(
        @NotBlank(message = "O nome não pode estar em branco")
        String nome,

        @NotNull(message = "O preço deve ser preenchido")
        BigDecimal preco,

        @NotNull(message = "A quantidade deve ser preenchida")
        Integer quantidade,

        @NotNull(message = "A categoria deve ser informada")
        Categoria categoria,

        @NotNull(message = "O ID do fornecedor deve ser informado")
        Long fornecedorId
) {
}

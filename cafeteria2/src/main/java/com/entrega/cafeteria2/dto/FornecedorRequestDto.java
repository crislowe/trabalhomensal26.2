package com.entrega.cafeteria2.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record FornecedorRequestDto(
        @NotBlank(message = "O nome não deve estar vazio")
        String nome,

        @NotNull(message = "O CNPJ não deve estar vazio")
        String cnpj
){
}

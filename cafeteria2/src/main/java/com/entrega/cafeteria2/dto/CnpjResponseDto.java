package com.entrega.cafeteria2.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record CnpjResponseDto(
        String cnpj,
        String razao_social,
        String nome_fantasia
) {}
package com.entrega.cafeteria2.client;

import com.entrega.cafeteria2.dto.CnpjResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

// A propriedade 'url' aponta para a base da BrasilAPI de CNPJ
@FeignClient(name = "cnpjClient", url = "https://brasilapi.com.br/api/cnpj/v1")
public interface CnpjClient {

    @GetMapping("/{cnpj}")
    CnpjResponseDto consultarCnpj(@PathVariable("cnpj") String cnpj);
}
package com.entrega.cafeteria2.service;

import com.entrega.cafeteria2.client.CnpjClient;
import com.entrega.cafeteria2.dto.CnpjResponseDto;
import com.entrega.cafeteria2.dto.FornecedorRequestDto;
import com.entrega.cafeteria2.dto.FornecedorResponseDto;
import com.entrega.cafeteria2.entity.Fornecedor;
import com.entrega.cafeteria2.repository.FornecedorRepository;
import feign.Client;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FornecedorService {

    private static final Logger log = LoggerFactory.getLogger(FornecedorService.class);

    private final FornecedorRepository fornecedorRepository;
    private final CnpjClient cnpjClient;

    public FornecedorService(FornecedorRepository fornecedorRepository, CnpjClient cnpjClient) {
        this.fornecedorRepository = fornecedorRepository;
        this.cnpjClient = cnpjClient;
    }

    public Fornecedor buscarPorIdEntity(Long id) {
        log.info("Buscando entidade Fornecedor por ID interno: {}", id);
        return fornecedorRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Fornecedor não encontrado com o ID: {}", id);
                    return new RuntimeException("Fornecedor não encontrado com o ID: " + id);
                });
    }

    @Transactional
    public FornecedorResponseDto salvar(FornecedorRequestDto dto) {
        String cnpjLimpo = dto.cnpj().replaceAll("\\D", "");

        try {
            // Tenta consultar na BrasilAPI
            CnpjResponseDto dadosExternos = cnpjClient.consultarCnpj(cnpjLimpo);
            log.info("CNPJ validado com sucesso na base externa: {}", dadosExternos.razao_social());
        } catch (Exception e) {
            log.error("Tentativa de cadastro com CNPJ inválido ou inexistente: {}", dto.cnpj());
            throw new RuntimeException("CNPJ inválido ou não encontrado na Receita Federal.");
        }

        // Se passou pelo try, o CNPJ é real e o código continua salvando normalmente...
        Fornecedor fornecedor = new Fornecedor();
        fornecedor.setNome(dto.nome());
        fornecedor.setCnpj(dto.cnpj());

        Fornecedor salvo = fornecedorRepository.save(fornecedor);
        return converterParaDto(salvo);
    }

    public List<FornecedorResponseDto> listarTodos() {
        log.info("Listando todos os fornecedores cadastrados.");
        return fornecedorRepository.findAll().stream()
                .map(this::converterParaDto)
                .collect(Collectors.toList());
    }

    public FornecedorResponseDto buscarPorId(Long id) {
        log.info("Buscando fornecedor por ID para resposta externa: {}", id);
        Fornecedor fornecedor = buscarPorIdEntity(id);
        return converterParaDto(fornecedor);
    }

    @Transactional
    public FornecedorResponseDto atualizar(Long id, FornecedorRequestDto dto) {
        log.info("Atualizando dados do fornecedor de ID: {}", id);
        Fornecedor fornecedor = buscarPorIdEntity(id);

        fornecedor.setNome(dto.nome());
        fornecedor.setCnpj(dto.cnpj());

        Fornecedor atualizado = fornecedorRepository.save(fornecedor);
        log.info("Fornecedor de ID: {} atualizado com sucesso.", id);

        return converterParaDto(atualizado);
    }

    @Transactional
    public void deletar(Long id) {
        log.info("Tentando deletar o fornecedor de ID: {}", id);
        if (!fornecedorRepository.existsById(id)) {
            log.error("Falha ao deletar: Fornecedor não encontrado com o ID: {}", id);
            throw new RuntimeException("Fornecedor não encontrado com o ID: " + id);
        }
        fornecedorRepository.deleteById(id);
        log.info("Fornecedor de ID: {} deletado com sucesso.", id);
    }

    private FornecedorResponseDto converterParaDto(Fornecedor fornecedor) {
        return new FornecedorResponseDto(
                fornecedor.getId(),
                fornecedor.getNome(),
                fornecedor.getCnpj()
        );
    }
}
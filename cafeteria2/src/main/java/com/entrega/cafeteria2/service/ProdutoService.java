package com.entrega.cafeteria2.service;

import com.entrega.cafeteria2.dto.ProdutoRequestDto;
import com.entrega.cafeteria2.dto.ProdutoResponseDto;
import com.entrega.cafeteria2.entity.Categoria;
import com.entrega.cafeteria2.entity.Fornecedor;
import com.entrega.cafeteria2.entity.Produto;
import com.entrega.cafeteria2.repository.ProdutoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProdutoService {

    private static final Logger log = LoggerFactory.getLogger(ProdutoService.class);

    private final ProdutoRepository produtoRepository;
    private final FornecedorService fornecedorService;

    public ProdutoService(ProdutoRepository produtoRepository, FornecedorService fornecedorService) {
        this.produtoRepository = produtoRepository;
        this.fornecedorService = fornecedorService;
    }

    @Transactional
    public ProdutoResponseDto salvar(ProdutoRequestDto dto) {
        log.info("Iniciando o cadastro de um novo produto: {}", dto.nome());

        Fornecedor fornecedor = fornecedorService.buscarPorIdEntity(dto.fornecedorId());

        Produto produto = new Produto();
        produto.setNome(dto.nome());
        produto.setPreco(dto.preco());
        produto.setQuantidade(dto.quantidade());
        produto.setCategoria(dto.categoria());
        produto.setFornecedor(fornecedor);

        Produto produtoSalvo = produtoRepository.save(produto);
        log.info("Produto cadastrado com sucesso com o ID: {}", produtoSalvo.getId());

        return converterParaDto(produtoSalvo);
    }

    public List<ProdutoResponseDto> listarTodos() {
        log.info("Listando todos os produtos cadastrados.");
        return produtoRepository.findAll().stream()
                .map(this::converterParaDto)
                .collect(Collectors.toList());
    }

    public ProdutoResponseDto buscarPorId(Long id) {
        log.info("Buscando produto por ID: {}", id);
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Produto não encontrado com o ID: {}", id);
                    return new RuntimeException("Produto não encontrado com o ID: " + id);
                });
        return converterParaDto(produto);
    }

    public List<ProdutoResponseDto> buscarPorCategoria(Categoria categoria) {
        log.info("Buscando produtos pela categoria: {}", categoria);
        return produtoRepository.findByCategoria(categoria).stream()
                .map(this::converterParaDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public ProdutoResponseDto atualizar(Long id, ProdutoRequestDto dto) {
        log.info("Atualizando dados do produto de ID: {}", id);
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Falha ao atualizar: Produto não encontrado com o ID: {}", id);
                    return new RuntimeException("Produto não encontrado com o ID: " + id);
                });

        Fornecedor fornecedor = fornecedorService.buscarPorIdEntity(dto.fornecedorId());

        produto.setNome(dto.nome());
        produto.setPreco(dto.preco());
        produto.setQuantidade(dto.quantidade());
        produto.setCategoria(dto.categoria());
        produto.setFornecedor(fornecedor);

        Produto produtoAtualizado = produtoRepository.save(produto);
        log.info("Produto de ID: {} atualizado com sucesso.", id);

        return converterParaDto(produtoAtualizado);
    }

    @Transactional
    public void deletar(Long id) {
        log.info("Tentando deletar o produto de ID: {}", id);
        if (!produtoRepository.existsById(id)) {
            log.error("Falha ao deletar: Produto não encontrado com o ID: {}", id);
            throw new RuntimeException("Produto não encontrado com o ID: " + id);
        }
        produtoRepository.deleteById(id);
        log.info("Produto de ID: {} deletado com sucesso.", id);
    }

    private ProdutoResponseDto converterParaDto(Produto produto) {
        return new ProdutoResponseDto(
                produto.getId(),
                produto.getNome(),
                produto.getPreco(),
                produto.getQuantidade(),
                produto.getCategoria(),
                produto.getFornecedor().getNome()
        );
    }
}
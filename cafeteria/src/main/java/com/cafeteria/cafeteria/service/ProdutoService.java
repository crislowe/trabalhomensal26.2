package com.cafeteria.cafeteria.service;


import com.cafeteria.cafeteria.dto.ProdutoRequestDto;
import com.cafeteria.cafeteria.dto.ProdutoResponseDto;
import com.cafeteria.cafeteria.model.Categoria;
import com.cafeteria.cafeteria.model.Produto;
import com.cafeteria.cafeteria.repository.ProdutoRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    //pra converter Produto em ProdutoResponse
    private ProdutoResponseDto converterParaResponse(Produto produto) {
        return new ProdutoResponseDto(
                produto.getId(),
                produto.getNome(),
                produto.getDescricao(),
                produto.getPreco(),
                produto.getDisponivel(),
                produto.getCategoria()
        );
    }

    // Esse @ deixa atômico (ou salva tudo ou não salva nada)
    @Transactional
    public ProdutoResponseDto criarProduto(ProdutoRequestDto produto) {

        Produto produto1 = new Produto();

        produto1.setNome(produto.nome());
        produto1.setDescricao(produto.descricao());
        produto1.setCategoria(produto.categoria());
        produto1.setDisponivel(produto.disponivel());
        produto1.setPreco(produto.preco());

        Produto produtoSalvo = produtoRepository.save(produto1);

        return converterParaResponse(produtoSalvo);
    }

    public List<ProdutoResponseDto> listar() {

        List<Produto> produtos = produtoRepository.findAll();

        List<ProdutoResponseDto> response = new ArrayList<>();

        for (Produto prod : produtos) {
            response.add(converterParaResponse(prod));
        }
        return response;
    }

    public List<ProdutoResponseDto> buscarPorCategoria(Categoria categoria){

        List<Produto> produtos = produtoRepository.findByCategoria(categoria);

        List<ProdutoResponseDto> response = new ArrayList<>();

        for (Produto prod : produtos){
            response.add(converterParaResponse(prod));
        }
        return response;
    }

    public ProdutoResponseDto buscarPorId(Long id) {
        Optional<Produto> produto = this.produtoRepository.findById(id);

        Produto produto1 = produto.orElseThrow(() -> new RuntimeException(
                "O Produto de id: " + id + " e tipo: " + Produto.class.getName() + " não foi encontrado"
        ));

        return converterParaResponse(produto1);

    }

    @Transactional
    public ProdutoResponseDto atualizar(Long id, ProdutoRequestDto produtoDoUsuario){
        Optional<Produto> produtoOpitional = this.produtoRepository.findById(id);

        Produto produto1 = produtoOpitional.orElseThrow(() -> new RuntimeException(
                "O Produto de id: " + id + " e tipo: " + Produto.class.getName() + " não foi encontrado"
        ));

        produto1.setNome(produtoDoUsuario.nome());
        produto1.setDescricao(produtoDoUsuario.descricao());
        produto1.setCategoria(produtoDoUsuario.categoria());
        produto1.setDisponivel(produtoDoUsuario.disponivel());
        produto1.setPreco(produtoDoUsuario.preco());

        Produto produtoSalvo = produtoRepository.save(produto1);

        return converterParaResponse(produtoSalvo);

    }

    @Transactional
    public void excluir(Long id){

        Optional<Produto> produto = this.produtoRepository.findById(id);

        Produto produto1 = produto.orElseThrow(() -> new RuntimeException(
                "O Produto de id: " + id + " e tipo: " + Produto.class.getName() + " não foi encontrado"
        ));

        produtoRepository.delete(produto1);
    }
}
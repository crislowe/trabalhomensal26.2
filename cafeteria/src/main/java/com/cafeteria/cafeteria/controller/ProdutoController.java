package com.cafeteria.cafeteria.controller;

import com.cafeteria.cafeteria.dto.ProdutoRequestDto;
import com.cafeteria.cafeteria.dto.ProdutoResponseDto;
import com.cafeteria.cafeteria.model.Categoria;
import com.cafeteria.cafeteria.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @PostMapping
    public ResponseEntity<ProdutoResponseDto> create(@RequestBody ProdutoRequestDto produto) {
        ProdutoResponseDto response = produtoService.criarProduto(produto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoResponseDto> search (@PathVariable Long id){
        ProdutoResponseDto response = produtoService.buscarPorId(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping()
    public ResponseEntity<List<ProdutoResponseDto>> searchAll (){
        List<ProdutoResponseDto> response = produtoService.listar();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/categoria")
    public ResponseEntity<List<ProdutoResponseDto>> searchCategory(@RequestParam Categoria categoria) {
        List<ProdutoResponseDto> response = produtoService.buscarPorCategoria(categoria);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProdutoResponseDto> update (@PathVariable Long id, @RequestBody ProdutoRequestDto produto){
        ProdutoResponseDto response = produtoService.atualizar(id, produto);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete (@PathVariable Long id){
        produtoService.excluir(id);
        return ResponseEntity.noContent().build();
    }

    //Tratamento para dar os erros certos
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> tratamentoNaoEncontrado(RuntimeException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

}

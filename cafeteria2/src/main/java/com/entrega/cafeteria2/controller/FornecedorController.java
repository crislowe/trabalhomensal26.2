package com.entrega.cafeteria2.controller;

import com.entrega.cafeteria2.dto.FornecedorRequestDto;
import com.entrega.cafeteria2.dto.FornecedorResponseDto;
import com.entrega.cafeteria2.service.FornecedorService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/fornecedores")
public class FornecedorController {

    private final FornecedorService fornecedorService;

    public FornecedorController(FornecedorService fornecedorService) {
        this.fornecedorService = fornecedorService;
    }

    @PostMapping("/save")
    public ResponseEntity<FornecedorResponseDto> criar(@RequestBody @Valid FornecedorRequestDto dto) {
        FornecedorResponseDto salvo = fornecedorService.salvar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<FornecedorResponseDto>> listarTodos() {
        List<FornecedorResponseDto> fornecedores = fornecedorService.listarTodos();
        return ResponseEntity.ok(fornecedores);
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<FornecedorResponseDto> buscarPorId(@PathVariable Long id) {
        FornecedorResponseDto fornecedor = fornecedorService.buscarPorId(id);
        return ResponseEntity.ok(fornecedor);
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<FornecedorResponseDto> atualizar(@PathVariable Long id, @RequestBody @Valid FornecedorRequestDto dto) {
        FornecedorResponseDto atualizado = fornecedorService.atualizar(id, dto);
        return ResponseEntity.ok(atualizado);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        fornecedorService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
package com.cafeteria.cafeteria.model;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;

@Entity
// já cria a tabela com o nome de produtos
@Table(name = "produtos")
@Getter
@Setter
// pra criar os contrutores automaticamente
@NoArgsConstructor
@AllArgsConstructor
public class Produto {

    @Id
    // gerando o id automaticamente
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String nome;
    private String descricao;
    private BigDecimal preco;
    private Boolean disponivel;

    @Enumerated(EnumType.STRING)
    private Categoria categoria;

}
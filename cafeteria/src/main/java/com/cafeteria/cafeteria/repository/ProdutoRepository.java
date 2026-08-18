package com.cafeteria.cafeteria.repository;

import com.cafeteria.cafeteria.model.Categoria;
import com.cafeteria.cafeteria.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    List<Produto> findByCategoria(Categoria categoria);

}
package com.entrega.cafeteria2.repository;

import com.entrega.cafeteria2.entity.Categoria;
import com.entrega.cafeteria2.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    List<Produto> findByCategoria(Categoria categoria);
}
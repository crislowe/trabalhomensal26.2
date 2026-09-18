package com.entrega.cafeteria2.repository;

import com.entrega.cafeteria2.entity.Fornecedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface FornecedorRepository extends JpaRepository<Fornecedor, Long> {
}

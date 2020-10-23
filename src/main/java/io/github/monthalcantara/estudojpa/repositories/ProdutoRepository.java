package io.github.monthalcantara.estudojpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.monthalcantara.estudojpa.domain.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Integer>{

}

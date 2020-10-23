package io.github.monthalcantara.estudojpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.monthalcantara.estudojpa.domain.Cidade;

public interface CidadeRepository extends JpaRepository<Cidade, Integer>{

}

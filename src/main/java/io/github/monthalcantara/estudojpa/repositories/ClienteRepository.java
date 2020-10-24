package io.github.monthalcantara.estudojpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.monthalcantara.estudojpa.domain.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer>{

}

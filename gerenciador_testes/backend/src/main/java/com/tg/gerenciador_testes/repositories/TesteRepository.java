package com.tg.gerenciador_testes.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tg.gerenciador_testes.model.Teste;

@Repository
public interface TesteRepository extends JpaRepository<Teste, Long>{

}

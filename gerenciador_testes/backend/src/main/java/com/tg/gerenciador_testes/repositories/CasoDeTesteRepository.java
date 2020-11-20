package com.tg.gerenciador_testes.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.tg.gerenciador_testes.dto.CasoDeTesteDTO;
import com.tg.gerenciador_testes.model.CasoDeTeste;

@Repository
public interface CasoDeTesteRepository extends JpaRepository<CasoDeTeste, Long>{
	
	@Query("select new com.tg.gerenciador_testes.dto.CasoDeTesteDTO(ct.id, ct.nome, ct.objetivo) from CasoDeTeste ct")
	List<CasoDeTesteDTO> buscarTodosCasosDeTeste();

}

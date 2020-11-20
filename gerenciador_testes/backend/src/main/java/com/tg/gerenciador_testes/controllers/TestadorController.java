package com.tg.gerenciador_testes.controllers;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tg.gerenciador_testes.dto.CasoDeTesteDTO;
import com.tg.gerenciador_testes.dto.MensagemDTO;
import com.tg.gerenciador_testes.model.CasoDeTeste;
import com.tg.gerenciador_testes.services.TestadorService;

@CrossOrigin()
@RestController
@RequestMapping("/api")
public class TestadorController {
	
	@Autowired
	private TestadorService service;
	
	@GetMapping("/teste")
	public ResponseEntity<String> teste() {
		service.teste();
		return ResponseEntity.ok().body("Executou os testes");
	}
	
	@PostMapping("/executar_testes")
	public ResponseEntity<MensagemDTO> executarTestes(@RequestBody CasoDeTesteDTO casoDeTesteDTO) throws IOException {
		MensagemDTO mensagem = service.executarTestes(casoDeTesteDTO);
		return ResponseEntity.status(HttpStatus.OK).body(mensagem);
	}
	
	@GetMapping("/buscarTodosCasosDeTeste")
	public ResponseEntity<List<CasoDeTesteDTO>> buscarTodosCasosDeTeste(){
		List<CasoDeTesteDTO> casosDeteste = service.buscarTodosCasosDeTeste();
		return ResponseEntity.ok().body(casosDeteste);
	}
	
	
	@GetMapping("/visualizar/{id}")
	public ResponseEntity<CasoDeTesteDTO> detalharCasoDeTeste(@PathVariable("id") Long idCasoDeTeste) throws Exception {
		CasoDeTesteDTO casoDeteste = service.detalharCasoDeTeste(idCasoDeTeste);
		return ResponseEntity.ok().body(casoDeteste);
	}
	
	@DeleteMapping("/deletarCasoDeTestePorId/{id}")
	public ResponseEntity<Void> deletarCasoDeTestePorId(final @PathVariable("id") Long idCasoDeTeste){
		service.deletarCasoDeTestePorId(idCasoDeTeste);
		return ResponseEntity.noContent().build();
	}
	
	@PostMapping("/inserir")
	public ResponseEntity<Long> inserir(@RequestBody CasoDeTesteDTO casoDeTesteDTO) throws Exception{
		CasoDeTeste casoDeTeste = service.inserir(casoDeTesteDTO);
		return ResponseEntity.ok().body(casoDeTeste.getId());
	}
	
	@PutMapping("/editar/{id}")
	public ResponseEntity<Long> editar(final @PathVariable("id") Long idCasoDeTeste, @RequestBody CasoDeTesteDTO casoDeTesteDTO) throws Exception {
		CasoDeTeste casoDeTeste = service.editar(casoDeTesteDTO);
		return  ResponseEntity.ok().body(casoDeTeste.getId());
	}
	
	@PostMapping("/clonar/{idCasoDeTeste}")
    public ResponseEntity<Long> clonar(final @PathVariable("idCasoDeTeste") Long idCasoDeTeste) throws Exception {
        CasoDeTeste casoDeTeste = service.clonar(idCasoDeTeste);
        return  ResponseEntity.ok().body(casoDeTeste.getId());
    }

}

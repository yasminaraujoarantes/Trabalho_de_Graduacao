package com.tg.gerenciador_testes.controllers;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tg.gerenciador_testes.dto.CasoDeTesteDTO;
import com.tg.gerenciador_testes.dto.MensagemDTO;
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
	public ResponseEntity<MensagemDTO> inserir(@RequestBody CasoDeTesteDTO casoDeTeste) throws IOException {
		MensagemDTO mensagem = service.executarTestes(casoDeTeste);
		return ResponseEntity.status(HttpStatus.OK).body(mensagem);
	}

}

package com.tg.gerenciador_testes.dto;

import java.util.List;

public class CasoDeTesteDTO {
	
	private Long id;
	
	private String nome;
	
	private String objetivo;
	
	private List<TesteDTO> testes;
	
	public CasoDeTesteDTO() {}
	
	public CasoDeTesteDTO(Long id, String nome, String objetivo) {
		super();
		this.id = id;
		this.nome = nome;
		this.objetivo = objetivo;
	}

	public CasoDeTesteDTO(Long id, String nome, String objetivo, List<TesteDTO> testes) {
		super();
		this.id = id;
		this.nome = nome;
		this.objetivo = objetivo;
		this.testes = testes;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<TesteDTO> getTestes() {
		return testes;
	}

	public void setTestes(List<TesteDTO> testes) {
		this.testes = testes;
	}

	public String getObjetivo() {
		return objetivo;
	}

	public void setObjetivo(String objetivo) {
		this.objetivo = objetivo;
	}
	
}

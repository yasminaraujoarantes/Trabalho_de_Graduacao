package com.tg.gerenciador_testes.dto;

public class MensagemDTO {
	
	private String titulo;
	
	private String descricao;
	
	private String tipo;
	
	public MensagemDTO() {}
	
	public MensagemDTO(String titulo, String descricao, String tipo) {
		super();
		this.titulo = titulo;
		this.descricao = descricao;
		this.tipo = tipo;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
}

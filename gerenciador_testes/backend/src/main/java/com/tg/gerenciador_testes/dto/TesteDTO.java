package com.tg.gerenciador_testes.dto;

public class TesteDTO {
	
	private Long id;
	
	private String action;
	
	private String byType;
	
	private String element;
	
	private String url;
	
	private String textInput;
	
	private String saidaEsperada;
	
	public TesteDTO() {}
	
	public TesteDTO(Long id, String action, String byType, String element, String url, String textInput, String saidaEsperada) {
		super();
		this.id = id;
		this.action = action;
		this.byType = byType;
		this.element = element;
		this.url = url;
		this.textInput = textInput;
		this.saidaEsperada = saidaEsperada;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getByType() {
		return byType;
	}

	public void setByType(String byType) {
		this.byType = byType;
	}

	public String getElement() {
		return element;
	}

	public void setByElement(String element) {
		this.element = element;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getTextInput() {
		return textInput;
	}

	public void setTextInput(String textInput) {
		this.textInput = textInput;
	}
	
	public String getSaidaEsperada() {
		return saidaEsperada;
	}

	public void setSaidaEsperada(String saidaEsperada) {
		this.saidaEsperada = saidaEsperada;
	}
	
}

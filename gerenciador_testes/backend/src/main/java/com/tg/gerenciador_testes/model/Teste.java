package com.tg.gerenciador_testes.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="teste")
public class Teste {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
	private Long id;
	
	@Column(name="action")
	private String action;
	
	@Column(name="by_type")
	private String byType;
	
	@Column(name="element")
	private String element;
	
	@Column(name="url")
	private String url;
	
	@Column(name="text_input")
	private String textInput;
	
	@Column(name="saida_esperada")
	private String saidaEsperada;
	
	@ManyToOne(fetch= FetchType.LAZY)
	@JoinColumn(name="id_caso_teste")
	@JsonManagedReference
	private CasoDeTeste casoDeteste;
	
	public Teste() {}
	
	public Teste(Long id, String action, String byType, String element, String url, String textInput, String saidaEsperada, CasoDeTeste casoDeteste) {
		super();
		this.id = id;
		this.action = action;
		this.byType = byType;
		this.element = element;
		this.url = url;
		this.textInput = textInput;
		this.saidaEsperada = saidaEsperada;
		this.casoDeteste = casoDeteste;
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

	public void setElement(String element) {
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

	public CasoDeTeste getCasoDeteste() {
		return casoDeteste;
	}

	public void setCasoDeteste(CasoDeTeste casoDeteste) {
		this.casoDeteste = casoDeteste;
	}
	
}

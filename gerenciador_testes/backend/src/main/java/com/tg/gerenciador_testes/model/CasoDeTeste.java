package com.tg.gerenciador_testes.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="caso_teste")
public class CasoDeTeste {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
	private Long id;
	
	@Column(name="nome")
	private String nome;
	
	@Column(name="objetivo")
	private String objetivo;
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="casoDeteste", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Teste> testes;
	
	public CasoDeTeste() {}

	public CasoDeTeste(Long id, String nome, String objetivo, List<Teste> testes) {
		super();
		this.id = id;
		this.nome = nome;
		this.testes = testes;
		this.objetivo = objetivo;
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

	public String getObjetivo() {
		return objetivo;
	}

	public void setObjetivo(String objetivo) {
		this.objetivo = objetivo;
	}

	public List<Teste> getTestes() {
		return testes;
	}
	
	public void setTestes(List<Teste> testes) {
		if (this.testes != null) {
			this.testes.clear();
			this.testes.addAll(testes);
		}else {
			this.testes = testes;
		}
	}

}

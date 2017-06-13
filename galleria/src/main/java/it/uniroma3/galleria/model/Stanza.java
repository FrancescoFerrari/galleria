package it.uniroma3.galleria.model;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Stanza {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	@NotNull
	@Size(min=1)
	private String nome;
	@OneToMany(mappedBy="stanza")
	private List<Opera> opere;
	
	
	
	public Stanza(){
	}
	
	public Stanza(String nome) {
		super();
		this.nome = nome;
		this.opere = new LinkedList<>();
	}
	
	public Stanza(String nome, List<Opera> opere) {
		super();
		this.nome = nome;
		this.opere =opere;
	}

	public Long getId() {
		return id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public List<Opera> getOpere() {
		return opere;
	}
	public void setOpere(List<Opera> opere) {
		this.opere = opere;
	}
	
}
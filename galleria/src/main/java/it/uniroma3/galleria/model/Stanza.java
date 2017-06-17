package it.uniroma3.galleria.model;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Stanza implements Comparable<Stanza> {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	@NotNull
	@Size(min=1)
	private String nome;
	@OneToMany(mappedBy="stanza",cascade=CascadeType.MERGE)
	private List<Opera> opere;
	@Size(max=1000)
	private String urlImmagine;
	
	
	
	public Stanza(){
	}
	
	public Stanza(String nome,String url) {
		super();
		this.nome = nome;
		this.urlImmagine=url;
		this.opere = new LinkedList<>();
	}
	
	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	public Stanza(String nome, List<Opera> opere) {
		super();
		this.nome = nome;
		this.opere =opere;
	}

	public Long getId() {
		return id;
	}
	


	public String getUrlImmagine() {
		return urlImmagine;
	}

	public void setUrlImmagine(String urlImmagine) {
		this.urlImmagine = urlImmagine;
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

	@Override
	public int compareTo(Stanza that) {
		return this.getNome().toUpperCase().compareTo(that.getNome().toUpperCase());
	}
	
	
	
}
package it.uniroma3.galleria.model;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.CascadeType;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

@Entity
public class Autore {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
   
   
    @NotNull
    @Size(min=1)
    private String nomeAutore;

 
    @NotNull
    @Size(min=1)
    private String nazionalita;

    @NotNull
    @Temporal(TemporalType.DATE)
    @Past
    private Date annoNascita;
    
    @Temporal(TemporalType.DATE)
    @Past
    private Date annoMorte;
    
    @NotNull
    @Size(max=1000)
    private String urlImmagine;
    
    @OneToMany(mappedBy="autore",cascade=CascadeType.REMOVE)
    private List<Opera> opereAutore;

	

    public Autore() {}
	
	public Autore(String nome, String nazionalita, Date annoNascita, Date annoMorte,String url) {
		this.nomeAutore = nome;
		this.nazionalita = nazionalita;
		this.annoNascita = annoNascita;
		this.opereAutore = new LinkedList<>();
		this.urlImmagine=url;
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

	public String getNazionalita() {
		return nazionalita;
	}

	public void setNazionalita(String nazionalita) {
		this.nazionalita = nazionalita;
	}

	public List<Opera> getOpereAutore() {
		return opereAutore;
	}

	public void setOpereAutore(List<Opera> opereAutore) {
		this.opereAutore = opereAutore;
	}

	public String getNomeAutore() {
		return nomeAutore;
	}

	public void setNomeAutore(String nome) {
		this.nomeAutore = nome;
	}

	public Date getAnnoNascita() {
		return annoNascita;
	}

	public void setAnnoNascita(Date annoNascita) {
		this.annoNascita = annoNascita;
	}

	public Date getAnnoMorte() {
		return annoMorte;
	}

	public void setAnnoMorte(Date annoMorte) {
		this.annoMorte = annoMorte;
	}

	@Override
	public String toString() {
		return "Artista [id=" + id + ", nome=" + nomeAutore + ", nazionalita=" + nazionalita + ", annoNascita=" + annoNascita	+ ", annoMorte=" + annoMorte + "]";
	}
	
	
	


}
package it.uniroma3.galleria.model;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

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
    private String nome;

    @NotNull
    @Size(min=1)
    private String cognome;

    @NotNull
    @Temporal(TemporalType.DATE)
    @Past
    private Date annoNascita;
    
    @Temporal(TemporalType.DATE)
    @Past
    private Date annoMorte;
    
    @OneToMany(mappedBy="autore")
    private List<Opera> opereAutore;

    protected Autore() {}
	
	public Autore(String nome, String cognome, Date annoNascita, Date annoMorte) {
		this.nome = nome;
		this.cognome = cognome;
		this.annoNascita = annoNascita;
		this.opereAutore = new LinkedList<>();
	}

	public List<Opera> getOpereAutore() {
		return opereAutore;
	}

	public void setOpereAutore(List<Opera> opereAutore) {
		this.opereAutore = opereAutore;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
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
		return "Artista [id=" + id + ", nome=" + nome + ", cognome=" + cognome + ", annoNascita=" + annoNascita
				+ ", annoMorte=" + annoMorte + "]";
	}
	
	
	


}
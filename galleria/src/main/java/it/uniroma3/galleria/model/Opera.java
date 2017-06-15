package it.uniroma3.galleria.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Opera implements Comparable<Opera> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min=1)
    private String nome;

    @NotNull
    @Size(min=1,max=2000)
    private String descrizione;
    
    @NotNull
    @Size(min=1)
    private String tecnica;
    @NotNull
    @Min(1)
    private Integer anno;
    
    @ManyToOne
    private Autore autore;
    
    @ManyToOne
    private Stanza stanza;
    
    @NotNull
    private double lunghezza;
    
    @NotNull
    private double larghezza;
    @Size(max=1000)
    @NotNull
    private String urlImmagine;
    

    /**
	 * @return the urlImmagine
	 */
	public String getUrlImmagine() {
		return urlImmagine;
	}

	/**
	 * @param urlImmagine the urlImmagine to set
	 */
	public void setUrlImmagine(String urlImmagine) {
		this.urlImmagine = urlImmagine;
	}

	protected Opera() {}
	
	public Opera(String nome, String descrizione,String tecnica, Integer anno, Autore autore, Stanza stanza, String urlImmagine,double lunghezza,double larghezza) {
		this.nome = nome;
		this.tecnica=tecnica;
		this.descrizione = descrizione;
		this.anno = anno;
		this.autore =autore;
		this.stanza=stanza;
		this.urlImmagine=urlImmagine;
		this.lunghezza=lunghezza;
		this.larghezza=larghezza;
	}	

	/**
	 * @return the stanza
	 */
	public Stanza getStanza() {
		return stanza;
	}

	/**
	 * @param stanza the stanza to set
	 */
	public void setStanza(Stanza stanza) {
		this.stanza = stanza;
	}

	/**
	 * @return the tecnica
	 */
	public String getTecnica() {
		return tecnica;
	}

	/**
	 * @param tecnica the tecnica to set
	 */
	public void setTecnica(String tecnica) {
		this.tecnica = tecnica;
	}

	/**
	 * @return the Autore
	 */
	public Autore getAutore() {
		return autore;
	}

	/**
	 * @param Autore the Autore to set
	 */
	public void setAutore(Autore autore) {
		this.autore = autore;
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

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public Integer getAnno() {
		return anno;
	}

	public void setAnno(Integer anno) {
		this.anno = anno;
	}
	
	public double getLunghezza() {
		return lunghezza;
	}

	public void setLunghezza(double lunghezza) {
		this.lunghezza = lunghezza;
	}

	public double getLarghezza() {
		return larghezza;
	}

	public void setLarghezza(double larghezza) {
		this.larghezza = larghezza;
	}

	@Override
    public String toString() {
        return String.format(
                "Opera[id=%d, nome='%s', descrizione='%s', anno=%d]",
                id, nome, descrizione, anno);
    }

	@Override
	public int compareTo(Opera that) {
		return this.nome.toUpperCase().compareTo(that.nome.toUpperCase());
	}


}
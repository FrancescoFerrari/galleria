package it.uniroma3.galleria.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.galleria.model.Autore;

public interface AutoreRepository extends CrudRepository<Autore,Long>{
	Autore findByNomeAutore(String nomeAutore);
	List<Autore> findByAnnoNascita(Date annoNascita);
	 void delete(Long id);
}

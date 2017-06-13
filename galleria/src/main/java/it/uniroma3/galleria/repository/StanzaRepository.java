package it.uniroma3.galleria.repository;


import org.springframework.data.repository.CrudRepository;

import it.uniroma3.galleria.model.Stanza;

public interface StanzaRepository extends CrudRepository<Stanza, Long> {

  	Stanza findByNome(String nome);
}
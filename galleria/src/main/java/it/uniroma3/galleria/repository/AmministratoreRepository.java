package it.uniroma3.galleria.repository;


import org.springframework.data.repository.CrudRepository;

import it.uniroma3.galleria.model.Amministratore;

public interface AmministratoreRepository extends CrudRepository<Amministratore, Long> {
   Amministratore findByUsername(String username);
       
}

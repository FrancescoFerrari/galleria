package it.uniroma3.galleria.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import it.uniroma3.galleria.model.Amministratore;
import it.uniroma3.galleria.repository.AmministratoreRepository;

@Service
public class AmministratoreService {

    @Autowired
    private AmministratoreRepository amministratoreRepository; 

    public Iterable<Amministratore> findAll() {
        return this.amministratoreRepository.findAll();
    }
    
    @Transactional
    public void add(final Amministratore user) {
        this.amministratoreRepository.save(user);
    }
    
    public Amministratore findByUsername(String username){
    	return this.amministratoreRepository.findByUsername(username);
    }
    
    public Amministratore findbyId(Long id) {
		return this.amministratoreRepository.findOne(id);
	}

}
package it.uniroma3.galleria.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.galleria.model.Stanza;
import it.uniroma3.galleria.repository.StanzaRepository;

@Service
public class StanzaService {

    @Autowired
    private StanzaRepository stanzaRepository; 

    public Iterable<Stanza> findAll() {
        return this.stanzaRepository.findAll();
    }

    @Transactional
    public void add(final Stanza opera) {
        this.stanzaRepository.save(opera);
    }

	public Stanza findbyId(Long id) {
		return this.stanzaRepository.findOne(id);
	}

}

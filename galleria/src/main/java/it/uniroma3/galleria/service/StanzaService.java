package it.uniroma3.galleria.service;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.galleria.model.Stanza;
import it.uniroma3.galleria.repository.StanzaRepository;

@Service
public class StanzaService {
	@Autowired
	private EntityManager em;
    @Autowired
    private StanzaRepository stanzaRepository; 

    public Iterable<Stanza> findAll() {
        return this.stanzaRepository.findAll();
    }
    public Stanza save(Stanza entity) {
		if (!em.contains(entity)) {
			em.persist(entity);
			return entity;
		} else {
			return em.merge(entity);
		}
	}
	@Transactional
	public void add(final Stanza stanza) {
		this.stanzaRepository.save(stanza);
	}

	public Stanza findbyId(Long id) {
		return this.stanzaRepository.findOne(id);
	}
	public void delete(Long id) {
		this.stanzaRepository.delete(id);

	}
}

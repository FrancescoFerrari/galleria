package it.uniroma3.galleria.service;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.galleria.model.Opera;
import it.uniroma3.galleria.repository.OperaRepository;

@Service
public class OperaService {
	@Autowired
	private EntityManager em;
	
	@Autowired
	private OperaRepository operaRepository; 

	public Iterable<Opera> findAll() {
		return this.operaRepository.findAll();
	}

	@Transactional
	public Opera save(Opera entity) {
		if (!em.contains(entity)) {
			em.persist(entity);
			return entity;
		} else {
			return em.merge(entity);
		}
	}
	@Transactional
	public void add(final Opera opera) {
		this.operaRepository.save(opera);
	}

	public Opera findbyId(Long id) {
		return this.operaRepository.findOne(id);
	}


}

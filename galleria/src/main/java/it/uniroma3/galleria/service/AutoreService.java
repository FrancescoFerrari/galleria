package it.uniroma3.galleria.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.galleria.model.Autore;
import it.uniroma3.galleria.repository.AutoreRepository;

@Service
public class AutoreService {
	  @Autowired
	    private AutoreRepository autoreRepository; 

	    public Iterable<Autore> findAll() {
	        return this.autoreRepository.findAll();
	    }

	    @Transactional
	    public void add(final Autore artista) {
	        this.autoreRepository.save(artista);
	    }

		public Autore findbyId(Long id) {
			return this.autoreRepository.findOne(id);
		}
		
		public Autore findbyName(String nomeAutore){
			return  this.autoreRepository.findByNomeAutore(nomeAutore);
		}
		@Transactional
		public void delete(Long id) {
			this.autoreRepository.delete(id);

		}
}

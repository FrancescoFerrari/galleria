package it.uniroma3.galleria.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.galleria.model.User;
import it.uniroma3.galleria.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository; 

    public Iterable<User> findAll() {
        return this.userRepository.findAll();
    }
    
    @Transactional
    public void add(final User user) {
        this.userRepository.save(user);
    }

}
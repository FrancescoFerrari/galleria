package it.uniroma3.galleria.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.galleria.model.User;

public interface UserRepository extends CrudRepository<User, Long> {

    List<User> findByUsername(String username);
       
}

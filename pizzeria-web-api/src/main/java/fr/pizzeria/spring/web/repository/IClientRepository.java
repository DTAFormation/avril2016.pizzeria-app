package fr.pizzeria.spring.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.pizzeria.model.Client;

public interface IClientRepository extends JpaRepository<Client, Integer>{
	Client findById(Integer cId);
    Client findByEmailAndMotDePasse(String email, String motDePasse);
}

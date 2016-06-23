package fr.pizzeria.spring.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.pizzeria.model.Client;
import fr.pizzeria.model.Pizza;

public interface IClientRepository extends JpaRepository<Client,Integer> {

}

package fr.pizzeria.spring.web.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.pizzeria.model.Client;
import fr.pizzeria.model.PizzaPerso;

public interface IPizzaPersoRepository extends JpaRepository<PizzaPerso,Integer> {
	List<PizzaPerso> findByCreateur (Client client);
}
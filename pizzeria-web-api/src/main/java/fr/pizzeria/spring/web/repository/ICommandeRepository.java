package fr.pizzeria.spring.web.repository;

import java.util.List;

import fr.pizzeria.model.Client;
import fr.pizzeria.model.Commande;

public interface ICommandeRepository {
	List<Commande> findByClient(Client client);

}

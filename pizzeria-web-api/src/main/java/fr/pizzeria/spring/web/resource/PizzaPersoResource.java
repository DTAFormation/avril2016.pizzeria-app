package fr.pizzeria.spring.web.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import fr.pizzeria.model.Client;
import fr.pizzeria.model.PizzaPerso;
import fr.pizzeria.spring.web.repository.IClientRepository;
import fr.pizzeria.spring.web.repository.IPizzaPersoRepository;

/**
 * 
 */
@RestController
@RequestMapping("/perso")
public class PizzaPersoResource {

	@Autowired private IPizzaPersoRepository pizzaPersoDao;
	@Autowired private IClientRepository clientDao;

	@RequestMapping(path= "/{cId}", method = RequestMethod.GET)
	public List<PizzaPerso> listAllPizzas(@PathVariable Integer cId) {
		Client cl = clientDao.findById(cId);
		return pizzaPersoDao.findByCreateur(cl);
	}
}

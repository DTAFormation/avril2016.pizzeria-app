package fr.pizzeria.spring.web.resource;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import fr.pizzeria.model.Pizza;

/**
 * 
 */
@RestController
@RequestMapping("/perso")
public class PizzaPersoResource {

	//@Autowired private IPizzaRepository pizzaDao;

	@RequestMapping(method = RequestMethod.GET)
	public List<Pizza> listAllPizzas() {
		return new ArrayList<Pizza>();
	}
}

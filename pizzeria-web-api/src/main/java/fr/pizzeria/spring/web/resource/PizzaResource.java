package fr.pizzeria.spring.web.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import fr.pizzeria.model.Ingredient;
import fr.pizzeria.model.Pizza;
import fr.pizzeria.spring.web.repository.IPizzaRepository;

/**
 * Resource Pizza.
 */
@RestController
@RequestMapping("/pizzas")
public class PizzaResource {

	@Autowired
	private IPizzaRepository pizzaDao;

	@RequestMapping(method = RequestMethod.GET)
	public List<Pizza> listAllPizzas() {
		return pizzaDao.findAll();
	}

	@RequestMapping(path = "/{code}", method = RequestMethod.GET)
	public Pizza findPizzas(@PathVariable String code) {
		return pizzaDao.findByCode(code);
	}
}

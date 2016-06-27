package fr.pizzeria.spring.web.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import fr.pizzeria.model.Ingredient;
import fr.pizzeria.spring.web.repository.IIngredientRepository;

@RestController
@RequestMapping("/ingredients")
public class IngredientResource {
	
	@Autowired private IIngredientRepository ingredientDao;

	@RequestMapping(method = RequestMethod.GET)
	public List<Ingredient> listAllIngredients() {
		return ingredientDao.findAll();
	}
	
}

package fr.pizzeria.spring.web.resource;

<<<<<<< HEAD
import fr.pizzeria.model.Pizza;
import fr.pizzeria.spring.web.repository.IPizzaRepository;
import java.util.List;
=======
import java.util.List;

>>>>>>> refs/remotes/origin/master
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

<<<<<<< HEAD
=======
import fr.pizzeria.model.Pizza;
import fr.pizzeria.spring.web.repository.IPizzaRepository;

>>>>>>> refs/remotes/origin/master
/**
 * Resource Pizza.
 */
@RestController
@RequestMapping("/pizzas")
public class PizzaResource {

<<<<<<< HEAD
  @Autowired private IPizzaRepository pizzaDao;

  @RequestMapping(method = RequestMethod.GET)
  public List<Pizza> listAllPizzas() {
    return pizzaDao.findAll();
  }
=======
	@Autowired private IPizzaRepository pizzaDao;

	@RequestMapping(method = RequestMethod.GET)
	public List<Pizza> listAllPizzas() {
		return pizzaDao.findAll();
	}
>>>>>>> refs/remotes/origin/master

}

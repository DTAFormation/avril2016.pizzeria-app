package fr.pizzeria.spring.web.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import fr.pizzeria.model.Client;
import fr.pizzeria.model.Commande;
import fr.pizzeria.model.CommandePizza;
import fr.pizzeria.spring.web.repository.IClientRepository;
import fr.pizzeria.spring.web.repository.ICommandeRepository;
import fr.pizzeria.spring.web.repository.IPizzaRepository;

/**
 * Resource Commande.
 */
@RestController
@RequestMapping("/commandes")
public class CommandeResource {

	@Autowired
	private ICommandeRepository commandeDao;
	@Autowired
	private IClientRepository clientDao;
	@Autowired
	private IPizzaRepository pizzaDao;

	@RequestMapping(path = "/{cId}", method = RequestMethod.GET)
	public List<Commande> listAllCommandesClient(@PathVariable Integer cId) {
		Client cl = clientDao.findById(cId);

		List<Commande> commandes = commandeDao.findByClient(cl);
		commandes.forEach(c -> {
			c.getPizzas().forEach(cp -> {
				cp.setPizza(pizzaDao.findById(cp.getPizzaId()));
			});
		});
		
		return commandes;
	}

	@RequestMapping(method = RequestMethod.POST)
	public Commande addCommande(@RequestBody Commande commande) {
		List<CommandePizza> pizzas = commande.getPizzas();
		commande.setPizzas(null);
		Commande commandAdded = commandeDao.save(commande);
		
		// Ajout des pizzas commandées (CommandePizza)
		commandAdded.setPizzas(pizzas);
		commandAdded.getPizzas().forEach(cp -> {
			cp.setCommandeId(commande.getId());
			cp.setCommande(commandAdded);
			cp.setPizza(pizzaDao.findById(cp.getPizzaId()));
		});
		
		return commandeDao.save(commandAdded);
	}

}

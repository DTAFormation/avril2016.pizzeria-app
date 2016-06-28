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
import fr.pizzeria.spring.web.repository.IClientRepository;
import fr.pizzeria.spring.web.repository.ICommandeRepository;

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

	@RequestMapping(path = "/{cId}", method = RequestMethod.GET)
	public List<Commande> listAllCommandesClient(@PathVariable Integer cId) {
		Client cl = clientDao.findById(cId);

		return commandeDao.findByClient(cl);
	}

	@RequestMapping(method = RequestMethod.POST)
	public Commande addCommande(@RequestBody Commande commande) {
		return commandeDao.save(commande);
	}

}

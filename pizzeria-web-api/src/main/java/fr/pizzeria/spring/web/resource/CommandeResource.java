package fr.pizzeria.spring.web.resource;

import fr.pizzeria.model.Client;
import fr.pizzeria.model.Commande;
import fr.pizzeria.spring.web.repository.ICommandeRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Resource Commande.
 */
@RestController
@RequestMapping("/commandes")
public class CommandeResource {

  @Autowired private ICommandeRepository commandeDao;

  @RequestMapping(method = RequestMethod.GET)
  public List<Commande> listAllCommandesClient(Client c) {
    return commandeDao.findByClient(c);
  }

}

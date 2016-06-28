package fr.pizzeria.spring.web.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import fr.pizzeria.model.Client;
import fr.pizzeria.spring.web.repository.IClientRepository;

@RestController
@RequestMapping("/clients")
public class ClientResource {

	@Autowired
	private IClientRepository clientDao;

	@RequestMapping(method = RequestMethod.GET)
	public List<Client> listAllClient() {
		return clientDao.findAll();
	}

	@RequestMapping(method = RequestMethod.POST)
	public Client saveClient(@RequestBody Client client) {

		try {
			return clientDao.save(client);

		} catch (Exception e) {
		}
		return null;

	}

	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<?> updateClient(@RequestBody Client client) {
		if (client.isValide()) {
			clientDao.save(client);
		}
		return client.isValide() ? ResponseEntity.ok(client) : ResponseEntity.unprocessableEntity().build();
	}
}
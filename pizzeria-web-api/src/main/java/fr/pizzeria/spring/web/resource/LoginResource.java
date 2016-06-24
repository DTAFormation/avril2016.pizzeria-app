package fr.pizzeria.spring.web.resource;

import java.util.List;
import fr.pizzeria.spring.web.repository.IClientRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.pizzeria.model.Client;






@RestController
@RequestMapping("/login")
public class LoginResource {

	@Autowired
	private IClientRepository clientDao;

	@RequestMapping(method = RequestMethod.POST)
	public Client LogClient(@RequestBody Client client) {
		Client login = clientDao.findByEmailAndMotDePasse(client.getEmail(), client.getMotDePasse());
			return login;
		//test en cours pour avoir une reponse personnalisé en cas de login invalide
//		requete a faire pour avoir un login :
//		POST	http://localhost:8080/login
//			avec un client qui est dans la base de donnée dans le corps de la requete
			
	}

	
	
//	@RequestMapping(method = RequestMethod.POST)
//	public Client LogClient(@RequestBody Client client) {
//		Client login = clientDao.findByEmailAndMotDePasse(client.getEmail(), client.getMotDePasse());
//		System.out.println("test ");
//		System.out.println(login);
//		if (login != null) {
//			return login;
//		} else {
//			return new ResponseEntity(HttpStatus.NO_CONTENT);
//		}
//
//		// return new ResponseEntity(HttpStatus.NOT_ACCEPTABLE);
//		// return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
//		// return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
//		// return (Client) ResponseEntity.notFound();
//		// return login;
//
//	}
	
	
	
	
}

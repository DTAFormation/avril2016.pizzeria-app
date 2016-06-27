package fr.pizzeria.spring.web;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.Client;
import fr.pizzeria.model.Pizza;
import fr.pizzeria.spring.web.repository.IClientRepository;
import fr.pizzeria.spring.web.repository.IPizzaRepository;

@Component
public class ApplicationStartedEventListener {

	@Autowired
	public ApplicationStartedEventListener(IPizzaRepository pizzaDao, IClientRepository clientDao) {
		super();
		System.err.println("bean ApplicationStartedEventListener");
		initPizzas(pizzaDao);
		initClient(clientDao);
	}

	private void initClient(IClientRepository clientDao) {
		List<Client> clients = new ArrayList<>();
		clients.add(new Client("Perchaud", "Samuel", "test@gmail.com", "aaa"));
		clients.add(new Client("Momo", "Antonin", "testos@gmail.com", "bbb"));
		clients.forEach(p -> {
		try {
			clientDao.save(p);

		} catch (Exception e) {
		}
		 });
		
	}

	public void initPizzas(IPizzaRepository pizzaDao) {
		List<Pizza> pizzas = new ArrayList<>();
		System.err.println("test init");
		pizzas.add(new Pizza("Fro", "4 Fromages", new BigDecimal("15.00"), CategoriePizza.VIANDE));
		pizzas.add(new Pizza("Bol", "Polognese", new BigDecimal("12.00"), CategoriePizza.SANS_VIANDE));
		pizzas.add(new Pizza("Pec", "Pecheur", new BigDecimal("10.00"), CategoriePizza.VIANDE));
		pizzas.add(new Pizza("Reg", "Regina", new BigDecimal("16.00"), CategoriePizza.SANS_VIANDE));
		pizzas.add(new Pizza("Sai", "4 Saisons", new BigDecimal("15.00"), CategoriePizza.VIANDE));
		pizzas.add(new Pizza("Rei", "Reine", new BigDecimal("12.00"), CategoriePizza.VIANDE));
		pizzas.add(new Pizza("Cho", "Chorizo", new BigDecimal("12.00"), CategoriePizza.VIANDE));
		pizzas.forEach(p -> {
		try {
			pizzaDao.save(p);

		} catch (Exception e) {
		}
		 });
	}

}
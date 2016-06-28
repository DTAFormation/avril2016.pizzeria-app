package fr.pizzeria.spring.web;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
		pizzas.add(new Pizza("Fro", "4 Fromages", new BigDecimal("15.00"), CategoriePizza.VIANDE,
				"images/4fromages.jpg",
				"Subtile association de quatre fromages et de sauce tomate épicée mais non pimentée pour ce grand classique de la pizza revu par notre pizzeria. Amateurs de fromages, cette pizza est faîte pour vous ! "));
		pizzas.add(new Pizza("Bol", "Polognese", new BigDecimal("12.00"), CategoriePizza.SANS_VIANDE,"images/bolognese.jpg","Fondue de mozzarella et d’emmental râpé sur de tendres morceaux de poulet rôti et rondelles de saucisse Pepperoni, le tout parfaitement associé à la sauce tomate justement dosée en épices et herbes aromatiques."));
		pizzas.add(new Pizza("Pec", "Pecheur", new BigDecimal("10.00"), CategoriePizza.VIANDE,"images/pecheur.jpg","Faîtes le plein de fruits de mer en une bouchée de cette somptueuse pizza du pêcheur ! Une pizza à garder dans vos favoris !La pizza aux fruits de mer est vraiment un régal ! Du thon ou du saumon."));
		pizzas.add(new Pizza("Reg", "Regina", new BigDecimal("16.00"), CategoriePizza.SANS_VIANDE,"images/regina.jpg","Saveurs estivales pour cette recette riche en légumes et haute en couleurs. La pizza Regina est une association généreuse et gourmande d’ingrédients faisant référence aux pays méditerranéens."));
		pizzas.add(new Pizza("Sai", "4 Saisons", new BigDecimal("15.00"), CategoriePizza.VIANDE,"images/4saisons.jpg","Une pizza dans laquelle vous retrouverez les saveurs du printemps, de l'été, de l'automne et de l'hiver.Tomates, fromage, épaule, poivrons, champignons, artichauts, olives."));
		pizzas.add(new Pizza("Rei", "Reine", new BigDecimal("12.00"), CategoriePizza.VIANDE,"images/reine.jpg","Cette pizza fait partie des grands classiques mais quand sa recette est revue par notre pizzeria, La Reine gagne en goût et en tenue. Sauce tomate exclusive, mozzarella fondante, champignons frais..."));
		pizzas.add(new Pizza("Cho", "Chorizo", new BigDecimal("12.00"), CategoriePizza.VIANDE,"images/chorizo.jpg","Généreusement garnie en viande, le chorizo fait honneur aux carnivores. Pas moins de trois sortes de viande se côtoient sur cette pizza relevées d’une sauce barbecue préparée exclusivement pour vous."));
		pizzas.forEach(p -> {
			try {
				pizzaDao.save(p);

			} catch (Exception e) {
			}
		});
	}

}
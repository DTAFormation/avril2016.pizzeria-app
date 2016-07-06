package fr.pizzeria.spring.web;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.Client;
import fr.pizzeria.model.Ingredient;
import fr.pizzeria.model.Pizza;
import fr.pizzeria.spring.web.repository.IClientRepository;
import fr.pizzeria.spring.web.repository.IIngredientRepository;
import fr.pizzeria.spring.web.repository.IPizzaRepository;

@Component
public class ApplicationStartedEventListener {

	@Autowired
	public ApplicationStartedEventListener(IPizzaRepository pizzaDao, IClientRepository clientDao, IIngredientRepository ingredientDao) {
		super();
		System.err.println("bean ApplicationStartedEventListener");
		initPizzas(pizzaDao, ingredientDao);
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

	public void initPizzas(IPizzaRepository pizzaDao, IIngredientRepository ingredientDao) {
		List<Pizza> pizzas = new ArrayList<>();
		System.err.println("test init");
		Ingredient saumon = new Ingredient("SAM", "saumon", 3.0);
		Ingredient fromage = new Ingredient("FRO", "fromage", 1.0);
		Ingredient tomate = new Ingredient("TOM", "tomate", 2.0);
		Ingredient oeuf = new Ingredient("OEU", "oeuf", 2.0);
		Ingredient poulet = new Ingredient("POU", "poulet", 2.0);
		ingredientDao.save(saumon);
		ingredientDao.save(fromage);
		ingredientDao.save(tomate);
		ingredientDao.save(oeuf);
		ingredientDao.save(poulet);
		
		
		Pizza pizza1 = new Pizza("Fro", "4 Fromages", new BigDecimal("15.00"), CategoriePizza.VIANDE,
				"images/4fromages.jpg",
				"Subtile association de quatre fromages et de sauce tomate épicée mais non pimentée pour ce grand classique de la pizza revu par notre pizzeria. Amateurs de fromages, cette pizza est faîte pour vous ! ");
		pizza1
			.addIngredient(tomate)
			.addIngredient(fromage)
			.addIngredient(oeuf);
		
		Pizza pizza2 = new Pizza("Bol", "Polognese", new BigDecimal("12.00"), CategoriePizza.SANS_VIANDE,"images/bolognese.jpg","Fondue de mozzarella et d’emmental râpé sur de tendres morceaux de poulet rôti et rondelles de saucisse Pepperoni, le tout parfaitement associé à la sauce tomate justement dosée en épices et herbes aromatiques.");
		pizza2
			.addIngredient(poulet)
			.addIngredient(tomate)
			.addIngredient(oeuf);
		
		Pizza pizza3 = new Pizza("Pec", "Pecheur", new BigDecimal("10.00"), CategoriePizza.VIANDE,"images/pecheur.jpg","Faîtes le plein de fruits de mer en une bouchée de cette somptueuse pizza du pêcheur ! Une pizza à garder dans vos favoris !La pizza aux fruits de mer est vraiment un régal ! Du thon ou du saumon.");
		pizza3
			.addIngredient(saumon)
			.addIngredient(fromage);
		
		Pizza pizza4 = new Pizza("Reg", "Regina", new BigDecimal("16.00"), CategoriePizza.SANS_VIANDE,"images/regina.jpg","Saveurs estivales pour cette recette riche en légumes et haute en couleurs. La pizza Regina est une association généreuse et gourmande d’ingrédients faisant référence aux pays méditerranéens.");
		pizza4
			.addIngredient(fromage)
			.addIngredient(oeuf);
		
		Pizza pizza5 = new Pizza("Sai", "4 Saisons", new BigDecimal("15.00"), CategoriePizza.VIANDE,"images/4saisons.jpg","Une pizza dans laquelle vous retrouverez les saveurs du printemps, de l'été, de l'automne et de l'hiver.Tomates, fromage, épaule, poivrons, champignons, artichauts, olives.");
		pizza5
			.addIngredient(fromage)
			.addIngredient(tomate)
			.addIngredient(oeuf);
		
		Pizza pizza6 = new Pizza("Rei", "Reine", new BigDecimal("12.00"), CategoriePizza.VIANDE,"images/reine.jpg","Cette pizza fait partie des grands classiques mais quand sa recette est revue par notre pizzeria, La Reine gagne en goût et en tenue. Sauce tomate exclusive, mozzarella fondante, champignons frais...");
		pizza6
			.addIngredient(fromage)
			.addIngredient(tomate)
			.addIngredient(oeuf)
			.addIngredient(poulet);
		
		Pizza pizza7 = new Pizza("Cho", "Chorizo", new BigDecimal("12.00"), CategoriePizza.VIANDE,"images/chorizo.jpg","Généreusement garnie en viande, le chorizo fait honneur aux carnivores. Pas moins de trois sortes de viande se côtoient sur cette pizza relevées d’une sauce barbecue préparée exclusivement pour vous.");
		pizza7
			.addIngredient(poulet)
			.addIngredient(fromage);
		
		pizzas.add(pizza1);
		pizzas.add(pizza2);
		pizzas.add(pizza3);
		pizzas.add(pizza4);
		pizzas.add(pizza5);
		pizzas.add(pizza6);
		pizzas.add(pizza7);
		pizzas.forEach(p -> {
			try {
				pizzaDao.save(p);

			} catch (Exception e) {
			}
		});
	}

}
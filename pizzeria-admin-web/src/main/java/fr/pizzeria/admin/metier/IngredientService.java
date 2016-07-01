package fr.pizzeria.admin.metier;

import java.util.List;

import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import fr.pizzeria.model.Ingredient;
import fr.pizzeria.model.Pizza;

@Stateless
public class IngredientService {

	@PersistenceContext
	protected EntityManager em;

	@Inject
	private PizzaService pizzaService;



	/**
	 * on récupère tout les ingredients qui sont actives (actif = true)
	 * 
	 * @return
	 */
	public List<Ingredient> findAll() {
		return em.createQuery("select i from Ingredient i where actif = true", Ingredient.class).getResultList();
	}

	public Ingredient findOneIngredient(String code) {
		return em.createQuery("select i from Ingredient i where i.code=:code and actif = true", Ingredient.class)
				.setParameter("code", code).getSingleResult();
	}

	public void updateIngredient(String code, Ingredient ingredientAvecCode) {
		Ingredient ing = findOneIngredient(code); // vérifie qu'une pizza est présente
		ing.setNom(ingredientAvecCode.getNom());
		em.merge(ing);
	}

	public boolean saveIngredient(Ingredient ingredientSansId) {
		try {
			findOneIngredient(ingredientSansId.getCode());
			return false;
		} catch (NoResultException | EJBException e) {
			em.persist(ingredientSansId);
			return true;
		}
	}

	public void deleteIngredient(String code) {
		List<Pizza> listPizzas = pizzaService.findAll();
		Ingredient ing = findOneIngredient(code);
		
		for( Pizza pizza : listPizzas){
			List<Ingredient> listeIngredientsPizza = pizza.getIngredients();
			if (!listeIngredientsPizza.contains(ing)){
				continue;
			}
			listeIngredientsPizza.remove(ing);
			pizzaService.updatePizza(pizza.getCode(), pizza);
		}

		ing.setActif(false);
		em.merge(ing);
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}

	public void setPizzaService(PizzaService pizzaService) {
		this.pizzaService = pizzaService;		
	}
}
package fr.pizzeria.admin.metier;

import fr.pizzeria.model.Ingredient;
import fr.pizzeria.model.Pizza;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class PizzaService {

	@PersistenceContext
	protected EntityManager em;

	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}

	/**
	 * on recupere toutes les pizzas actives (actif = true)
	 * 
	 * @return
	 */
	public List<Pizza> findAll() {
		return em.createQuery("select p from Pizza p where actif = true", Pizza.class).getResultList();
	}

	public List<Pizza> findAllWithIngredient() {
		List<Pizza> pizzas = em.createQuery("select p from Pizza p where actif = true", Pizza.class).getResultList();
		for (Pizza pizza : pizzas) {
			if (pizza.getIngredients().iterator().hasNext()) {
				pizza.getIngredients().iterator().next();
			}
		}
		return pizzas;
	}

	public Pizza findOnePizza(String code) {
		return em.createQuery("select p from Pizza p where p.code=:code and actif = true", Pizza.class).setParameter("code", code).getSingleResult();
	}

	public Pizza findOnePizzaWithIngredients(String code) {
		Pizza pizza = em.createQuery("select p from Pizza p where p.code=:code and actif = true", Pizza.class).setParameter("code", code).getSingleResult();
		// simulation de recupération des ingrédients (requetes)
		if (pizza.getIngredients().iterator().hasNext()) {
			pizza.getIngredients().iterator().next();
		}
		return pizza;
	}

	public List<Pizza> isCodeTaken(String code) {
		return em.createQuery("select p from Pizza p where p.code=:code and actif = true", Pizza.class).setParameter("code", code).getResultList();
	}

	public void updatePizza(String code, Pizza pizzaAvecId) {
		Pizza p = findOnePizza(code); // vérifie qu'une pizza est présente

		p.setActif(false);
		Pizza newPizza = pizzaAvecId.copy();
		newPizza.setId(null);
		em.merge(p);
		em.persist(newPizza);
	}

	public void savePizza(Pizza pizzaSansId) {
		em.persist(pizzaSansId);
	}

	public void deletePizza(String code) {
		Pizza p = findOnePizza(code); // vérifie qu'une pizza est présente
		p.setActif(false);
		em.merge(p);
	}

	public void envoyeEmail() {
		EMailService email = new EMailService();
		email.send("antonin.monmarthe@googlemail.com", "testObjet", "Je suis ton père !");
	}
//antonin.monmarthe@googlemail.com perchaud.samuel@gmail.com
	
}
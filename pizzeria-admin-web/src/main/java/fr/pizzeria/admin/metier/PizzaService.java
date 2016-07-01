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
	 * on recupere toutes les pizzas actives (delflag =0 )
	 * 
	 * @return
	 */
	public List<Pizza> findAll() {
		return em.createQuery("select p from Pizza p where delFlag = 0", Pizza.class).getResultList();
	}

	public List<Pizza> findAllWithIngredient() {
		List<Pizza> pizzas = em.createQuery("select p from Pizza p where delFlag = 0", Pizza.class).getResultList();
		for (Pizza pizza : pizzas) {
			if (pizza.getIngredients().iterator().hasNext()) {
				pizza.getIngredients().iterator().next();
			}
		}
		return pizzas;
	}

	public Pizza findOnePizza(String code) {
		return em.createQuery("select p from Pizza p where p.code=:code and delFlag = 0", Pizza.class)
				.setParameter("code", code).getSingleResult();
	}

	public Pizza findOnePizzaWithIngredients(String code) {
		Pizza pizza = em.createQuery("select p from Pizza p where p.code=:code and delFlag = 0", Pizza.class)
				.setParameter("code", code).getSingleResult();
		// simulation de recupération des ingrédients (requetes)
		if (pizza.getIngredients().iterator().hasNext()) {
			pizza.getIngredients().iterator().next();
		}
		return pizza;
	}

	public List<Pizza> isCodeTaken(String code) {
		return em.createQuery("select p from Pizza p where p.code=:code and delFlag = 0", Pizza.class)
				.setParameter("code", code).getResultList();
	}

	public void updatePizza(String code, Pizza pizzaAvecId) {
		Pizza p = findOnePizza(code); // vérifie qu'une pizza est présente
		p.setDelFlag(true);
		pizzaAvecId.setId(null);
		em.merge(p);
		em.persist(pizzaAvecId);
	}

	public void savePizza(Pizza pizzaSansId) {
		em.persist(pizzaSansId);
	}

	public void deletePizza(String code) {
		Pizza p = findOnePizza(code); // vérifie qu'une pizza est présente
		p.setDelFlag(true);
		em.merge(p);
	}

	public void envoyeEmail() {

		EMailService email = new EMailService();
		email.send("aatonyoz@gmail.com", "fgsdf", "dfsdqg");
	}
}
=======
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
	 * on recupere toutes les pizzas actives (delflag =0 )
	 * 
	 * @return
	 */
	public List<Pizza> findAll() {
		return em.createQuery("select p from Pizza p where delFlag = 0", Pizza.class).getResultList();
	}
	
	public List<Pizza> findAllWithIngredient() {
		List<Pizza> pizzas = em.createQuery("select p from Pizza p where delFlag = 0", Pizza.class).getResultList();
		for (Pizza pizza : pizzas) {
			if(pizza.getIngredients().iterator().hasNext()) {
				pizza.getIngredients().iterator().next();
			}
		}
		return pizzas;
	}

	public Pizza findOnePizza(String code) {
		return em.createQuery("select p from Pizza p where p.code=:code and delFlag = 0", Pizza.class)
				.setParameter("code", code).getSingleResult();
	}
	
	public Pizza findOnePizzaWithIngredients(String code) {
		Pizza pizza = em.createQuery("select p from Pizza p where p.code=:code and delFlag = 0", Pizza.class)
				.setParameter("code", code).getSingleResult();
		// simulation de recupération des ingrédients (requetes)
		if(pizza.getIngredients().iterator().hasNext()) {
			pizza.getIngredients().iterator().next();
		}
		return pizza;
	}
	
	public List<Pizza> isCodeTaken(String code) {
		return em.createQuery("select p from Pizza p where p.code=:code and delFlag = 0", Pizza.class)
				.setParameter("code", code).getResultList();
	}
	public void updatePizza(String code, Pizza pizzaAvecId) {
		Pizza p = findOnePizza(code); // vérifie qu'une pizza est présente
		p.setDelFlag(true);
		pizzaAvecId.setId(null);
		em.merge(p);
		em.persist(pizzaAvecId);
	}

	public void savePizza(Pizza pizzaSansId) {
		em.persist(pizzaSansId);
	}

	public void deletePizza(String code) {
		Pizza p = findOnePizza(code); // vérifie qu'une pizza est présente
		p.setDelFlag(true);
		em.merge(p);
	}
}

package fr.pizzeria.admin.metier;

import fr.pizzeria.model.Pizza;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class PizzaService {

	@PersistenceContext
	protected EntityManager em;

	/**
	 * on recupere toutes les pizzas actives (delflag =0 )
	 * 
	 * @return
	 */
	public List<Pizza> findAll() {
		return em.createQuery("select p from Pizza p where delFlag = 0", Pizza.class).getResultList();
	}

	public Pizza findOnePizza(String code) {
		return em.createQuery("select p from Pizza p where p.code=:code and delFlag = 0", Pizza.class)
				.setParameter("code", code).getSingleResult();
	}

	public void updatePizza(String code, Pizza pizzaAvecId) {
		Pizza p = findOnePizza(code); // vérifie qu'une pizza est présente
		// if(p != null)
		p.setDelFlag(true);
		em.merge(p);
		pizzaAvecId.setId(null);
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

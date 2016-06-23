package fr.pizzeria.admin.metier;

import java.util.List;

import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import fr.pizzeria.model.Ingredient;

@Stateless
public class IngredientService {

	@PersistenceContext
	protected EntityManager em;
	
	/**
	 * on résupère tout les ingredients qui sont actives (actif = true)
	 * @return
	 */
	public List<Ingredient> findAll() {
		return em.createQuery("select i from Ingredient i where actif=1", Ingredient.class).getResultList();
	}

	public Ingredient findOneIngredient(String code) {
		return em.createQuery("select i from Ingredient i where i.code=:code and actif=1", Ingredient.class)
				.setParameter("code", code).getSingleResult();
	}

	public void updateIngredient(String code, Ingredient ingredientAvecCode) {
		Ingredient ing = findOneIngredient(code); // vérifie qu'une pizza est présente
		ing.setName(ingredientAvecCode.getName());
		em.merge(ing);
	}

	public boolean saveIngredient(Ingredient ingredientSansId) {
		try {
			findOneIngredient(ingredientSansId.getCode());
			return false;
		}catch(NoResultException | EJBException e) {
			em.persist(ingredientSansId);
			return true;
		}
	}

	public void deleteIngredient(String code) {
		Ingredient ing = findOneIngredient(code);
		ing.setActif(false);
		em.merge(ing);
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}
}

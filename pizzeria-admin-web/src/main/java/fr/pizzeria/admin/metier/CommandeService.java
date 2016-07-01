package fr.pizzeria.admin.metier;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import fr.pizzeria.model.Commande;

@Stateless
public class CommandeService {

	@PersistenceContext
	protected EntityManager em;
	
	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}

	public List<Commande> findAll() {
		return em
				.createQuery(
						"select c from Commande c where delFlag = 0", 
						Commande.class
				)
				.getResultList();
	}

	public Commande findOneCommande(String code) {
		return em
				.createQuery(
						"select c from Commande c where c.numeroCommande = :numeroCommande and delFlag = 0",
						Commande.class
				)
				.setParameter("numeroCommande", code)
				.getSingleResult();
	}

	public long isCodeTaken(String code) {
		return em
				.createQuery(
						"select count(c.numeroCommande) from Commande c where c.numeroCommande = :numeroCommande",
						Long.class
				)
				.setParameter("numeroCommande", code)
				.getSingleResult();
	}

	public void updateCommande(String code, Commande commandeAvecId) {
		findOneCommande(code); // vérifie qu'une commande est présente
		em.merge(commandeAvecId);
	}

	public void saveCommande(Commande commandeSansId) {
		em.persist(commandeSansId);
	}

	public void deleteCommande(String code) {
		Commande c = findOneCommande(code); // vérifie qu'une commande est présente
		c.setDelFlag(true);
		em.merge(c);
	}
}

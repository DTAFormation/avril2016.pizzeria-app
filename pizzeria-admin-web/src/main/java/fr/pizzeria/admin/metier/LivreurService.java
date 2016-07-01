package fr.pizzeria.admin.metier;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import fr.pizzeria.model.Livreur;

@Stateless
public class LivreurService {

	@PersistenceContext
	protected EntityManager em;

	public List<Livreur> findAll() {
		return em.createQuery("select p from Livreur p where p.actif = true", Livreur.class).getResultList();
	}

	public Livreur findOneLivreur(String id) {
		return em.createQuery("select p from Livreur p where p.id=:id and p.actif = true", Livreur.class)
				.setParameter("id", Integer.parseInt(id)).getSingleResult();
	}

	public void updateLivreur(String id, String nom, String prenom) {
		Livreur livreur = findOneLivreur(id); // vérifie qu'une Livreur est présente
		livreur.setNom(nom);
		livreur.setPrenom(prenom);
		em.merge(livreur);
	}

	public void saveLivreur(Livreur LivreurSansId) {
		em.persist(LivreurSansId);
	}

	public void deleteLivreur(String id) {
		Livreur livreur = findOneLivreur(id); // vérifie qu'une Livreur est présente
		livreur.setActif(false);
		em.merge(livreur);
	}

	public List<Livreur> findLivreur(String nom, String prenom) {
		return em.createQuery("select p from Livreur p where p.nom=:nom and p.prenom=:prenom", Livreur.class)
				.setParameter("nom", nom).setParameter("prenom", prenom).getResultList();
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}

}

package fr.pizzeria.admin.metier;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import fr.pizzeria.model.Commande;

@Stateless
public class CommandeService {

  @PersistenceContext protected EntityManager em;

  public List<Commande> findAll() {
    return em.createQuery("select c from Commande c", Commande.class).getResultList();
  }

  public Commande findOneCommande(String code) {
    return em.createQuery("select c from Commande c where c.numeroCommande=:numeroCommande", Commande.class)
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
    em.remove(findOneCommande(code));
  }
}

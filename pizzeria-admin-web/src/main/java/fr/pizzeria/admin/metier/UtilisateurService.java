package fr.pizzeria.admin.metier;

import fr.pizzeria.model.Utilisateur;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class UtilisateurService {

  @PersistenceContext protected EntityManager em;


  public List<Utilisateur> findAll() {
    return em.createQuery("select u from Utilisateur u", Utilisateur.class).getResultList();
  }

  public Utilisateur findOneUtilisateur(String email) {
    return em.createQuery("select u from Utilisateur u where u.email=:email", Utilisateur.class)
        .setParameter("email", email)
        .getSingleResult();
  }

  public void updateUtilisateur(String email, Utilisateur utilisateurAvecId) {
    findOneUtilisateur(email); // vérifie qu'un utilisateur est présent
    em.merge(utilisateurAvecId);
  }

  public void saveUtilisateur(Utilisateur utilisateurSansId) {
    em.persist(utilisateurSansId);
  }

  public void deleteUtilisateur(String email) {
    em.remove(findOneUtilisateur(email));
  }
}

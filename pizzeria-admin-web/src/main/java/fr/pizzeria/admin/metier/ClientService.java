package fr.pizzeria.admin.metier;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import fr.pizzeria.model.Client;

@Stateless
public class ClientService {

	@PersistenceContext
	protected EntityManager em;

	public List<Client> findAll() {
		return em.createQuery("select c from Client c where isActive = 1", Client.class).getResultList();
	}

	public Client findOneClient(String email) {
		return em.createQuery("select c from Client c where c.email=:email and isActive = 1", Client.class)
				.setParameter("email", email).getSingleResult();
	}

	public void updateClient(String email, Client clientAvecId) {
		findOneClient(email); // vérifie qu'un client est présent
		em.merge(clientAvecId);
	}

	public void saveClient(Client clientSansId) {
		em.persist(clientSansId);
	}

	public void deleteClient(String email) {
		Client c = findOneClient(email);
		if (c != null) {
			c.setActive(false);
			em.merge(c);
		}

	}

	public void setEm(EntityManager em2) {
		this.em = em2;

	}
}

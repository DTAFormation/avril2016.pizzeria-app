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

	public void updateClient(String oldEmail, Client clientAvecId) {
		Client oldClient = findOneClient(oldEmail); // vérifie qu'une pizza est présente
		oldClient.setActive(false);
		clientAvecId.setId(null);
		em.merge(oldClient);
		em.persist(clientAvecId);
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
	public void hardDeleteClients() {
		List<Client>clients= em.createQuery("select c from Client c where  isActive = 0", Client.class).getResultList();
				for (Client client : clients) {
					System.out.println("client : "+client.getPrenom()+" "+ client.getNom());
					em.remove(client);
				}
	}
	public void setEm(EntityManager em2) {
		this.em = em2;
	}

	public List<Client> isEmailTaken(String email) {
		return em.createQuery("select c from Client c where c.email=:email and isActive=1", Client.class).setParameter("email", email)
				.getResultList();
	}
}

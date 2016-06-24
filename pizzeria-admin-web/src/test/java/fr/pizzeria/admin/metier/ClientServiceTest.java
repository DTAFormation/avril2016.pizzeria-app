package fr.pizzeria.admin.metier;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.slf4j.LoggerFactory;

import fr.pizzeria.model.Client;

@RunWith(MockitoJUnitRunner.class)
public class ClientServiceTest {
	private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(ClientServiceTest.class);

	@Mock
	private EntityManager em;

	@Mock
	private TypedQuery<Client> query;

	private ClientService service;

	@Before
	public void setUp() {
		service = new ClientService();
		service.setEm(em);
	}

	@Test
	public void testFindAll() {
		Client c1 = new Client("test", "test", "test@test.fr", "10 av aa", "00000000");
		Client c2 = new Client("test2", "test2", "test@test.fr", "10 av aa", "00000000");
		List<Client> lClients = new ArrayList<>();
		service.saveClient(c1);
		service.saveClient(c2);
		when(em.createQuery("select c from Client c where isActive = 1", Client.class)).thenReturn(query);
		when(query.getResultList()).thenReturn(lClients);
		List<Client> lFindAll = service.findAll();
		assertEquals(lFindAll.size(), lClients.size());
	}

	@Test
	public void testFindOneClient() {

		Client c1 = new Client("test", "test", "test@test.fr", "10 av aa", "00000000");
		when(em.createQuery("select c from Client c where c.email=:email and isActive = 1", Client.class))
				.thenReturn(query);
		when(query.setParameter("email", "test@test.fr")).thenReturn(query);
		when(query.getSingleResult()).thenReturn(c1);

		Client c2 = service.findOneClient("test@test.fr");
		assertTrue(c1.equals(c2));
	}

	@Test
	public void testUpdateClient() {

		LOG.info("Etant donne un objet client");
		Client c1 = new Client("test", "test", "test@test.fr", "10 av aa", "00000000");
		Client c2 = new Client("test", "test", "test22@test.fr", "10 av aa", "00000000");
		when(em.createQuery("select c from Client c where c.email=:email and isActive = 1", Client.class))
				.thenReturn(query);
		when(query.setParameter("email", "test@test.fr")).thenReturn(query);
		when(query.getSingleResult()).thenReturn(c1);
		service.updateClient("test@test.fr", c2);
		LOG.info("Alors 'pizza' a ete persiste");
		verify(em).merge(c1);
		verify(em).persist(c2);

		assertFalse(c1.isActive());
		LOG.info("FIN");
	}

	@Test
	public void testSaveClient() {
		LOG.info("Etant donne un objet Client");
		Client client = new Client("test", "test", "test@test.fr", "10 av aa", "00000000");

		LOG.info("Lorsque ejb.saveClient(client)");
		service.saveClient(client);

		LOG.info("Alors 'client' a ete persiste");
		verify(em).persist(client);
		LOG.info("FIN");
	}

	@Test
	public void testDeleteClient() {
		LOG.info("Etant donne un objet Client");
		Client client = new Client(1, "test", "test", "test@test.fr", "10 av aa", "00000000");
		when(em.createQuery("select c from Client c where c.email=:email and isActive = 1", Client.class))
				.thenReturn(query);
		when(query.setParameter("email", "test@test.fr")).thenReturn(query);
		when(query.getSingleResult()).thenReturn(client);
		LOG.info("Lorsque ejb.deleteClient(client)");
		service.deleteClient("test@test.fr");

		LOG.info("Alors 'client' a ete modifie");
		verify(em).merge(client);
		assertFalse(client.isActive());
		LOG.info("FIN");
	}

}

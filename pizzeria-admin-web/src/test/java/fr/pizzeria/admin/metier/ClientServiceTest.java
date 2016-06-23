package fr.pizzeria.admin.metier;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;


import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.slf4j.LoggerFactory;
import static org.mockito.Mockito.*;
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
	public void creerClient() {		
		LOG.info("Etant donne un objet Client");		
		Client client = new Client("test","test","test@test.fr","1541604","10 av aa","00000000");
		
		LOG.info("Lorsque ejb.saveClient(client)");
		service.saveClient(client);
		
		LOG.info("Alors 'client' a ete persiste");
		verify(em).persist(client);
		LOG.info("FIN");
	}
	
	@Test
	public void supprimerClient() {		
		LOG.info("Etant donne un objet Client");		
		Client client = new Client(1,"test","test","test@test.fr","1541604","10 av aa","00000000");
		when(em.createQuery("select c from Client c where p.email=:email and isActive = 1", Client.class)).thenReturn(query);
		when(query.setParameter("email", "test@test.fr")).thenReturn(query);
		when(query.getSingleResult()).thenReturn(client);
		LOG.info("Lorsque ejb.deleteClient(client)");
		service.deleteClient("test@test.fr");
		
		LOG.info("Alors 'client' a ete modifie");
		verify(em).merge(client);
		
		LOG.info("FIN");
	}
	
	@Test
	public void supprimerClientVerifModifIsActive() {		
		LOG.info("Etant donne un objet Client");		
		Client client = new Client(1,"test","test","test@test.fr","1541604","10 av aa","00000000");
		when(em.createQuery("select c from Client c where p.email=:email and isActive = 1", Client.class)).thenReturn(query);
		when(query.setParameter("email", "test@test.fr")).thenReturn(query);
		when(query.getSingleResult()).thenReturn(client);
		LOG.info("Lorsque ejb.deleteClient(client)");
		service.deleteClient("test@test.fr");
		
		LOG.info("Alors 'client' a ete modifie et is Active est modifié à false");
		verify(em).merge(client);
		assertFalse(client.isActive());
		LOG.info("FIN");
	}
	

}
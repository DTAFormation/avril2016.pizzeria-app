package fr.pizzeria.admin.metier;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import fr.pizzeria.model.Utilisateur;

@RunWith(MockitoJUnitRunner.class)
public class UtilisateurServiceTest {
	
	private static final Logger LOG = Logger
            .getLogger(UtilisateurServiceTest.class.getName());
	
	@Mock 
	private EntityManager em;
	
	@Mock
	private TypedQuery<Utilisateur> query;
	
	private UtilisateurService userService;

	@Before
	public void setUp() {
		userService = new UtilisateurService();
		userService.em = em;		
	}
	
	@Test
	public void testFindOneUtilisateur() {
		Utilisateur user =  new Utilisateur("Mario", "Luigi", "luigi.mario@mushroom-kingdom.com", "boo96LoL!");
		when(em.createQuery("select u from Utilisateur u where u.email=:email", Utilisateur.class)).thenReturn(query);
		
		when(query.setParameter("email", "luigi.mario@mushroom-kingdom.com")).thenReturn(query);
		when(query.getSingleResult()).thenReturn(user);
		
		Utilisateur otherUser = userService.findOneUtilisateur("luigi.mario@mushroom-kingdom.com");
		assertEquals(user, otherUser);
	}
	
	// test à écrire
	@Test
	public void testSaveUtilisateur() {
		Utilisateur user =  new Utilisateur("Mario", "Luigi", "luigi.mario@mushroom-kingdom.com", "boo96LoL!");
		when(em.createQuery("select u from Utilisateur u where u.email=:email", Utilisateur.class)).thenReturn(query);
		
		when(query.setParameter("email", "luigi.mario@mushroom-kingdom.com")).thenReturn(query);
		when(query.getSingleResult()).thenReturn(user);
		
		Utilisateur otherUser = userService.findOneUtilisateur("luigi.mario@mushroom-kingdom.com");
		assertEquals(user, otherUser);
	}

}

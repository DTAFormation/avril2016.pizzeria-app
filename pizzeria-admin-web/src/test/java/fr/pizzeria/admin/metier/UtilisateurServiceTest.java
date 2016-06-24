package fr.pizzeria.admin.metier;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
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
	
	
	@Test
	public void testSaveUtilisateur() {
		Utilisateur user =  new Utilisateur("Mario", "Luigi", "luigi.mario@mushroom-kingdom.com", "boo96LoL!");
		userService.saveUtilisateur(user);
		
		verify(em).persist(user);
		
	}
	
	@Test
	public void testSaveUtilisateurAvecDoublons() {
		// 1er utilisateur à enregistrer
		Utilisateur user =  new Utilisateur("Mario", "Luigi", "luigi.mario@mushroom-kingdom.com", "boo96LoL!");
		userService.saveUtilisateur(user);
		verify(em).persist(user);
		
		// 2eme utilisateur à enregistrer (normalement non enregistré)
		Utilisateur userWithSameEmail =  new Utilisateur("Tomerzy", "Roblia", "luigi.mario@mushroom-kingdom.com", "Snoo-PINGAS-usUAl");
		userService.saveUtilisateur(userWithSameEmail);
		verify(em).persist(userWithSameEmail);
		
		when(em.createQuery("select u from Utilisateur u where u.email=:email", Utilisateur.class)).thenReturn(query);
		
		when(query.setParameter("email", "luigi.mario@mushroom-kingdom.com")).thenReturn(query);
		when(query.getSingleResult()).thenReturn(user);
		
		Utilisateur utilisateurTrouve = userService.findOneUtilisateur("luigi.mario@mushroom-kingdom.com");
		assertEquals(user, utilisateurTrouve);

	}

}

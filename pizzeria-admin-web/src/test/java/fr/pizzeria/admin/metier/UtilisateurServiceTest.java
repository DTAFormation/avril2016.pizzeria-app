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
		Utilisateur userWithSameEmail =  new Utilisateur("Tomerzy", "Roblia", "luigi.mario@mushroom-kingdom.com", "Ronflex!A+Bien-dormI");
		userService.saveUtilisateur(userWithSameEmail);
		verify(em).persist(userWithSameEmail);
		
		when(em.createQuery("select u from Utilisateur u where u.email=:email", Utilisateur.class)).thenReturn(query);
		
		when(query.setParameter("email", "luigi.mario@mushroom-kingdom.com")).thenReturn(query);
		when(query.getSingleResult()).thenReturn(user);
		
		Utilisateur utilisateurTrouve = userService.findOneUtilisateur("luigi.mario@mushroom-kingdom.com");
		assertEquals(user, utilisateurTrouve);

	}
	
	@Test
	public void testUpdateUtilisateur() {
		// création de l'utilisateur
		Utilisateur user =  new Utilisateur("Toto", "Lamia", "lala-satalin.deviluke@deviluke.dl", "La+Sa-De!");
		userService.saveUtilisateur(user);
		verify(em).persist(user);
		
		when(em.createQuery("select u from Utilisateur u where u.email=:email", Utilisateur.class)).thenReturn(query);
		when(query.setParameter("email", "lala-satalin.deviluke@deviluke.dl")).thenReturn(query);
		when(query.getSingleResult()).thenReturn(user);
		
		Utilisateur utilisateurTrouve = userService.findOneUtilisateur("lala-satalin.deviluke@deviluke.dl");
		assertEquals(user, utilisateurTrouve);
		
		// Mise à jour de l'utilisateur
		user.setNom("Deviluke");
		user.setPrenom("Lala Satalin");
		
		userService.updateUtilisateur(user.getEmail(), user);
		verify(em).merge(user);
		
		// Vérification de la mise à jour
		when(em.createQuery("select u from Utilisateur u where u.email=:email", Utilisateur.class)).thenReturn(query);
		when(query.setParameter("email", "lala-satalin.deviluke@deviluke.dl")).thenReturn(query);
		when(query.getSingleResult()).thenReturn(user);
		
		Utilisateur utilisateurCorrect =  new Utilisateur("Deviluke", "Lala Satalin", "lala-satalin.deviluke@deviluke.dl", "La+Sa-De!");
		assertEquals(user, utilisateurCorrect);
	}
	
	@Test
	public void testDeleteUtilisateur() {
		// création de l'utilisateur
		Utilisateur user =  new Utilisateur("Toto", "Lamia", "lala-satalin.deviluke@deviluke.dl", "La+Sa-De!");
		userService.saveUtilisateur(user);
		verify(em).persist(user);
		
		// Vérification de l'existence de l'utilisateur
		when(em.createQuery("select u from Utilisateur u where u.email=:email", Utilisateur.class)).thenReturn(query);
		when(query.setParameter("email", "lala-satalin.deviluke@deviluke.dl")).thenReturn(query);
		when(query.getSingleResult()).thenReturn(user);
		
		Utilisateur utilisateurTrouve = userService.findOneUtilisateur("lala-satalin.deviluke@deviluke.dl");
		assertEquals(user, utilisateurTrouve);
		
		userService.deleteUtilisateur("lala-satalin.deviluke@deviluke.dl");
		verify(em).remove(user);
	}
	
	@Test
	public void testFindAll() {
		// création des utilisateurs
		List<Utilisateur> utilisateurs =  new ArrayList();
		utilisateurs.add(new Utilisateur("Deviluke", "Lala Satalin", "lala-satalin.deviluke@deviluke.dl", "La+Sa-De!"));
		utilisateurs.add(new Utilisateur("Uzumaki", "Naruto", "naruto.uzumaki@konoha.hi", "Nindo"));
		utilisateurs.add(new Utilisateur("Abc", "Xyz", "abc.xyz@def.gh", "I!j+K-l"));
		
		for(Utilisateur user : utilisateurs) {
			userService.saveUtilisateur(user);
			verify(em).persist(user);
		}
		
	}

}

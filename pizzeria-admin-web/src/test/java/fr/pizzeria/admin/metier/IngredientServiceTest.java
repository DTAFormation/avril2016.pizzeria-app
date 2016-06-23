package fr.pizzeria.admin.metier;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import fr.pizzeria.model.Ingredient;

@RunWith(MockitoJUnitRunner.class)
public class IngredientServiceTest {
	
	private static final Logger LOG = Logger
            .getLogger(IngredientServiceTest.class.getName());
	
	@Mock 
	private EntityManager em;
	
	@Mock
	private TypedQuery<Ingredient> query;
	
	private IngredientService service;
	
	@Before
	public void setUp() {
		service = new IngredientService();
		service.setEm(em);		
	}
	
	@Test
	public void findOneIngredient() {
		LOG.info("Etant donne un objet ingredient");		

		Ingredient ingredient = new Ingredient("CHA","champignon");
		when(em.createQuery("select i from Ingredient i where i.code=:code and actif=1", Ingredient.class)).thenReturn(query);
		when(query.setParameter("code", "CHA")).thenReturn(query);
		when(query.getSingleResult()).thenReturn(ingredient);

		Ingredient newIngredient = service.findOneIngredient("CHA");
		
		LOG.info("Alors 'ingredient' a ete modifie");
		assertEquals(ingredient,newIngredient);
		
		LOG.info("FIN");
	}
	
	@Test
	public void creerIngredient() {		
		LOG.info("Etant donne un objet ingredient");		
		Ingredient ingredient = new Ingredient("CHA","champignon");
		
		service.saveIngredient(ingredient);
		
		LOG.info("Alors 'ingredient' a ete persiste");
		verify(em).persist(ingredient);
		LOG.info("FIN");
	}
	
	@Test
	public void supprimerIngredient() {		
		LOG.info("Etant donne un objet ingredient");		

		Ingredient ingredient = new Ingredient("CHA","champignon");
		when(em.createQuery("select i from Ingredient i where i.code=:code and actif=1", Ingredient.class)).thenReturn(query);
		when(query.setParameter("code", "CHA")).thenReturn(query);
		when(query.getSingleResult()).thenReturn(ingredient);

		service.deleteIngredient("CHA");
		
		LOG.info("Alors 'ingredient' a ete modifie");
		verify(em).merge(ingredient);
		
		LOG.info("FIN");
	}
	
	@Test
	public void supprimerIngredientVerifModifIsActive() {		
		LOG.info("Etant donne un objet ingredient");		
		Ingredient ingredient = new Ingredient("CHA","champignon");
		when(em.createQuery("select i from Ingredient i where i.code=:code and actif=1", Ingredient.class)).thenReturn(query);
		when(query.setParameter("code", "CHA")).thenReturn(query);
		when(query.getSingleResult()).thenReturn(ingredient);

		service.deleteIngredient("CHA");
		
		LOG.info("Alors 'ingredient' a ete modifie et is Active est modifié à false");
		verify(em).merge(ingredient);
		assertFalse(ingredient.isActif());
		LOG.info("FIN");
	}
}

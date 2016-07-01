package fr.pizzeria.admin.web.pizza;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import fr.pizzeria.admin.metier.IngredientService;
import fr.pizzeria.admin.metier.PizzaService;
import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.Ingredient;
import fr.pizzeria.model.Pizza;

@RunWith(MockitoJUnitRunner.class)
public class EditerPizzaControllerTest {

	@Mock 
	private EntityManager em;
	
	@Mock
	private TypedQuery<Ingredient> query;
	
	@Mock
	private TypedQuery<Pizza> pizzaQuery;
	
	@Mock
	HttpServletRequest req;
	
	@Mock
	HttpServletResponse resp;

	private IngredientService serviceIngredient;
	private PizzaService servicePizza;
	
	@Before
	public void setUp() {
		serviceIngredient = new IngredientService();
		serviceIngredient.setEm(em);	
		servicePizza = new PizzaService();
		servicePizza.setEm(em);
	}
	
	@Test
	public void testDoPostHttpServletRequestHttpServletResponse() throws ServletException, IOException {
		Ingredient ingredient = new Ingredient("CHA","champignon");
		Ingredient ingredient2 = new Ingredient("SAM","saumon");
		Ingredient ingredient3 = new Ingredient("FRO","fromage");
		List<Ingredient> listeIngredient = new ArrayList<>();
		listeIngredient.add(ingredient);
		listeIngredient.add(ingredient2);
		listeIngredient.add(ingredient3);
		String stringvalues[] = {"CHA", "SAM", "FRO"};
		Pizza oldPizza = new Pizza(1, "PEP", "peperoni", new BigDecimal(12.5), CategoriePizza.VIANDE, "http://placehold.it/350x150", listeIngredient);
		oldPizza.setDelFlag(true);
		
		Pizza newPizza = new Pizza(2, "PEP", "peperoni", new BigDecimal(12.5), CategoriePizza.VIANDE, "http://placehold.it/350x150", listeIngredient);
		newPizza.setId(null);
		
		when(req.getParameter("id")).thenReturn("1");
		when(req.getParameter("nom")).thenReturn("peperoni");
		when(req.getParameter("urlImage")).thenReturn("http://placehold.it/350x150");
		when(req.getParameter("prix")).thenReturn("12.5");
		when(req.getParameter("code")).thenReturn("PEP");
		when(req.getParameterValues("ingredient")).thenReturn(stringvalues);
		
		when(em.createQuery("select i from Ingredient i where i.code=:code and actif=1", Ingredient.class)).thenReturn(query);
		when(query.setParameter("code", "CHA")).thenReturn(query);
		when(query.getSingleResult()).thenReturn(ingredient);
		
		when(em.createQuery("select i from Ingredient i where i.code=:code and actif=1", Ingredient.class)).thenReturn(query);
		when(query.setParameter("code", "SAM")).thenReturn(query);
		when(query.getSingleResult()).thenReturn(ingredient2);
		
		when(em.createQuery("select i from Ingredient i where i.code=:code and actif=1", Ingredient.class)).thenReturn(query);
		when(query.setParameter("code", "FRO")).thenReturn(query);
		when(query.getSingleResult()).thenReturn(ingredient3);
		
		when(em.createQuery("select p from Pizza p where p.code=:code and delFlag = 0", Pizza.class)).thenReturn(pizzaQuery);
		when(pizzaQuery.setParameter("code", "PEP")).thenReturn(pizzaQuery);
		when(pizzaQuery.getSingleResult()).thenReturn(oldPizza);
		
		EditerPizzaController controllerTest = new EditerPizzaController();
		controllerTest.setPizzaService(servicePizza);
		controllerTest.setIngredientService(serviceIngredient);
		controllerTest.doPost(req, resp);
		
		verify(em).persist(newPizza);
		verify(em).merge(oldPizza);
		
	}

}

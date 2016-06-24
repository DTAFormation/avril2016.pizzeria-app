package fr.pizzeria.model;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class PizzaTest {
	
	@Test
	public void creerPizza(){
		Pizza p = new Pizza("TES","test",BigDecimal.valueOf(10),CategoriePizza.POISSON );
		assertEquals("test",p.getNom());
	}
}

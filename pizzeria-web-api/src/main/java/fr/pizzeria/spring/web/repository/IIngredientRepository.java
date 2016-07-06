package fr.pizzeria.spring.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.pizzeria.model.Ingredient;
import fr.pizzeria.model.Pizza;

public interface IIngredientRepository extends JpaRepository<Ingredient, Integer> {
	Pizza findByCode(String code);
}

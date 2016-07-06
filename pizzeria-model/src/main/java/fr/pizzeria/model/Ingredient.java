package fr.pizzeria.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Ingredient {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Double prix;
	private String code;
	private String name;
	private boolean actif = true;

	public Ingredient() {
		// default construct
	}

	public Ingredient(String code, String name, Double prix) {
		this.prix = prix;
		this.code = code;
		this.name = name;
	}

	public Ingredient(Integer id, String code, String name) {
		this.id = id;
		this.code = code;
		this.name = name;
	}

	public Ingredient(String code, String name) {
		this.code = code;
		this.name = name;
	}
	
	public Double getPrix() {
		return prix;
	}

	public void setPrix(Double prix) {
		this.prix = prix;
	}

	public boolean isActif() {
		return actif;
	}

	public void setActif(boolean actif) {
		this.actif = actif;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}

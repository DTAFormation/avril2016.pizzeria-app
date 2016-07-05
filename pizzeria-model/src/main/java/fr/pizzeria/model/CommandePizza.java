package fr.pizzeria.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "commande_pizza")
@IdClass(CommandePizzaId.class)
public class CommandePizza {
	@Id
	private Integer commandeId;
	
	@Id
	private Integer pizzaId;
	
	@JsonIgnore
	@ManyToOne
	@PrimaryKeyJoinColumn(name = "commande_id", referencedColumnName = "id")
	private Commande commande;
	
	@ManyToOne//(fetch=FetchType.EAGER)
	@PrimaryKeyJoinColumn(name = "pizza_id", referencedColumnName = "id")
	private Pizza pizza;
	
	private int quantite;
	
	public CommandePizza(Commande commande, Pizza pizza, int quantite) {
		this.commande = commande;
		this.pizza = pizza;
		this.quantite = quantite;
	}
	
	public CommandePizza() {
		// TODO Auto-generated constructor stub
	}

	public int getCommandeId() {
		return commandeId;
	}

	public void setCommandeId(int commandeId) {
		this.commandeId = commandeId;
	}

	public int getPizzaId() {
		return pizzaId;
	}

	public void setPizzaId(int pizzaId) {
		this.pizzaId = pizzaId;
	}

	public Commande getCommande() {
		return commande;
	}

	public void setCommande(Commande commande) {
		this.commande = commande;
	}

	public Pizza getPizza() {
		return pizza;
	}

	public void setPizza(Pizza pizza) {
		this.pizza = pizza;
	}

	public int getQuantite() {
		return quantite;
	}

	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}
}

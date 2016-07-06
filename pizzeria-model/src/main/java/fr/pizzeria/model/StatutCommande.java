package fr.pizzeria.model;

import com.fasterxml.jackson.annotation.JsonValue;

public enum StatutCommande {
	PASSE("Passée"), NON_TRAITE("Non traitée"), EN_PREPARATION("En préparation"), PREPARE("Préparée"), 
		EXPEDIE("Expédiée"), LIVRE("Livrée"), TERMINE("Terminée");

	private String libelle;

	StatutCommande(String libelle) {
		this.libelle = libelle;
	}

	@JsonValue
	public String getLibelle() {
		return libelle;
	}

	@Override
	public String toString() {
		return libelle;
	}
}

package fr.pizzeria.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.apache.commons.lang3.StringUtils;

@Entity
public class Livreur {
	
	public static final String CODE_LIVREUR_PAR_DEFAUT = "DEFAUT"; 
	
	public Livreur() {
		this.actif = true;
	}
	
	public Livreur(String nom, String prenom) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.actif = true; 
	}
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@Column(unique=true)
	private String code;
	private String nom;
	private String prenom;
	private Boolean actif;
	
	
	public String genererCodeBrut () {
		// supprimer les accents
		String nomStr = StringUtils.stripAccents(nom);
		String prenomStr = StringUtils.stripAccents(prenom);
		
		// supprimer tout ce qui n'est pas une lettre
		nomStr = StringUtils.removePattern(nomStr, "[^A-Za-z]");
		prenomStr = StringUtils.removePattern(prenomStr, "[^A-Za-z]");
		
		// prendre les 3 premiers caractères, ou tous les caractères s'il y en a moins
		nomStr = nomStr.substring(0, (nomStr.length() > 2) ? 3 : nomStr.length());
		prenomStr = prenomStr.substring(0, (prenomStr.length() > 2) ? 3 : prenomStr.length());
		
		if ((nomStr + prenomStr).length() == 0) {
			// la chaîne en sortie serait vide : retourner le code par défaut
			return CODE_LIVREUR_PAR_DEFAUT;
		} else {
			// concaténer le nom et prénom, passer le tout en majuscules
			return (nomStr + prenomStr).toUpperCase();
		}
		
	}
	
	
	

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	
	
	public Boolean getActif() {
		return actif;
	}

	public void setActif(Boolean actif) {
		this.actif = actif;
	}

	@Override
	public String toString() {
		return prenom + " " + nom;
	}

	
	

}


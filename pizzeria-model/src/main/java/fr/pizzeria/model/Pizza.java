package fr.pizzeria.model;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

@Entity
public class Pizza {

	private final static Map<String, String> FORMAT = new HashMap<String, String>();
	private final static String AUTRE_FORMAT = "(%s)";

	static {
		FORMAT.put("code", "%s ->");
		FORMAT.put("nom", "%s ***");
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@ToString
	@Column(unique = true)
	public String code;
	@ToString(uppercase = true)
	private String nom;
	@ToString
	private BigDecimal prix;
	@ToString
	@Enumerated(EnumType.STRING)
	private CategoriePizza categorie;
	private String urlImage;
	private String description;
	
	@ManyToMany
	@JoinTable(name = "pizza_ingredient", joinColumns = @JoinColumn(name = "pizza_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "ingredient_id", referencedColumnName = "id"))
	private List<Ingredient> ingredients = new ArrayList<>();

	public Pizza() {
		// implémentation par défaut
	}

	
	
	public Pizza(String code, String nom, BigDecimal prix, CategoriePizza categorie, String urlImage,
			String description, List<Ingredient> ingredients) {
		this.code = code;
		this.nom = nom;
		this.prix = prix;
		this.categorie = categorie;
		this.urlImage = urlImage;
		this.description = description;
		this.ingredients = ingredients;
	}



	public Pizza(String code, String nom, BigDecimal prix, CategoriePizza cat, String description) {
		this();
		this.code = code;
		this.nom = nom;
		this.prix = prix;
		this.categorie = cat;
		this.description = description;
	}

	public Pizza(String code, String nom, BigDecimal prix, CategoriePizza cat) {
		this();
		this.code = code;
		this.nom = nom;
		this.prix = prix;
		this.categorie = cat;
	}

	public Pizza(Integer id, String code, String nom, BigDecimal prix, CategoriePizza categorie, String urlImage,
			String description) {
		this.code = code;
		this.nom = nom;
		this.prix = prix;
		this.categorie = categorie;
		this.urlImage = urlImage;
		this.id = id;
		this.description = description;
	}

	public Pizza(String code, String nom, BigDecimal prix, CategoriePizza categorie, String urlImage,
			String description) {
		this.code = code;
		this.nom = nom;
		this.prix = prix;
		this.categorie = categorie;
		this.urlImage = urlImage;
		this.description = description;
	}

	public Pizza(Integer id, String code, String nom, BigDecimal prix, CategoriePizza categorie, String urlImage) {
		this.code = code;
		this.nom = nom;
		this.prix = prix;
		this.categorie = categorie;
		this.urlImage = urlImage;
		this.id = id;

	}

	public List<Ingredient> getIngredients() {
		return ingredients;
	}

	public void setIngredients(List<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}

	public Pizza addIngredient(Ingredient ingredient) {
		this.ingredients.add(ingredient);
		return this;
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

	/**
	 * Utiliser plutôt getNouveauPrix()
	 * 
	 * @return
	 */
	public BigDecimal getPrix() {
		return prix;
	}

	public void setPrix(BigDecimal prix) {
		this.prix = prix;
	}

	public CategoriePizza getCategorie() {
		return categorie;
	}

	public void setCategorie(CategoriePizza categorie) {
		this.categorie = categorie;
	}

	public String getUrlImage() {
		return urlImage;
	}

	public void setUrlImage(String urlImage) {
		this.urlImage = urlImage;
	}

	@Override
	public String toString() {
		return Arrays.asList(this.getClass().getDeclaredFields()).stream()
				.filter(field -> field.getAnnotation(ToString.class) != null).map(getValeurDuChamp())
				.collect(Collectors.joining(" "));
	}

	private Function<? super Field, ? extends String> getValeurDuChamp() {
		return field -> {

			String resultat = "";
			try {
				resultat = field.getAnnotation(ToString.class).uppercase() ? field.get(this).toString().toUpperCase()
						: field.get(this).toString();
			} catch (SecurityException | IllegalArgumentException | IllegalAccessException e1) {
				e1.printStackTrace();
			}

			String formatResultat = FORMAT.get(field.getName()) == null ? AUTRE_FORMAT : FORMAT.get(field.getName());

			return String.format(formatResultat, resultat);
		};
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(17, 37).append(code).toHashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (obj == this) {
			return true;
		}
		if (obj.getClass() != getClass()) {
			return false;
		}
		Pizza rhs = (Pizza) obj;
		return new EqualsBuilder().append(code, rhs.code).isEquals();
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}

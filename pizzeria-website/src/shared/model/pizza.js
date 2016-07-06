export class Pizza {
  constructor ({id, code, nom, prix, categorie, urlImage, description, ingredients}) {
    this.id = id
    this.code = code
    this.nom = nom
    this.prix = prix
    this.categorie = categorie
    this.urlImage = urlImage
    this.description = description
    this.ingredients = ingredients
  }
}

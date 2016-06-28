export class Pizza {
  constructor ({id = 0, code, nom, prix, categorie, urlImage}) {
    this.id = id
    this.code = code
    this.nom = nom
    this.prix = prix
    this.categorie = categorie
    this.urlImage = urlImage
  }
}

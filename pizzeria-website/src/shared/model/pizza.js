export class Pizza {
  constructor ({code, nom, prix, categorie, urlImage}) {
    this.code = code;
    this.nom = nom;
    this.prix = prix;
    this.categorie = categorie;
    this.imageUrl = urlImage;
  }
}
export class Client {
  constructor ({id, nom, prenom, email, motDePasse, sexe, dateNaissance, adresseNum, adresseRue, adresseDetail, adresseCodePostal, adresseVille, numeroTel}) {
    this.id = id
    this.nom = nom
    this.prenom = prenom
    this.email = email
    this.motDePasse = motDePasse
    this.sexe = sexe
    this.dateNaissance = dateNaissance
    this.adresseNum = adresseNum
    this.adresseRue = adresseRue
    this.adresseDetail = adresseDetail
    this.adresseCodePostal = adresseCodePostal
    this.adresseVille = adresseVille
    this.numeroTel = numeroTel
  }
}

export class Commande {
  constructor ({id, numeroCommande, statut, dateCommande, livreur, client, pizzas}) {
    this.id = id
    this.numeroCommande = numeroCommande
    this.statut = statut
    this.dateCommande = dateCommande
    this.livreur = livreur
    this.client = client
    this.pizzas = pizzas
  }

  calculPrixTotal () {
    let prixTotal = 0
    this.pizzas.forEach(pizza => {
      prixTotal += pizza.prix
    })
    return prixTotal
  }
}

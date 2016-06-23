export class Commande {
  constructor ({id, numeroCommande, dateCommande, statut, livreur, client, pizzas}) {
    this.id = id
    this.numeroCommande = numeroCommande
    this.dateCommande = dateCommande
    this.statut = statut
    this.livreur = livreur
    this.client = client
    this.pizzas = pizzas
  }
}

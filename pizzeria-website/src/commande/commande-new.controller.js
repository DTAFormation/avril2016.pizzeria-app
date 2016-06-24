import { Pizza } from '../shared/model/pizza'
import { Commande } from '../shared/model/commande'

export class CommandeNewController {

  constructor (commandesService, $location) {
    this.commandesService = commandesService
    this.$location = $location
    this.total = 0
    this.panier = [
      {'pizza': new Pizza({ 'nom': 'Royale', 'code': 'royale', 'prix': 12, 'categorie': 'VIANDE', 'urlImage': 'http://placehold.it/150x150' }), 'quantite': 1},
      {'pizza': new Pizza({ 'nom': 'Royale', 'code': 'royale', 'prix': 12, 'categorie': 'VIANDE', 'urlImage': 'http://placehold.it/150x150' }), 'quantite': 2},
      {'pizza': new Pizza({ 'nom': 'Royale', 'code': 'royale', 'prix': 12, 'categorie': 'VIANDE', 'urlImage': 'http://placehold.it/150x150' }), 'quantite': 1}
    ]
    this.panier.forEach(obj => {
      this.total += obj.pizza.prix * obj.quantite
    })
  }

  validate () {
    let pizzas = []
    this.panier.forEach(obj => {
      for (let i = 0; i < obj.quantite; i++) {
        pizzas.push(obj.pizza)
      }
    })
    let commande = new Commande({
      numeroCommande: 'CO-' + new Date().getTime(),
      dateCommande: new Date().getTime(),
      statut: 'NON_TRAITE',
      livreur: null,
      client: null,
      pizzas: pizzas
    })

    return this.commandesService.addOne(commande)
      .then(data => {
        this.$location.path('/')
      })
  }
}

CommandeNewController.$inject = ['CommandesService', '$location']

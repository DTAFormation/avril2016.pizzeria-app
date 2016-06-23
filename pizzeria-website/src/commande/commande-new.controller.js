import { Pizza } from '../shared/model/pizza'

export class CommandeNewController {

  constructor (commandesService) {
    this.commandesService = commandesService
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
}

CommandeNewController.$inject = ['CommandesService']

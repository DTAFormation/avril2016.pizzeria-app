import { Pizza } from '../shared/model/pizza'
import { Commande } from '../shared/model/commande'

export class CommandeNewController {

  constructor (commandesService, panierService, $localStorage, $location) {
    this.commandesService = commandesService
    this.panierService = panierService
    this.$localStorage = $localStorage
    this.$location = $location
    this.total = 0
    this.panier = this.panierService.findAllPizzas()
    Object.keys(this.panier).forEach(key => {
      this.total += this.panier[key].pizza.prix * this.panier[key].quantite
    })
  }

  validate () {
    let pizzas = []
    Object.keys(this.panier).forEach(key => {
      for (let i = 0; i < this.panier[key].quantite; i++) {
        pizzas.push(this.panier[key].pizza)
      }
    })
    let commande = new Commande({
      numeroCommande: 'CO-' + new Date().getTime(),
      dateCommande: new Date().getTime(),
      statut: 'NON_TRAITE',
      livreur: null,
      client: this.$localStorage.client,
      pizzas: pizzas
    })

    return this.commandesService.addOne(commande)
      .then(data => {
        this.panierService.deleteAllPizzas()
        this.$location.path('/commandes')
        return data
      })
  }
}

CommandeNewController.$inject = ['CommandesService', 'PanierService', '$localStorage', '$location']

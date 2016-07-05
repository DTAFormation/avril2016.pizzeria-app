import { Commande } from '../shared/model/commande'

export class CommandeNewController {
  constructor (commandesService, panierService, $localStorage, $location) {
    this.commandesService = commandesService
    this.panierService = panierService
    this.$localStorage = $localStorage
    this.$location = $location
    if (!this.$localStorage.client) {
      $location.path('/connexion')
    }
    this.panier = this.panierService.findAllPizzas()
    this.calcTotalPrice()
  }

  calcTotalPrice () {
    this.total = 0
    Object.keys(this.panier).forEach(key => {
      this.total += this.panier[key].pizza.prix * this.panier[key].quantite
    })
  }

  incrementPizza (pizza) {
    this.panierService.incrementPizza(pizza)
    this.calcTotalPrice()
  }

  decrementPizza (pizza) {
    this.panierService.decrementPizza(pizza)
    this.calcTotalPrice()
  }

  validate () {
    let pizzas = []
    Object.keys(this.panier).forEach(key => {
      for (let i = 0; i < this.panier[key].quantite; i++) {
        pizzas.push(this.panier[key].pizza)
      }
    })
    
    let commandesPizzas = []
    Object.keys(this.panier).forEach(key => {
      commandesPizzas.push({
        pizzaId: this.panier[key].pizza.id,
        quantite: this.panier[key].quantite
      })
    })

    let commande = new Commande({
      numeroCommande: 'CO-' + new Date().getTime(),
      dateCommande: new Date().getTime(),
      statut: 'NON_TRAITE',
      livreur: null,
      client: this.$localStorage.client,
      pizzas: commandesPizzas
    })

    return this.commandesService.addOne(commande)
      .then(data => {
        this.panierService.deleteAllPizzas()
        this.$location.path('/commandes/' + (this.$localStorage.client) ? (this.$localStorage.client.id) : (''))
        return data
      })
  }
}

CommandeNewController.$inject = ['CommandesService', 'PanierService', '$localStorage', '$location']

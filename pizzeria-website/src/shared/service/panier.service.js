
import { PizzasService } from './pizza.service'

export class PanierService {

  constructor ($localStorage, $http, $rootScope) {
    this.$localStorage = $localStorage
    this.$rootScope = $rootScope
    if (!this.$localStorage.panier) this.$localStorage.panier = {}
    if (!this.$localStorage.cartValue) this.$localStorage.cartValue = 0
    this.pizzaService = new PizzasService($http);
    this.allPizza = this.pizzaService.findAllPizzas();
    this.pizzaPanier = {}
    this.getPizzaByPanier();
  }

  addPizza (pizza) {
    var panier = this.findAllPizzas()
    if (panier[pizza.code]) panier[pizza.code]['quantite']++
    else panier[pizza.code] = {'quantite': 1}
    this.$localStorage.panier = panier
    this.$localStorage.cartValue += pizza.prix
    this.getPizzaByPanier()
    
  }

  deletePizza (pizza) {
    var panier = this.findAllPizzas()
    this.$localStorage.cartValue-= pizza.prix*panier[pizza.code]['quantite']

    delete panier[pizza.code]
    this.$localStorage.panier = panier
    delete this.pizzaPanier[pizza.code]
    this.$rootScope.$broadcast('EVENT_PRIX')
  }

  incrementPizza (pizza) {
    var panier = this.findAllPizzas()
    if (panier[pizza.code]) {
      panier[pizza.code]['quantite']++
      this.$localStorage.cartValue+=pizza.prix
    }
    this.$localStorage.panier = panier
    this.getPizzaByPanier()
  }

  decrementPizza (pizza) {
    var panier = this.findAllPizzas()
    if (panier[pizza.code]) {
      panier[pizza.code]['quantite']--
      this.$localStorage.cartValue-= pizza.prix
      if (panier[pizza.code]['quantite'] === 0) {
        this.deletePizza(pizza)
      }
    }
    this.$localStorage.panier = panier
    this.getPizzaByPanier()
  }

  /**
   * récupère les pizzas présente dans le panier
   */
  getPizzaByPanier () {
    var panier = this.findAllPizzas()
    this.allPizza
    .then(function(pizzas) {
      pizzas.map(pizza => {
        if (panier[pizza.code]) {
          this.pizzaPanier[pizza.code] = {'pizza': pizza, 'quantite': panier[pizza.code]['quantite']}
        }
      })
    }.bind(this))
    this.$rootScope.$broadcast('EVENT_PRIX')
  }

  // TODO : changer le nom (voir raison dans findAllPizzas() )
  deleteAllPizzas () {
    this.$localStorage.panier = {}
    this.$localStorage.cartValue=0

    this.pizzaPanier = {}
  }

  // TODO : changer de nom vers "getPanier" (on ne récupère pas des pizzas mais une liste d'objets avec une pizza ET une quantité)
  findAllPizzas () {
    return this.$localStorage.panier
  }

  size () {
    return Object.keys(this.findAllPizzas()).length
  }
}

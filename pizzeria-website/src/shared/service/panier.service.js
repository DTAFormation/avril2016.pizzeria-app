import { PizzasService } from './pizza.service'

export class PanierService {

  constructor ($localStorage, $http) {
    this.$localStorage = $localStorage
    if (!this.$localStorage.panier) this.$localStorage.panier = {}
    var pizzaService = new PizzasService($http);
    this.allPizza = pizzaService.findAllPizzas();
    this.pizzaPanier = {}
    this.getPizzaByPanier();
  }

  addPizza (pizza) {
    var panier = this.findAllPizzas()
    if (panier[pizza.id]) panier[pizza.id]['quantite']++
    else panier[pizza.id] = {'quantite': 1}
    this.$localStorage.panier = panier
    this.getPizzaByPanier()
    console.log(this.pizzaPanier)
  }

  deletePizza (id) {
    var panier = this.findAllPizzas()
    delete panier[id]
    this.$localStorage.panier = panier
    delete this.pizzaPanier[id]
  }

  incrementPizza (id) {
    var panier = this.findAllPizzas()
    if (panier[id]) panier[id]['quantite']++
    this.$localStorage.panier = panier
    this.getPizzaByPanier()
  }

  decrementPizza (id) {
    var panier = this.findAllPizzas()
    if (panier[id]) {
      panier[id]['quantite']--
      if (panier[id]['quantite'] === 0) {
        this.deletePizza(id)
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
        if (panier[pizza.id]) {
          this.pizzaPanier[pizza.id] = {'pizza': pizza, 'quantite': panier[pizza.id]['quantite']}
        }
      })
    }.bind(this))
  }

  // TODO : changer le nom (voir raison dans findAllPizzas() )
  deleteAllPizzas () {
    this.$localStorage.panier = {}
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

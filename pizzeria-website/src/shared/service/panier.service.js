export class PanierService {
  constructor ($localStorage) {
    this.$localStorage = $localStorage
    if (!this.$localStorage.panier) this.$localStorage.panier = {}
  }

  addPizza (pizza) {
    var panier = this.findAllPizzas()
    if (panier[pizza.id]) panier[pizza.id]['quantite']++
    else panier[pizza.id] = {'pizza': pizza, 'quantite': 1}
    this.$localStorage.panier = panier
  }

  deletePizza (id) {
    var panier = this.findAllPizzas()
    delete panier[id]
    this.$localStorage.panier = panier
  }

  incrementPizza (id) {
    var panier = this.findAllPizzas()
    if (panier[id]) panier[id]['quantite']++
    this.$localStorage.panier = panier
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
  }

  // TODO : changer le nom (voir raison dans findAllPizzas() )
  deleteAllPizzas () {
    this.$localStorage.panier = {}
  }

  // TODO : changer de nom vers "getPanier" (on ne récupère pas des pizzas mais une liste d'objets avec une pizza ET une quantité)
  findAllPizzas () {
    return this.$localStorage.panier
  }

  size () {
    return Object.keys(this.findAllPizzas()).length
  }
}
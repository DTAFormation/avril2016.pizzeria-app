export class PanierService {
  constructor ($localStorage) {
    this.$localStorage = $localStorage
  }
  addPizza (pizza) {
    var panier = this.findAllPizzas()
    if (panier[pizza.id]) panier[pizza.id]['quantite']++
    else panier[pizza.id] = {'pizza': pizza, 'quantite': 1}
    this.$localStorage.panier = panier
  }

  deletePizza (pizza) {
    console.log('delete:', pizza)
    var panier = this.findAllPizza()
    panier[pizza.id] = undefined
  }

  deleteAllPizzas () {
    this.$localStorage.panier = undefined
  }

  findAllPizzas () {
    return this.$localStorage.panier || {}
  }
}

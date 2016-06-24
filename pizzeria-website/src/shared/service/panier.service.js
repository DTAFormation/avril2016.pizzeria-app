export class PanierService {
  constructor ($localStorage) {
    this.$localStorage = $localStorage
    if(!this.$localStorage.panier) this.$localStorage.panier = {}
  }
  addPizza (pizza) {
    var panier = this.findAllPizzas()
    if (panier[pizza.id]) panier[pizza.id]['quantite']++
    else panier[pizza.id] = {'pizza': pizza, 'quantite': 1}
    this.$localStorage.panier = panier
  }

  deletePizza (pizza) {
    console.log('it should delete one pizza')
  }

  deleteAllPizzas () {
    this.$localStorage.panier = {}
  }

  findAllPizzas () {
    return this.$localStorage.panier
  }
}
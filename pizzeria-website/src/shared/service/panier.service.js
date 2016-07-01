export class PanierService {
  constructor ($localStorage) {
    this.$localStorage = $localStorage
    if (!this.$localStorage.panier) this.$localStorage.panier = {}
     if (!this.$localStorage.cartValue) this.$localStorage.cartValue = 0
  }

  addPizza (pizza) {
    var panier = this.findAllPizzas()
    if (panier[pizza.id]) panier[pizza.id]['quantite']++
    else panier[pizza.id] = {'pizza': pizza, 'quantite': 1}
    this.$localStorage.panier = panier
    this.$localStorage.cartValue+= pizza.prix 
  }

  deletePizza (id) {
    var panier = this.findAllPizzas()
    this.$localStorage.cartValue-= panier[id]['pizza']['prix']*panier[id]['quantite']

    delete panier[id]
    this.$localStorage.panier = panier
  }

  incrementPizza (id) {
    var panier = this.findAllPizzas()
    console.log('incrementPizza ' , panier[id]['pizza']['prix'])
    if (panier[id]) {
      panier[id]['quantite']++
      this.$localStorage.cartValue+= panier[id]['pizza']['prix']
    }
    this.$localStorage.panier = panier
  }

  decrementPizza (id) {
    var panier = this.findAllPizzas()
    console.log('decrementPizza')
    if (panier[id]) {
      panier[id]['quantite']--
      this.$localStorage.cartValue-= panier[id]['pizza']['prix']
      if (panier[id]['quantite'] === 0) {
        this.deletePizza(id)
      }
    }
    this.$localStorage.panier = panier
  }

  // TODO : changer le nom (voir raison dans findAllPizzas() )
  deleteAllPizzas () {
    this.$localStorage.panier = {}
    this.$localStorage.cartValue=0

  }

  // TODO : changer de nom vers "getPanier" (on ne récupère pas des pizzas mais une liste d'objets avec une pizza ET une quantité)
  findAllPizzas () {
    return this.$localStorage.panier
  }

  size () {
    return Object.keys(this.findAllPizzas()).length
  }
}

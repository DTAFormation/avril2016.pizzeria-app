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
    var panier = this.findAllPizza()
    panier[pizza.id] = undefined
  }

  deleteAllPizzas () {
    this.$localStorage.panier = ''
  }

  findAllPizzas () {
    return this.$localStorage.panier || {}
  }
}

//  this.contenu = [
//       { 'pizza': new Pizza({'code': 'royale', 'nom': 'Royale', 'prix': 12, 'categorie': 'VIANDE', 'urlImage': 'http://placehold.it/150x150'}), 'quantite': 1 },
//       { 'pizza': new Pizza({'code': 'marguerita', 'nom': 'Margherita', 'prix': 15, 'categorie': 'VIANDE', 'urlImage': 'http://placehold.it/150x150'}), 'quantite': 3 }
//     ]
//   }
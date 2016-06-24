export class PanierService {
  constructor ($localStorage) {
    this.$localStorage = $localStorage
    this.$localStorage['panier'] = {}
  }

  /*
  addPizza (pizza) {
    console.log('dans le panier service - ' + pizza.code)
    // $localStorage.$default({
    //   panier:"pizza1"
    // })
    if (!this.$localStorage['panier'][pizza.code]) {
      this.$localStorage['panier'][pizza.code] = { 'pizza': pizza, 'quantite': 1 }
    } else {
      this.$localStorage['panier'][pizza.code].quantite++
    }
  }

  // supprime un TYPE de pizza de la liste, et non une seule pizza (voir la fonction pour modifier les quantités)
  removePizza (codePizza) {
    console.log('remove pizza ' + codePizza)
    this.$localStorage['panier'][codePizza] = undefined
  }
  */

}

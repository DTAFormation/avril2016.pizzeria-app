export class PanierService {
  constructor ($localStorage) {
    this.$localStorage = $localStorage
  }

  addPizza (pizza) {
    console.log('dans le panier service')
    // $localStorage.$default({
    //   panier:"pizza1"
    // })
  }
}
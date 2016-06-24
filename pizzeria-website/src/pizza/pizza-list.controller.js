import { PanierService } from '../shared/service/panier.service'

export class PizzaListController {

  constructor (pizzasService, panierService) {
    this.panierService = panierService
    this.pizzasService = pizzasService
    this.ordering = 'nom'
    this.findAllPizzas()
  }

  addPizza (pizza) {
    console.log(this.panierService)
    console.log(this.pizzasService)
    this.panierService.addPizza(pizza)
  }

  findAllPizzas () {
    const ctrl = this
    return this.pizzasService.findAllPizzas()
        .then(data => {
          ctrl.listePizzas = []
          data.forEach((item) => {
            ctrl.listePizzas.push(item)
          })
        })
  }
}

PizzaListController.$inject = ['PizzasService', '$q', 'PanierService']

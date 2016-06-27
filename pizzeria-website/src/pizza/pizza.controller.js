import {Pizza} from '../shared/model/pizza.js'

export class PizzaController {

  constructor(pizzasService, panierService, $routeParams) {
    this.pizzasService = pizzasService
    this.panierService = panierService

    console.log('verif')
    this.ordering = 'nom'
    this.findAllPizzas()
    this.$routeParams = $routeParams
    this.findPizza()
      .then(response => {
        console.log(response)
        this.pizza = new Pizza(response)
      })
  }

  addPizza(pizza) {
    this.panierService.addPizza(pizza)
  }


  findAllPizzas() {
    const ctrl = this
    return this.pizzasService.findAllPizzas()
      .then(data => {
        ctrl.listePizzas = []
        data.forEach((item) => {
          ctrl.listePizzas.push(item)
        })
      })
  }
  findPizza() {
    const ctrl = this
    return this.pizzasService.findOne(this.$routeParams.code)

  }

}

PizzaController.$inject = ['PizzasService', 'PanierService', '$routeParams']

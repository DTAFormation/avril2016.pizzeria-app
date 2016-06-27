import {Pizza} from '../shared/model/pizza.js'

export class PizzaController {

  constructor (pizzasService, $routeParams) {
    this.pizzasService = pizzasService

    console.log('verif')
    this.ordering = 'nom'
    this.findAllPizzas()
    this.$routeParams = $routeParams
    this.findPizza()
    .then ( response => {
      console.log(response)
      this.pizza = new Pizza(response)
    })
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
  findPizza() {
    const ctrl = this
    return this.pizzasService.findOne(this.$routeParams.code)
    
  }

}

PizzaController.$inject = ['PizzasService','$routeParams']

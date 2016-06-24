import {Pizza} from '../model/pizza.js'

const PIZZA_RESOURCE_URL = 'http://localhost:8080/pizzas'

export class PizzasService {
  constructor ($http) {
    this.$http = $http
  }

  findAllPizzas () {
    return this.$http.get(PIZZA_RESOURCE_URL)
        .then(response => response.data)
        .then(pizzas => pizzas.map(pizza => new Pizza(pizza)))
  }

  findOne (code) {
    return this.$http.get(PIZZA_RESOURCE_URL + '/' + code)
        .then(response => response.data)
  }
}

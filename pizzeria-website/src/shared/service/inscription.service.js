import { Client } from '../model/client.js'

const CLIENT_RESOURCE_URL = 'http://localhost:8080/clients'

export class InscriptionService {
  constructor ($http) {
    this.$http = $http
  }

  //   findAllPizzas() {
  //     return this.$http.get(PIZZA_RESOURCE_URL)
  //         .then(response => response.data)
  //         .then(pizzas => pizzas.map(pizza => new Pizza(pizza)))
  //   }

  //   findOne(code) {
  //     return this.$http.get(PIZZA_RESOURCE_URL + '/' + code)
  //         .then(response => response.data)
  //   }

  saveClient (client) {
    console.log('enter save client service', client)
    return this.$http.post(CLIENT_RESOURCE_URL , client)
  }

}

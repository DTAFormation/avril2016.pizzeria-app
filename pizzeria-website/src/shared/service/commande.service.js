import {Commande} from '../model/commande.js';

const COMMANDE_RESOURCE_URL = 'http://localhost:8080/commandes';

export class CommandesService {
  constructor($http, $localStorage) {
    this.$http = $http
    this.$localStorage = $localStorage
  }

  findAllCommandesClient( clientId ) {
    console.log("clientId : ", clientId)
    if (!clientId) {
      this.$location.path('/home')
    }
    return this.$http.get(COMMANDE_RESOURCE_URL + '/' + clientId)
      .then(response => response.data)
      .then(commandes => commandes.map(commande => new Commande(commande)));
  }

  addOne (commande) {
    return this.$http.post(COMMANDE_RESOURCE_URL, commande)
      .then(response => response.data)
  }
}

import {Commande} from '../model/commande.js';

const COMMANDE_RESOURCE_URL = 'http://localhost:8080/commandes';

export class CommandesService {
  constructor($http) {
    this.$http = $http;
  }

  findAllCommandesClient( clientId ) {
    console.log('clientId', clientId)
    if (clientId) {
      return this.$http.get(COMMANDE_RESOURCE_URL + '/' + clientId)
        .then(response => response.data)
        .then(commandes => commandes.map(commande => new Commande(commande)));
    }
    return this.$http.get(COMMANDE_RESOURCE_URL)
        .then(response => response.data)
        .then(commandes => commandes.map(commande => new Commande(commande)));
  }
}

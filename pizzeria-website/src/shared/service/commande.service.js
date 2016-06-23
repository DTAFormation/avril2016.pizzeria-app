// import { Commande } from '../model/commande'

const COMMANDE_RESOURCE_URL = 'http://localhost:8080/commandes'

export class CommandesService {
  constructor ($http) {
    this.$http = $http
  }

  addOne (commande) {
    return this.$http.post(COMMANDE_RESOURCE_URL, commande)
      .then(response => response.data)
  }
}

import { Client } from '../model/client.js'

const CLIENT_RESOURCE_URL = 'http://localhost:8080/clients'

export class ConnexionService {
  constructor($http) {
    this.$http = $http;
  }

  getClient (id) {
    console.log('enter get client service', id)
    return this.$http.get(CLIENT_RESOURCE_URL +'/'+id)
  }

}
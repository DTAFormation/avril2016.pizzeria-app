import { Client } from '../model/client.js'

const CLIENT_RESOURCE_URL = 'http://localhost:8080/login'

export class ConnexionService {
  constructor($http) {
    this.$http = $http;
  }

  login(client) {
    return this.$http.post(CLIENT_RESOURCE_URL, client).then(response => response.data)
  }
}
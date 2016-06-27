import { Client } from '../model/client.js'

const CLIENT_RESOURCE_URL = 'http://localhost:8080/clients'
const LOGIN_RESOURCE_URL = 'http://localhost:8080/login'


export class ClientService {
  constructor ($http) {
    this.$http = $http
  }

  saveClient (client) {
    return this.$http.post(CLIENT_RESOURCE_URL, client)
  }

  updateClient (client) {
    return this.$http.put(CLIENT_RESOURCE_URL, client)
  }

  login (client) {
    return this.$http.post(LOGIN_RESOURCE_URL, client).then(response => response.data)
  }

}

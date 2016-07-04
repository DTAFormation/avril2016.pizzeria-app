import { Client } from '../model/client.js'

const CLIENT_RESOURCE_URL = 'http://localhost:8080/clients'
const LOGIN_RESOURCE_URL = 'http://localhost:8080/login'
const CLIENT_RESOURCE_RECHERCHE_URL = 'http://localhost:8080/clients/recherche'


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
  rechercheClientByEmail (email) {
    console.log(email + 'ClientService')
    console.log(encodeURI(email) + 'encodage')
    return this.$http.post(CLIENT_RESOURCE_RECHERCHE_URL, email).then(response => { 
      console.log('response', response)
      return response.data
    })
  }

}

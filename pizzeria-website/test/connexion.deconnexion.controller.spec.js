import { Client } from '../src/shared/model/client'

describe('Test du ConnexionController et DeconnexionController', function () {
  var rootScope
  var ctrl
  var ctrlConnexion
  var ctrlDeconnexion
  var http
  var localStorage

  beforeEach(angular.mock.module('pizzeria-website'))

  beforeEach(angular.mock.inject(function ($rootScope, $controller, $httpBackend, $localStorage) {
    const scope = $rootScope.$new()
    rootScope = $rootScope
    ctrl = $controller
    ctrlConnexion = $controller('ConnexionController', {$scope: scope})
    http = $httpBackend
    localStorage = $localStorage
  }))

  it('should create the client in localStorage', function (done) {
    localStorage.client = null
    expect(localStorage.client).toEqual(null)

    let client = new Client({
      id: 1,
      nom: 'nom',
      prenom: 'prenom',
      email: 'email',
      motDePasse: 'motDePasse'
    })

    http.when('POST', 'http://localhost:8080/login').respond(client)

    ctrlConnexion.connexionClient({$invalid: false, email: 'email', motDePasse: 'motDePasse'})
      .then(result => expect(localStorage.client).toEqual(client))
      .finally(done)

    http.flush()
  })

  it('should delete the client in localStorage', function () {
    let client = new Client({
      id: 1,
      nom: 'nom',
      prenom: 'prenom',
      email: 'email',
      motDePasse: 'motDePasse'
    })
    localStorage.client = client
    expect(localStorage.client).toEqual(client)
    ctrlDeconnexion = ctrl('DeconnexionController', {$scope: rootScope.$new()})
    expect(localStorage.client).toEqual(null)
  })
})

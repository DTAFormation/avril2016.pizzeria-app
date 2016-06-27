import { Commande } from '../src/shared/model/commande'
import { Client } from '../src/shared/model/client'

describe('Test du CommandeNewController', function () {
  var ctrl
  var http

  beforeEach(angular.mock.module('pizzeria-website'))

  beforeEach(angular.mock.inject(function ($rootScope, $controller, $httpBackend, $localStorage) {
    const scope = $rootScope.$new()
    ctrl = $controller('CommandeNewController', {$scope: scope})
    http = $httpBackend
    $localStorage.client = new Client({id: 1})
  }))

  it('should save commande', function (done) {
    let commande = new Commande({
      numeroCommande: 'CO-' + new Date().getTime(),
      dateCommande: new Date().getTime(),
      statut: 'NON_TRAITE',
      livreur: null,
      client: null,
      pizzas: []
    })

    http.when('POST', 'http://localhost:8080/commandes').respond(commande)

    ctrl.validate()
      .then(result => expect(result).toEqual(commande))
      .finally(done)

    http.flush()
  })
})

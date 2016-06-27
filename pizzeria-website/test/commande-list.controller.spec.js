import { Commande } from '../src/shared/model/commande'

describe('Test du CommandeListController', function() {
  var ctrl
  var http

  beforeEach(angular.mock.module('pizzeria-website'))

  beforeEach(angular.mock.inject(function ($rootScope, $controller, $routeParams, $httpBackend) {
    const scope = $rootScope.$new()
    ctrl = $controller('CommandeListController', {$scope: scope, $routeParams: {id: 1}})
    http = $httpBackend
  }))

  it('should load commandes client', function (done) {
    let commande = [new Commande({
      id: 1,
      numeroCommande: 'CO-1466781196862',
      statut: 'NON_TRAITE',
      dateCommande: 1466781197000,
      livreur: null,
      client: {
        id: 1,
        nom: 'perchaud',
        prenom: 'samuel',
        email: '',
        motDePasse: '',
        sexe: '',
        dateNaissance: 627174000000,
        adresseNum: '',
        adresseRue: '',
        adresseDetail: '',
        adresseCodePostal: '',
        adresseVille: '',
        numeroTel: ''
      },
      pizzas: [
        {
          id: 1,
          code: 'QX',
          nom: 'qx',
          prix: 15,
          categorie: 'VIANDE',
          urlImage: 'http://placehold.it/150x150'
        }
      ]
    })]

    http.when('GET', 'http://localhost:8080/commandes/1').respond(commande)

    ctrl.findAllCommandes()
      .then(result => expect(result).toEqual(commande))
      .finally(done)

    http.flush()
  })
})

import angular from 'angular'
import ngRoute from 'angular-route'
import ngStorage from 'ngstorage'
import PizzaModule from './pizza'
import InscriptionModule from './inscription'
import CommandeModule from './commande'
import navbar from './navbar'
import PanierModule from './panier'
import ConnexionModule from './connecter'
import DeconnexionModule from './deconnexion'
import ModificationDonneesCompte from './modificationDonneesCompte'

angular.module('pizzeria-website', [PizzaModule, navbar, PanierModule, InscriptionModule, CommandeModule, ConnexionModule, DeconnexionModule, ngRoute, ModificationDonneesCompte, 'ngStorage'])
  .config(function ($routeProvider) {
    $routeProvider
      .when('/pizzas', {
        templateUrl: 'pizza/pizza-list.html'
      })
      .when('/pizzas/:code?',{
        templateUrl:'visual-pizza.html',
        controller:'PizzaController',
        controllerAs: 'ctrl'
      })
      .when('/commandes/new', {
        templateUrl: 'commande/commande-new.html',
        controller: 'CommandeNewController',
        controllerAs: 'ctrl'
      })
      .when('/commandes/:id?', {
        templateUrl: 'commande/commande-list.html',
        controller: 'CommandeListController',
        controllerAs: 'ctrl'
      })
      .when('/connexion', {
        templateUrl: 'client/se-connecter.html',
        controller: 'ConnexionController',
        controllerAs: 'ctrl'
      })
      .when('/deconnexion', {
        template: '',
        controller: 'DeconnexionController',
        controllerAs: 'ctrl'
      })
      .when('/inscription', {
        templateUrl: 'client/inscription.html',
        controller: 'InscriptionController',
        controllerAs: '$ctrl'
      })
      .when('/modificationDonneesCompte', {
        templateUrl: 'client/modificationDonneesCompte.html',
        controller: 'ModificationDonneesCompteController',
        controllerAs: '$ctrl'
      })
      .when('/about', {
        templateUrl: 'about/about.html'
      })
      .when('/home', {
        templateUrl: 'home/home.html'
      })
      .otherwise('/home')
  })
  .controller('Ctrl', function (
    $localStorage
  ) {})

angular.bootstrap(document, ['pizzeria-website'])

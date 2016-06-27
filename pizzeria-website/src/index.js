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

angular.module('pizzeria-website', [PizzaModule, navbar, PanierModule, InscriptionModule, CommandeModule, ConnexionModule, DeconnexionModule, ngRoute, 'ngStorage'])
  .config(function ($routeProvider) {
    $routeProvider
      .when('/pizzas', {
        templateUrl: 'pizza/pizza-list.html'
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
        templateUrl: 'se-connecter.html',
        controller: 'ConnexionController',
        controllerAs: 'ctrl'
      })
      .when('/deconnexion', {
        template: '',
        controller: 'DeconnexionController',
        controllerAs: 'ctrl'
      })
      .when('/inscription', {
        templateUrl: 'inscription/inscription.html',
        controller: 'InscriptionController',
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

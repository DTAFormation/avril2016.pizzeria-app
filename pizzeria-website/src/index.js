import angular from 'angular'
import ngRoute from 'angular-route'
import ngStorage from 'ngstorage'
import PizzaModule from './pizza'
import InscriptionModule from './inscription'
import CommandeModule from './commande'
import navbar from './navbar'
import PanierModule from './panier'
import ConnexionModule from './connecter'

angular.module('pizzeria-website', [PizzaModule, navbar, PanierModule, InscriptionModule, CommandeModule, ConnexionModule, ngRoute, 'ngStorage'])
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
      .when('/connexion', {
        templateUrl: 'se-connecter.html',
        controller: 'ConnexionController',
        controllerAs: 'ctrl'
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

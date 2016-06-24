import angular from 'angular'
import ngRoute from 'angular-route'
import PizzaModule from './pizza'
import InscriptionModule from './inscription'
import CommandeModule from './commande'
import navbar from './navbar'
import PanierModule from './panier'

angular.module('pizzeria-website', [PizzaModule, navbar, PanierModule, ngRoute])
    .config(function ($routeProvider) {
      $routeProvider
          .when('/pizzas', {
            templateUrl: 'pizza/pizza-list.html'
          })
          .when('/about', {
            templateUrl: 'about/about.html'
          })
          .when('/home', {
            templateUrl: 'home/home.html'
          })
          .otherwise('/home')
    })

angular.bootstrap(document, ['pizzeria-website'])

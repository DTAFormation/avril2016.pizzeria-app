import angular from 'angular'
import ngRoute from 'angular-route'
import PizzaModule from './pizza'
import CommandeModule from './commande'
import navbar from './navbar'

angular.module('pizzeria-website', [PizzaModule, CommandeModule, navbar, ngRoute])
    .config(function ($routeProvider) {
      $routeProvider
          .when('/pizzas', {
            templateUrl: 'pizza/pizza-list.html',
            controller: 'PizzaListController',
            controllerAs: 'ctrl'
          })
          .when('/commandes', {
            templateUrl: 'commande/commande-list.html',
            controller: 'CommandeListController',
            controllerAs: 'ctrl'
          })
          .when('/commandes/:id?', {
            templateUrl: 'commande/commande-list.html',
            controller: 'CommandeListController',
            controllerAs: 'ctrl'
          })
          .when('/about', {
            templateUrl: 'about/about.html'
          })
          .when('/home', {
            templateUrl: 'home/home.html'
          })
          .otherwise('/home')
    });

angular.bootstrap(document, ['pizzeria-website'])
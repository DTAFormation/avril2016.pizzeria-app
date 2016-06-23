import angular from 'angular'
import ngRoute from 'angular-route'
import PizzaModule from './pizza'
import InscriptionModule from './inscription'

angular.module('pizzeria-website', [PizzaModule, ngRoute, InscriptionModule])
  .config(function ($routeProvider) {
    $routeProvider
      .when('/pizzas', {
        templateUrl: 'pizza/pizza-list.html',
        controller: 'PizzaListController',
        controllerAs: 'ctrl'
      })
      .when('/about', {
        templateUrl: 'about/about.html'
      })
      .when('/home', {
        templateUrl: 'home/home.html'
      })
      .when('/inscription', {
        templateUrl: 'inscription/inscription.html',
        controller: 'InscriptionController',
        controllerAs: '$ctrl'
      })
      .otherwise('/home')
  })

angular.bootstrap(document, ['pizzeria-website'])

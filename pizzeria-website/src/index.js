import angular from 'angular'
import ngRoute from 'angular-route'
import PizzaModule from './pizza'

angular.module('pizzeria-website', [PizzaModule, ngRoute])
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
          .otherwise('/home')
    });

angular.bootstrap(document, ['pizzeria-website'])
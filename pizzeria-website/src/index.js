import angular from 'angular'
import ngRoute from 'angular-route'
import ngStorage from 'ngstorage'
import PizzaModule from './pizza'
import navbar from './navbar'
import PanierModule from './panier'

angular.module('pizzeria-website', [PizzaModule, navbar, PanierModule, ngRoute, 'ngStorage'])
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
  .controller('Ctrl', function (
    $scope,
    $localStorage,
    $sessionStorage
  ) { });

angular.bootstrap(document, ['pizzeria-website'])

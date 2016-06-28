<<<<<<< HEAD

import angular from 'angular'
import ngRoute from 'angular-route'
import PizzaModule from './pizza'
import InscriptionModule from './inscription'
import CommandeModule from './commande'
import navbar from './navbar'
import PanierModule from './panier'

angular.module('pizzeria-website', [PizzaModule, navbar, PanierModule, InscriptionModule, CommandeModule, ngRoute])
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

=======
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
>>>>>>> origin/ust-006-harmonisation

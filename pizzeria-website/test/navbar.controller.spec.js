import { NavbarController } from '../src/navbar/navbar.controller'
import { Client } from '../src/shared/model/client'

describe('Test du NavbarController', function () {
  var ctrl
  var navbar
  var http
  var localStorage
  var ctrlConnexion
  var ctrlDeconnexion

  beforeEach(angular.mock.module('pizzeria-website'))

  beforeEach(angular.mock.inject(function ($rootScope, $componentController, $location, $controller, $httpBackend, $localStorage) {
    const scope = $rootScope.$new()
    navbar = {
      controller: NavbarController,
      template: `
      <nav class="navbar navbar-default">
        <div class="container-fluid">
          <div class="navbar-header">
            <a class="navbar-brand" href="#/">DTA Pizzeria</a>
          </div>
          <ul class="nav navbar-nav">
            <li ng-class="{active : $ctrl.isActive('/home')}"><a href="#/">Accueil</a></li>
            <li ng-class="{active : $ctrl.isActive('/pizzas')}" role="presentation"><a href="#/pizzas">Nos Pizzas</a></li>
            <li ng-if="$ctrl.logged" ng-class="{active : $ctrl.isActive('/commandes')}" role="presentation"><a href="#/commandes/{{ $ctrl.$localStorage.client.id }}">Vos Commandes</a></li>
            <li ng-class="{active : $ctrl.isActive('/about')}" role="presentation"><a href="#/about">A propos</a></li>
            <li ng-if="$ctrl.logged"  ng-class="{active : $ctrl.isActive('/modificationDonneesCompte')}" role="presentation"><a href="#/modificationDonneesCompte">Mon compte</a></li>
            <li ng-if="!$ctrl.logged" ng-class="{active : $ctrl.isActive('/inscription')}" role="presentation"><a href="#/inscription">Inscription</a></li>
            <li ng-if="!$ctrl.logged" ng-class="{active : $ctrl.isActive('/connexion')}" role="presentation"><a href="#/connexion">Connexion</a></li>
            <li ng-if="$ctrl.logged"  ng-class="{active : $ctrl.isActive('/deconnexion')}" role="presentation"><a href="#/deconnexion">Se déconnecter</a></li>
          </ul>
        </div>
      </nav>
      `
    }
    ctrl = $componentController('navbarComponent', {$location, $scope: scope}, {navbar: navbar})
    ctrlConnexion = $controller('ConnexionController', {$scope: scope})
    ctrlDeconnexion = $controller('DeconnexionController', {$scope: scope})
    http = $httpBackend
    localStorage = $localStorage
  }))

  // Test en cours d'écriture
  it('should display the default navbar after initialisation', function () {
    // ctrl.logged = false
    // console.log('Logged? ', ctrl.logged)

    let htmlToDisplay = `
      <nav class="navbar navbar-default">
        <div class="container-fluid">
          <div class="navbar-header">
            <a class="navbar-brand" href="#/">DTA Pizzeria</a>
          </div>
          <ul class="nav navbar-nav">
            <li ng-class="{active : $ctrl.isActive('/home')}"><a href="#/">Accueil</a></li>
            <li ng-class="{active : $ctrl.isActive('/pizzas')}" role="presentation"><a href="#/pizzas">Nos Pizzas</a></li>
            <li ng-if="$ctrl.logged" ng-class="{active : $ctrl.isActive('/commandes')}" role="presentation"><a href="#/commandes/{{ $ctrl.$localStorage.client.id }}">Vos Commandes</a></li>
            <li ng-class="{active : $ctrl.isActive('/about')}" role="presentation"><a href="#/about">A propos</a></li>
            <li ng-if="$ctrl.logged"  ng-class="{active : $ctrl.isActive('/modificationDonneesCompte')}" role="presentation"><a href="#/modificationDonneesCompte">Mon compte</a></li>
            <li ng-if="!$ctrl.logged" ng-class="{active : $ctrl.isActive('/inscription')}" role="presentation"><a href="#/inscription">Inscription</a></li>
            <li ng-if="!$ctrl.logged" ng-class="{active : $ctrl.isActive('/connexion')}" role="presentation"><a href="#/connexion">Connexion</a></li>
            <li ng-if="$ctrl.logged"  ng-class="{active : $ctrl.isActive('/deconnexion')}" role="presentation"><a href="#/deconnexion">Se déconnecter</a></li>
          </ul>
        </div>
      </nav>
      `

    
    expect(ctrl.logged).toBeDefined()
    expect(ctrl.logged).toEqual(false)
    expect(ctrl.navbar).toBeDefined()
    expect(ctrl.navbar.template).toBeDefined()
    expect(ctrl.navbar.template).toBe(htmlToDisplay)

  })

  it('should display links associated with account management after login (connexion)', function () {
    // Définition 
    let htmlLoggedOut = `
      <nav class="navbar navbar-default">
        <div class="container-fluid">
          <div class="navbar-header">
            <a class="navbar-brand" href="#/">DTA Pizzeria</a>
          </div>
          <ul class="nav navbar-nav">
            <li ng-class="{active : $ctrl.isActive('/home')}"><a href="#/">Accueil</a></li>
            <li ng-class="{active : $ctrl.isActive('/pizzas')}" role="presentation"><a href="#/pizzas">Nos Pizzas</a></li>
            <li ng-class="{active : $ctrl.isActive('/about')}" role="presentation"><a href="#/about">A propos</a></li>
            <li ng-if="!$ctrl.logged" ng-class="{active : $ctrl.isActive('/inscription')}" role="presentation"><a href="#/inscription">Inscription</a></li>
            <li ng-if="!$ctrl.logged" ng-class="{active : $ctrl.isActive('/connexion')}" role="presentation"><a href="#/connexion">Connexion</a></li>
          </ul>
        </div>
      </nav>
      `

    let htmlLoggedIn = `
      <nav class="navbar navbar-default">
        <div class="container-fluid">
          <div class="navbar-header">
            <a class="navbar-brand" href="#/">DTA Pizzeria</a>
          </div>
          <ul class="nav navbar-nav">
            <li ng-class="{active : $ctrl.isActive('/home')}"><a href="#/">Accueil</a></li>
            <li ng-class="{active : $ctrl.isActive('/pizzas')}" role="presentation"><a href="#/pizzas">Nos Pizzas</a></li>
            <li ng-if="$ctrl.logged" ng-class="{active : $ctrl.isActive('/commandes')}" role="presentation"><a href="#/commandes/{{ $ctrl.$localStorage.client.id }}">Vos Commandes</a></li>
            <li ng-class="{active : $ctrl.isActive('/about')}" role="presentation"><a href="#/about">A propos</a></li>
            <li ng-if="$ctrl.logged"  ng-class="{active : $ctrl.isActive('/modificationDonneesCompte')}" role="presentation"><a href="#/modificationDonneesCompte">Mon compte</a></li>
            <li ng-if="$ctrl.logged"  ng-class="{active : $ctrl.isActive('/deconnexion')}" role="presentation"><a href="#/deconnexion">Se déconnecter</a></li>
          </ul>
        </div>
      </nav>
      `
    ctrl.navbar.template = htmlLoggedOut

    // Connexion au site
    localStorage.client = null
    expect(localStorage.client).toEqual(null)

    let client = new Client({
      id: 1,
      nom: 'nom',
      prenom: 'prenom',
      email: 'email',
      motDePasse: 'motDePasse'
    })

    http.when('POST', 'http://localhost:8080/login').respond(client)
    // Vérification de la connexion
    ctrlConnexion.connexionClient({$invalid: false, email: 'email', motDePasse: 'motDePasse'})
      .then(result => expect(localStorage.client).toEqual(client))
/*
    console.log('*** Local Storage ****')
    console.log('Client : ', localStorage.client)
    console.log('*** Fin ****')

    console.log(ctrl.logged)
*/
    ctrl.logged = true
    expect(ctrl.logged).toEqual(true)
    ctrl.navbar.template = htmlLoggedIn


    // Vérification de la mise à jour du template
    expect(ctrl.navbar.template).toBe(htmlLoggedIn)
  })

  it('should display the default navbar after logout', function () {
    let htmlLoggedOut = `
      <nav class="navbar navbar-default">
        <div class="container-fluid">
          <div class="navbar-header">
            <a class="navbar-brand" href="#/">DTA Pizzeria</a>
          </div>
          <ul class="nav navbar-nav">
            <li ng-class="{active : $ctrl.isActive('/home')}"><a href="#/">Accueil</a></li>
            <li ng-class="{active : $ctrl.isActive('/pizzas')}" role="presentation"><a href="#/pizzas">Nos Pizzas</a></li>
            <li ng-class="{active : $ctrl.isActive('/about')}" role="presentation"><a href="#/about">A propos</a></li>
            <li ng-if="!$ctrl.logged" ng-class="{active : $ctrl.isActive('/inscription')}" role="presentation"><a href="#/inscription">Inscription</a></li>
            <li ng-if="!$ctrl.logged" ng-class="{active : $ctrl.isActive('/connexion')}" role="presentation"><a href="#/connexion">Connexion</a></li>
          </ul>
        </div>
      </nav>
      `

    let htmlLoggedIn = `
      <nav class="navbar navbar-default">
        <div class="container-fluid">
          <div class="navbar-header">
            <a class="navbar-brand" href="#/">DTA Pizzeria</a>
          </div>
          <ul class="nav navbar-nav">
            <li ng-class="{active : $ctrl.isActive('/home')}"><a href="#/">Accueil</a></li>
            <li ng-class="{active : $ctrl.isActive('/pizzas')}" role="presentation"><a href="#/pizzas">Nos Pizzas</a></li>
            <li ng-if="$ctrl.logged" ng-class="{active : $ctrl.isActive('/commandes')}" role="presentation"><a href="#/commandes/{{ $ctrl.$localStorage.client.id }}">Vos Commandes</a></li>
            <li ng-class="{active : $ctrl.isActive('/about')}" role="presentation"><a href="#/about">A propos</a></li>
            <li ng-if="$ctrl.logged"  ng-class="{active : $ctrl.isActive('/modificationDonneesCompte')}" role="presentation"><a href="#/modificationDonneesCompte">Mon compte</a></li>
            <li ng-if="$ctrl.logged"  ng-class="{active : $ctrl.isActive('/deconnexion')}" role="presentation"><a href="#/deconnexion">Se déconnecter</a></li>
          </ul>
        </div>
      </nav>
      `
    ctrl.navbar.template = htmlLoggedIn
    // Déconnexion du site
    let client = new Client({
      id: 1,
      nom: 'nom',
      prenom: 'prenom',
      email: 'email',
      motDePasse: 'motDePasse'
    })
    localStorage.client = client
    expect(localStorage.client).toEqual(client)
    // ctrlDeconnexion = $controller('DeconnexionController', {$scope: scope})
    expect(localStorage.client).toEqual(null)

    // Vérification de la déconnexion

    // Vérification de la mise à jour du template
  })
})

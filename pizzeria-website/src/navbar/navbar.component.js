import { NavbarController } from './navbar.controller'

export const navbarComponent = {
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
        <li ng-class="{active : $ctrl.isActive('/perso')}" role="presentation"><a href="#/perso">Pizza personallisée</a></li>
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

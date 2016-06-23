import { NavbarController } from './navbar.controller'

export const navbarComponent = {
  controller: NavbarController,
  template: `
  <nav>
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="#/">DTA Pizzeria</a>
    </div>
    <ul class="nav navbar-nav">
      <li ng-class="{active : $ctrl.isActive('/home')}"><a href="#/">Accueil</a></li>
      <li ng-class="{active : $ctrl.isActive('/pizzas')}" role="presentation"><a href="#/pizzas">Nos Pizzas</a></li>
      <li ng-class="{active : $ctrl.isActive('/about')}" role="presentation"><a href="#/about">A propos</a></li>
    </ul>
  </div>
</nav>
  `
}
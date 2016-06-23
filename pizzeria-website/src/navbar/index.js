import angular from 'angular'
import { navbarComponent } from './navbar.component'

export default

  angular.module('dtang.navbar', [])
    .component('navbarComponent', navbarComponent)
    .name
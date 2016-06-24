import angular from 'angular'
import { PanierComponent } from './panier.component'
import { PanierService } from '../shared/service/panier.service'

export default angular.module('pizzeria.panier', [])
    .component('panierComponent', PanierComponent)
    .service('PanierService', PanierService)
    .name

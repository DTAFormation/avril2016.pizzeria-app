import angular from 'angular'
import angularConfirm from 'angular-confirm'
import { PanierComponent } from './panier.component'
import { PanierService } from '../shared/service/panier.service'
import modal from 'angular-ui-bootstrap/src/modal/index-nocss.js';
import typeahead from 'angular-ui-bootstrap/src/typeahead/index-nocss.js';

export default angular.module('pizzeria.panier', ['angular-confirm', modal])
    .component('panierComponent', PanierComponent)
    .service('PanierService', PanierService)
    .name

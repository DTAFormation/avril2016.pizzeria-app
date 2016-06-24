import angular from 'angular';
import { CommandeListController } from './commande-list.controller';
import { CommandesService } from '../shared/service/commande.service';

export default angular.module('pizzeria.commande', [])
    .controller('CommandeListController', CommandeListController)
    .service('CommandesService', CommandesService)
    .name;

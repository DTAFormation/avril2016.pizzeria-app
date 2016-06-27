import angular from 'angular'
import { CommandeNewController } from './commande-new.controller'
import { CommandeListController } from './commande-list.controller'
import { CommandesService } from '../shared/service/commande.service'

export default angular.module('pizzeria.commande', [])
  .controller('CommandeNewController', CommandeNewController)
  .controller('CommandeListController', CommandeListController)
  .service('CommandesService', CommandesService)
  .name

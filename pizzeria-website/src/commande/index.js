import angular from 'angular'
import { CommandeNewController } from './commande-new.controller'
import { CommandesService } from '../shared/service/commande.service'

export default angular.module('pizzeria.commande', [])
  .controller('CommandeNewController', CommandeNewController)
  .service('CommandesService', CommandesService)
  .name

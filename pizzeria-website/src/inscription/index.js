import angular from 'angular'
import { InscriptionController } from './inscription.controller'
import { InscriptionService } from '../shared/service/inscription.service'

export default angular.module('pizzeria.inscription', [])
  .controller('InscriptionController', InscriptionController)
  .service('InscriptionService', InscriptionService)
  .name

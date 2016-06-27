import angular from 'angular'
import { InscriptionController } from './inscription.controller'
import { ClientService } from '../shared/service/client.service'

export default angular.module('pizzeria.inscription', [])
  .controller('InscriptionController', InscriptionController)
  .service('ClientService', ClientService)
  .name

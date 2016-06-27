import angular from 'angular'
import { ConnexionController } from './connexion.controller'
import { ConnexionService } from '../shared/service/connexion.service'

export default angular.module('pizzeria.connexion', [])
  .controller('ConnexionController', ConnexionController)
  .service('ConnexionService', ConnexionService)
  .name

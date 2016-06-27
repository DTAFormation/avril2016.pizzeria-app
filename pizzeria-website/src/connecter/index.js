import angular from 'angular'
import { ConnexionController } from './Connexion.controller'
import { ClientService } from '../shared/service/client.service'

export default angular.module('pizzeria.connexion', [])
  .controller('ConnexionController', ConnexionController)
  .service('ClientService', ClientService)
  .name

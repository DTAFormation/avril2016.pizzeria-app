// $('.message a').click(function(){
//    $('form').animate({height: "toggle", opacity: "toggle"}, "slow")
// })

import angular from 'angular'
import { ModificationDonneesCompteController } from './modificationDonneesCompte.controller'
import { ClientService } from '../shared/service/client.service'

export default angular.module('pizzeria.modificationDonneesCompte', [])
  .controller('ModificationDonneesCompteController', ModificationDonneesCompteController)
  .service('ClientService', ClientService)
  .name

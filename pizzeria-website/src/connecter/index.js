// $('.message a').click(function(){
//    $('form').animate({height: "toggle", opacity: "toggle"}, "slow");
// });

import angular from 'angular';
import { ConnexionController } from './Connexion.controller';
import { ConnexionService } from '../shared/service/connexion.service';

export default angular.module('pizzeria.connexion', [])
    .controller('ConnexionController', ConnexionController)
    .service('ConnexionService', ConnexionService)
    .name;
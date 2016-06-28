<<<<<<< HEAD
import angular from 'angular';
import { PizzaListController } from './pizza-list.controller';
import { PizzasService } from '../shared/service/pizza.service';

export default angular.module('pizzeria.pizza', [])
    .controller('PizzaListController', PizzaListController)
    .service('PizzasService', PizzasService)
    .name;
=======
import angular from 'angular'
import { PizzaListComponent } from './pizza-list.component'
import { PizzasService } from '../shared/service/pizza.service'

export default angular.module('pizzeria.pizza', [])
    .component('pizzaList', PizzaListComponent)
    .service('PizzasService', PizzasService)
    .name
>>>>>>> refs/remotes/origin/master

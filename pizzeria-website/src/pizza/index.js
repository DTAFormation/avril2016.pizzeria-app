import angular from 'angular';
import { PizzaListController } from './pizza-list.controller';
import { PizzasService } from '../shared/service/pizza.service';

export default angular.module('pizzeria.pizza', [])
    .controller('PizzaListController', PizzaListController)
    .service('PizzasService', PizzasService)
    .name;

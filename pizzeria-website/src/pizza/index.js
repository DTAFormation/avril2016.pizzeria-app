import angular from 'angular'
import { PizzaListComponent } from './pizza-list.component'
import { PizzasService } from '../shared/service/pizza.service'
import {PizzaController} from './pizza.controller'

export default angular.module('pizzeria.pizza', [])
    .component('pizzaList', PizzaListComponent)
    .controller('PizzaController', PizzaController)
    .service('PizzasService', PizzasService)
    .name

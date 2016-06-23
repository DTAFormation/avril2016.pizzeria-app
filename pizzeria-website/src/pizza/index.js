import angular from 'angular'
import { PizzaListComponent } from './pizza-list.component'
import { PizzasService } from '../shared/service/pizza.service'

export default angular.module('pizzeria.pizza', [])
    .component('pizzaList', PizzaListComponent)
    .service('PizzasService', PizzasService)
    .name

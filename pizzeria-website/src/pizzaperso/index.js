import angular from 'angular'
import { PizzaPersoComponent } from './pizza-perso.component'
import { IngredientService } from '../shared/service/ingredient.service'

export default angular.module('pizzeria.pizzaperso', [])
    .component('pizzaPerso', PizzaPersoComponent)
    .service('IngredientService', IngredientService)
    .name

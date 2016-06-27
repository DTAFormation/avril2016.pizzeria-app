import { Ingredient } from '../shared/model/ingredient.js'
import { Pizza } from '../shared/model/pizza.js'

export class PizzaPersoController {

  constructor (IngredientService) {
    console.log('controller pizza perso initialisé')
    this.IngredientService = IngredientService
    this.ingredients = []
    this.getIngredients()
    this.bghover = []
    this.pizzaIngredients = []
  }

  onAddIngredient (ingredient) {
    this.pizzaIngredients.push(ingredient)
  }

  onRemoveIngredient (ingredient) {
    console.log('A faire, enlever un ingrédient ! =)')
  }

  onHoverIngredient ($index) {
    this.bghover[$index] = {"background-color": "lightgrey"}
  }

  onLeaveIngredient ($index) {
    this.bghover[$index] = {"background-color": "white"}
  }

  getIngredients () {
    return this.IngredientService.getIngredients().then(response => {
      response.forEach(ingredient => this.ingredients.push(ingredient))
      
    })
  }
}

PizzaPersoController.$inject = ['IngredientService']

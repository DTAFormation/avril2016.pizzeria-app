import { Ingredient } from '../model/ingredient.js'

export class IngredientService {
  constructor ($http, $q) {
    this.$http = $http
    this.$q = $q
  }

  getIngredients () {
    if (this.ingredients) {
      return this.$q.resolve(this.ingredients)
    } else {
      return this.$http.get('http://localhost:1337/ingredients')
        .then(response => {
          this.ingredients = response.data
            .map(obj => new Ingredient(obj))
          return this.ingredients
        })
    }
  }
}

IngredientService.$inject = ['$http', '$q']

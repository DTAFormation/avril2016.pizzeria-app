export class PizzaListController {

  constructor (pizzasService, panierService) {
    this.pizzasService = pizzasService
    this.panierService = panierService
    this.ordering = 'id'
    this.findAllPizzas()
  }

  addPizza (pizza) {
    this.panierService.addPizza(pizza)
  }

  findAllPizzas () {
    const ctrl = this
    return this.pizzasService.findAllPizzas()
        .then(data => {
          ctrl.listePizzas = []
          data.forEach((item) => {
            ctrl.listePizzas.push(item)
          })
        })
  }
}

PizzaListController.$inject = ['PizzasService', 'PanierService']

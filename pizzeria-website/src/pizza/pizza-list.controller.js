<<<<<<< HEAD
<<<<<<< HEAD
export class PizzaListController {

  constructor(pizzasService) {
    this.pizzasService = pizzasService;
    this.ordering = 'nom';
    this.findAllPizzas();
  }

  findAllPizzas() {
    const ctrl = this;
    return this.pizzasService.findAllPizzas()
        .then(data => {
          ctrl.listePizzas = [];
          data.forEach((item) => {
            ctrl.listePizzas.push(item)
          })
        });
  }
}

PizzaListController.$inject = ['PizzasService'];
=======
export class PizzaListController {

  constructor (pizzasService) {
    this.pizzasService = pizzasService
    this.ordering = 'nom'
    this.findAllPizzas()
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

PizzaListController.$inject = ['PizzasService']
>>>>>>> refs/remotes/origin/master
=======
export class PizzaListController {

  constructor(pizzasService) {
    this.pizzasService = pizzasService;
    this.ordering = 'nom';
    this.findAllPizzas();
  }

  findAllPizzas() {
    const ctrl = this;
    return this.pizzasService.findAllPizzas()
        .then(data => {
          ctrl.listePizzas = [];
          data.forEach((item) => {
            ctrl.listePizzas.push(item)
          })
        });
  }
}

PizzaListController.$inject = ['PizzasService'];
>>>>>>> origin/ust-006-harmonisation

import { Pizza } from '../src/shared/model/pizza'

const VERBOSE = false // mettre true pour afficher certaines sorties console

describe('Test: PanierController', function () {
  var ctrl

  // -----------------------------------------------------
  // -- initialisations

  beforeEach(angular.mock.module('pizzeria-website'))

  beforeEach(angular.mock.inject(function ($rootScope, $componentController) {
    const scope = $rootScope.$new()
    ctrl = $componentController('panierComponent', {$scope: scope})
    ctrl.clear()
  }))

  // -----------------------------------------------------
  // -- tests

  // panier vide lors de son initialisation
  it('should be empty right after initialization', function () {
    expect(ctrl.isEmpty()).toEqual(true)
  })

  // insertion de pizzas dans le panier
  it('should insert pizzas in the basket', function () {
    var contenuPanier

    expect(ctrl.size()).toEqual(0)
    if (VERBOSE) console.log('ctrl.size() === ' + ctrl.size())

    ctrl.addPizza(new Pizza({'id': 1, 'code': 'royale', 'nom': 'Royale', 'prix': 12, 'categorie': 'VIANDE', 'urlImage': 'http://placehold.it/150x150'}))
    expect(ctrl.size()).toEqual(1)
    if (VERBOSE) console.log('ctrl.size() === ' + ctrl.size())

    ctrl.addPizza(new Pizza({'id': 2, 'code': 'marguerita', 'nom': 'Margherita', 'prix': 15, 'categorie': 'VIANDE', 'urlImage': 'http://placehold.it/150x150'}))
    expect(ctrl.size()).toEqual(2)
    if (VERBOSE) console.log('ctrl.size() === ' + ctrl.size())

    contenuPanier = ctrl.findAllPizzas()
    expect(contenuPanier[2].quantite).toEqual(1)

    // identifiant non séquentiel (1 et 2 déjà pris, prendre quelque chose de non adjacent)
    ctrl.addPizza(new Pizza({'id': 19, 'code': 'bolognaise', 'nom': 'Bolognaise', 'prix': 12, 'categorie': 'VIANDE', 'urlImage': 'http://placehold.it/150x150'}))
    expect(ctrl.size()).toEqual(3)
    if (VERBOSE) console.log('ctrl.size() === ' + ctrl.size())

    // pizza déjà existante : pas de changement de taille
    ctrl.addPizza(new Pizza({'id': 2, 'code': 'marguerita', 'nom': 'Margherita', 'prix': 15, 'categorie': 'VIANDE', 'urlImage': 'http://placehold.it/150x150'}))
    expect(ctrl.size()).toEqual(3)
    if (VERBOSE) console.log('ctrl.size() === ' + ctrl.size())

    // ajouter test pour quantité pizza d'un type dans ctrl
    contenuPanier = ctrl.findAllPizzas()
    expect(contenuPanier[2].quantite).toEqual(2)

    // tester la quantité totale des pizzas tous types confondus
    var keys = Object.keys(contenuPanier)
    var somme = 0
    for (var i = 0; i < keys.length; i++) {
      somme += contenuPanier[keys[i]].quantite
    }
    expect(somme).toEqual(4)

  // insertion d'une pizza différente, mais ayant un identifiant déjà pris : exception (cas qui ne devrait pas se produire)
  // DÉSACTIVÉ - addPizza ne vérifie pas encore si la pizza fournie est identique ou non à celle ayant le même id
  // expect(() => {
  //   ctrl.addPizza(new Pizza({'id': 2, 'code': 'fausse_marguerita', 'nom': 'Fausse margherita', 'prix': 20, 'categorie': 'VIANDE', 'urlImage': 'http://placehold.it/150x150'}))
  // }).toThrow()
  })

  // suppression d'une pizza du panier
  it('should delete an item from the basket', function () {
    ctrl.addPizza(new Pizza({'id': 1, 'code': 'royale', 'nom': 'Royale', 'prix': 12, 'categorie': 'VIANDE', 'urlImage': 'http://placehold.it/150x150'}))
    ctrl.addPizza(new Pizza({'id': 2, 'code': 'marguerita', 'nom': 'Margherita', 'prix': 15, 'categorie': 'VIANDE', 'urlImage': 'http://placehold.it/150x150'}))
    ctrl.addPizza(new Pizza({'id': 8, 'code': 'savoyarde', 'nom': 'Savoyarde', 'prix': 14, 'categorie': 'VIANDE', 'urlImage': 'http://placehold.it/150x150'}))
    ctrl.addPizza(new Pizza({'id': 13, 'code': '4fromages', 'nom': '4 fromages', 'prix': 10, 'categorie': 'SANS_VIANDE', 'urlImage': 'http://placehold.it/150x150'}))

    if (VERBOSE) console.log('ctrl.size() === ', ctrl.size())
    expect(ctrl.size()).toEqual(4)

    // supprime 1 item
    if (VERBOSE) console.log('ctrl.size() === ', ctrl.size())
    ctrl.deletePizza(1)
    expect(ctrl.size()).toEqual(3)

    // item déjà supprimé : toujours autant d'items
    if (VERBOSE) console.log('ctrl.size() === ', ctrl.size())
    ctrl.deletePizza(1)
    expect(ctrl.size()).toEqual(3)

    // item déjà supprimé : toujours autant d'items
    if (VERBOSE) console.log('ctrl.size() === ', ctrl.size())
    ctrl.deletePizza(8)
    expect(ctrl.size()).toEqual(2)
  })

  // incrementation d'une pizza du panier
  it('should increment an item from the basket', function () {
    let pizza = new Pizza({'id': 1, 'code': 'royale', 'nom': 'Royale', 'prix': 12, 'categorie': 'VIANDE', 'urlImage': 'http://placehold.it/150x150'})
    ctrl.addPizza(pizza)

    let panier = ctrl.findAllPizzas()

    if (VERBOSE) console.log('panier[pizza.id].pizza === ', panier[pizza.id].pizza)
    expect(panier[pizza.id].pizza).toEqual(pizza)

    // incremente 1 item
    if (VERBOSE) console.log('panier[pizza.id].quantite === ', panier[pizza.id].quantite)
    ctrl.incrementPizza(pizza.id)
    expect(panier[pizza.id].quantite).toEqual(2)

    // incremente encore une fois
    if (VERBOSE) console.log('panier[pizza.id].quantite === ', panier[pizza.id].quantite)
    ctrl.incrementPizza(pizza.id)
    expect(panier[pizza.id].quantite).toEqual(3)
  })

  // décrementation d'une pizza du panier
  it('should decrement an item from the basket', function () {
    let pizza = new Pizza({'id': 1, 'code': 'royale', 'nom': 'Royale', 'prix': 12, 'categorie': 'VIANDE', 'urlImage': 'http://placehold.it/150x150'})
    ctrl.addPizza(pizza)

    let panier = ctrl.findAllPizzas()

    if (VERBOSE) console.log('panier[pizza.id].pizza === ', panier[pizza.id].pizza)
    expect(ctrl.size()).toEqual(1)
    expect(panier[pizza.id].pizza).toEqual(pizza)
    ctrl.incrementPizza(pizza.id)
    expect(panier[pizza.id].quantite).toEqual(2)

    // decrement 1 item
    if (VERBOSE) console.log('panier[pizza.id].quantite === ', panier[pizza.id].quantite)
    ctrl.decrementPizza(pizza.id)
    expect(ctrl.size()).toEqual(1)
    expect(panier[pizza.id].quantite).toEqual(1)

    // decrement encore une fois et tombe a 0, supprime la ligne
    if (VERBOSE) console.log('panier[pizza.id].quantite === ', panier[pizza.id].quantite)
    ctrl.decrementPizza(pizza.id)
    expect(ctrl.size()).toEqual(0)
  })

  // vidage du panier
  it('should clean the basket', function () {
    ctrl.addPizza(new Pizza({'id': 1, 'code': 'royale', 'nom': 'Royale', 'prix': 12, 'categorie': 'VIANDE', 'urlImage': 'http://placehold.it/150x150'}))
    ctrl.addPizza(new Pizza({'id': 2, 'code': 'marguerita', 'nom': 'Margherita', 'prix': 15, 'categorie': 'VIANDE', 'urlImage': 'http://placehold.it/150x150'}))
    ctrl.addPizza(new Pizza({'id': 8, 'code': 'savoyarde', 'nom': 'Savoyarde', 'prix': 14, 'categorie': 'VIANDE', 'urlImage': 'http://placehold.it/150x150'}))
    ctrl.addPizza(new Pizza({'id': 13, 'code': '4fromages', 'nom': '4 fromages', 'prix': 10, 'categorie': 'SANS_VIANDE', 'urlImage': 'http://placehold.it/150x150'}))

    if (VERBOSE) console.log('ctrl.size() === ', ctrl.size())
    expect(ctrl.size()).toEqual(4)

    // vide le panier
    if (VERBOSE) console.log('ctrl.size() === ', ctrl.size())
    ctrl.deleteAllPizzas()
    expect(ctrl.size()).toEqual(0)
  })
})
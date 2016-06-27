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
    expect(ctrl.size()).toEqual(0)
    if (VERBOSE) console.log('ctrl.size() === ' + ctrl.size())

    ctrl.addPizza(new Pizza({'id': 1, 'code': 'royale', 'nom': 'Royale', 'prix': 12, 'categorie': 'VIANDE', 'urlImage': 'http://placehold.it/150x150'}))
    expect(ctrl.size()).toEqual(1)
    if (VERBOSE) console.log('ctrl.size() === ' + ctrl.size())

    ctrl.addPizza(new Pizza({'id': 2, 'code': 'marguerita', 'nom': 'Margherita', 'prix': 15, 'categorie': 'VIANDE', 'urlImage': 'http://placehold.it/150x150'}))
    expect(ctrl.size()).toEqual(2)
    if (VERBOSE) console.log('ctrl.size() === ' + ctrl.size())

    // identifiant non séquentiel (1 et 2 déjà pris, prendre quelque chose de non adjacent)
    ctrl.addPizza(new Pizza({'id': 19, 'code': 'bolognaise', 'nom': 'Bolognaise', 'prix': 12, 'categorie': 'VIANDE', 'urlImage': 'http://placehold.it/150x150'}))
    expect(ctrl.size()).toEqual(3)
    if (VERBOSE) console.log('ctrl.size() === ' + ctrl.size())

    // pizza déjà existante : pas de changement de taille
    ctrl.addPizza(new Pizza({'id': 2, 'code': 'marguerita', 'nom': 'Margherita', 'prix': 15, 'categorie': 'VIANDE', 'urlImage': 'http://placehold.it/150x150'}))
    expect(ctrl.size()).toEqual(3)
    if (VERBOSE) console.log('ctrl.size() === ' + ctrl.size())

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
    ctrl.addPizza(new Pizza({'id': 3, 'code': 'savoyarde', 'nom': 'Savoyarde', 'prix': 14, 'categorie': 'VIANDE', 'urlImage': 'http://placehold.it/150x150'}))
    ctrl.addPizza(new Pizza({'id': 4, 'code': '4fromages', 'nom': '4 fromages', 'prix': 10, 'categorie': 'SANS_VIANDE', 'urlImage': 'http://placehold.it/150x150'}))
    
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
    ctrl.deletePizza(3)
    expect(ctrl.size()).toEqual(2)
  })
})

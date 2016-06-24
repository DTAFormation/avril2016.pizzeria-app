import { Pizza } from '../src/shared/model/pizza'

describe('Test du PanierController', function () {
  var ctrl

  beforeEach(angular.mock.module('pizzeria-website'))

  beforeEach(angular.mock.inject(function ($rootScope, $componentController) {
    const scope = $rootScope.$new()
    ctrl = $componentController('panierComponent', {$scope: scope})
    // ctrl.panierService.deleteAllPizzas()
    ctrl.clear()
  }))

  it('should be empty before any insertion', function () {
    // TODO isEmpty ne retourne pas toujours true, même avec un panier vide...
    expect(ctrl.isEmpty()).toEqual(true)
  })

  it('should insert pizzas in the basket', function () {
    // TODO à implémenter, échoue par défaut
    expect(false).toEqual(true)
  })

  it('should delete an item from the basket', function () {
    ctrl.addPizza(new Pizza({ 'pizza': new Pizza({'id': 1, 'code': 'royale', 'nom': 'Royale', 'prix': 12, 'categorie': 'VIANDE', 'urlImage': 'http://placehold.it/150x150'}), 'quantite': 1 }))
    ctrl.addPizza(new Pizza({ 'pizza': new Pizza({'id': 2, 'code': 'marguerita', 'nom': 'Margherita', 'prix': 15, 'categorie': 'VIANDE', 'urlImage': 'http://placehold.it/150x150'}), 'quantite': 3 }))
    ctrl.addPizza(new Pizza({ 'pizza': new Pizza({'id': 3, 'code': 'savoyarde', 'nom': 'Savoyarde', 'prix': 14, 'categorie': 'VIANDE', 'urlImage': 'http://placehold.it/150x150'}), 'quantite': 4 }))
    ctrl.addPizza(new Pizza({ 'pizza': new Pizza({'id': 4, 'code': '4fromages', 'nom': '4 fromages', 'prix': 10, 'categorie': 'SANS_VIANDE', 'urlImage': 'http://placehold.it/150x150'}), 'quantite': 1 }))
    
    console.log('ctrl.size() = ', ctrl.size())
    expect(ctrl.size()).toEqual(4)
    
    // supprime 1 item
    console.log('ctrl.size() = ', ctrl.size())
    ctrl.deletePizza(1)
    expect(ctrl.size()).toEqual(3)
    
    // item déjà supprimé : toujours autant d'items
    console.log('ctrl.size() = ', ctrl.size())
    ctrl.deletePizza(1)
    expect(ctrl.size()).toEqual(3)
    
    // item déjà supprimé : toujours autant d'items
    console.log('ctrl.size() = ', ctrl.size())
    ctrl.deletePizza(3)
    expect(ctrl.size()).toEqual(2)

  })
})
